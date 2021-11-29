package fr.belinguier.java.compiler.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * This Constant class represents a float constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.4">ConstantFloat in ClassFile's structure</a>
 */
public class ConstantFloat extends Constant {

    /**
     * This field contains the {@link Float} number, that this constant should represent.
     * @since 1.0
     */
    private final float value;

    /**
     * Initializes a newly created Constant object to represent {@link Float} number.
     * @param value The {@link Float} number that the constant should represent.
     * @since 1.0
     */
    public ConstantFloat(float value) {
        super(ConstantType.FLOAT);
        this.value = value;
    }

    /**
     * Get the {@link Float} number that the constant represents.
     * @return The {@link Float} number.
     */
    public float getValue() {
        return this.value;
    }

    @Override
    public int serializationSize() {
        return super.serializationSize() + 4;
    }

    @Override
    public byte[] serialize(ConstantPool constantPool) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(serializationSize());

        byteBuffer.put((byte) getConstantType().getTag());
        byteBuffer.putFloat(this.value);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.value);
    }
}
