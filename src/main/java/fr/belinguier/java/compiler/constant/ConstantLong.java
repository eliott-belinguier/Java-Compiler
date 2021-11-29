package fr.belinguier.java.compiler.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * This Constant class represents a long constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.5">ConstantLong in ClassFile's structure</a>
 */
public class ConstantLong extends Constant {

    /**
     * This field contains the {@link Long} number, that this constant should represent.
     * @since 1.0
     */
    private final long value;

    /**
     * Initializes a newly created Constant object to represent {@link Long} number.
     * @param value The {@link Long} number that the constant should represent.
     * @since 1.0
     */
    public ConstantLong(long value) {
        super(ConstantType.LONG);
        this.value = value;
    }

    /**
     * Get the {@link Long} number that the constant represents.
     * @return The {@link Long} number.
     */
    public long getValue() {
        return this.value;
    }

    @Override
    public int serializationSize() {
        return super.serializationSize() + 8;
    }

    @Override
    public byte[] serialize(ConstantPool constantPool) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(serializationSize());

        byteBuffer.put(getConstantType().getTag());
        byteBuffer.putLong(this.value);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.value);
    }
}
