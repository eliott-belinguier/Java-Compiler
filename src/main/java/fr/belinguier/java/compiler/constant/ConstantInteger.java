package fr.belinguier.java.compiler.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * This Constant class represents a integer constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.4">ConstantInteger in ClassFile's structure</a>
 */
public class ConstantInteger extends Constant {

    /**
     * This field contains the {@link Integer} number, that this constant should represent.
     * @since 1.0
     */
    private final int value;

    /**
     * Initializes a newly created Constant object to represent {@link Integer} number.
     * @param value The {@link Integer} number that the constant should represent.
     * @since 1.0
     */
    public ConstantInteger(int value) {
        super(ConstantType.INTEGER);
        this.value = value;
    }

    /**
     * Get the {@link Integer} number that the constant represents.
     * @return The {@link Integer} number.
     * @since 1.0
     */
    public int getValue() {
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
        byteBuffer.putInt(this.value);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.value);
    }
}
