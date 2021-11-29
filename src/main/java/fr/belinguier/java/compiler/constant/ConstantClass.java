package fr.belinguier.java.compiler.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * This Constant class represents a class constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.1">ConstantClass in ClassFile's structure</a>
 */
public class ConstantClass extends Constant {

    /**
     * This field contains the {@link Class}'s name as a constant.
     * @since 1.0
     * @see ConstantUtf8
     */
    private final ConstantUtf8 className;

    /**
     * Initializes a newly created Constant object to represent {@link Class}.
     * @param className The {@link Class}'s name to represent as a constant.
     * @since 1.0
     */
    public ConstantClass(ConstantUtf8 className) {
        super(ConstantType.CLASS);
        this.className = className;
    }

    /**
     * Initializes a newly created Constant object to represent {@link Class}.
     * @param className The {@link Class}'s name to represent.
     * @since 1.0
     */
    public ConstantClass(String className) {
        this(new ConstantUtf8(className));
    }

    /**
     * Get the represented class's name, as a constant.
     * @return The class's name, as a constant.
     * @since 1.0
     * @see ConstantUtf8
     */
    public ConstantUtf8 getClassName() {
        return this.className;
    }

    @Override
    public int serializationSize() {
        return super.serializationSize() + 2;
    }

    @Override
    public byte[] serialize(ConstantPool constantPool) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(serializationSize());
        final short nameIndex = constantPool.getOrRegister(this.className);

        byteBuffer.put(getConstantType().getTag());
        byteBuffer.putShort(nameIndex);
        return byteBuffer.array();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.className);
    }
}
