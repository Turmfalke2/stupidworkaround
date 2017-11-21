import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.io.*;

//import javassist.*;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader classLoader, String className, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
               
            String normalizedClassName = className.replaceAll("/", ".");
		      if (!normalizedClassName.equals("Reika.ChromatiCraft.Auxiliary.RecipeManagers.CastingRecipes.Blocks.PortalRecipe")) {
               System.out.println("AGENT: not interested in " + normalizedClassName);
		      	return null; // No modifications.
		      }
            System.out.println("AGENT: found class: " + normalizedClassName);
            ClassReader cr = new ClassReader(bytes);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
            ClassVisitor cv = new MethodFinder(cw, className);
            cr.accept(cv, 0);

            System.out.println("AGENT: writing back");
            byte[] nC = cw.toByteArray();
            System.out.println("AGENT: length + " + nC.length);

            return nC;
      }
   });
   }



}
