import org.objectweb.asm.*;

public class DieMethodDie extends MethodVisitor {
    private final MethodVisitor targetWriter;
    private final int locals;

    DieMethodDie(MethodVisitor writer, int llocals) {
        super(Opcodes.ASM5);
        targetWriter = writer;
        locals = llocals;
    }


    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        targetWriter.visitMaxs(0, locals);
    }

    @Override
    public void visitCode() {
        targetWriter.visitCode();
        targetWriter.visitInsn(Opcodes.RETURN);
    }

    @Override
    public void visitEnd() {
        targetWriter.visitEnd();
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return targetWriter.visitAnnotation(desc, visible);
    }

    @Override
    public void visitParameter(String name, int access) {
        targetWriter.visitParameter(name, access);
    }
}
