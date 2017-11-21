import org.objectweb.asm.*;


public class MethodFinder extends ClassVisitor {
    private String className;
           
    public MethodFinder(ClassVisitor cv, String pClassName) {
	super(Opcodes.ASM5, cv);
	className = pClassName;
    }
                                                         
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
       System.out.println("AGENT: method + " +  name);
       if(name.equals("genLightning"))
       {
         return new DieMethodDie(
            super.visitMethod(access, name, desc, signature, exceptions),
            (Type.getArgumentsAndReturnSizes(desc)>>2)-1);
       } else  {
         return super.visitMethod(access, name, desc, signature, exceptions); 
       }
    }
}
