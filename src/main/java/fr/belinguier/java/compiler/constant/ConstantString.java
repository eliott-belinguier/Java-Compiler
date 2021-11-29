package fr.belinguier.java.compiler.constant;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * This Constant class represents a string constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 */
public class ConstantString extends Constant {

    /**
     * This {@link java.lang.reflect.Field} contains the UTF-8 bytecode string as a {@link Constant}.
     * @since 1.0
     * @see ConstantUtf8
     */
    private final ConstantUtf8 value;

    /**
     * Initializes a newly created {@link Constant} object to represent a {@link String}.
     * @param value The UTF-8 bytecode string to represent as a {@link Constant}.
     * @since 1.0
     * @see ConstantUtf8
     */
    public ConstantString(ConstantUtf8 value) {
        super(ConstantType.STRING);
        this.value = value;
    }

    /**
     * Initializes a newly created {@link Constant} object to represent a {@link String}.
     * @param value The {@link String} to represent.
     * @since 1.0
     */
    public ConstantString(String value) {
        this(new ConstantUtf8(value));
    }

    /**
     * Get the UTF-8 bytecode string, as a {@link Constant}.
     * @return The UTF-8 bytecode string, as a {@link Constant}.
     * @since 1.0
     * @see ConstantUtf8
     */
    public ConstantUtf8 getValue() {
        return this.value;
    }

    @Override
    public int serializationSize() {
        return super.serializationSize() + 2;
    }

    @Override
    public byte[] serialize(ConstantPool constantPool) {
        final ByteBuffer byteBuffer;
        final short stringIndex;

        if (constantPool == null)
            return null;
        byteBuffer = ByteBuffer.allocate(serializationSize());
        stringIndex = constantPool.getOrRegister(this.value);
        byteBuffer.put((byte) getConstantType().getTag());
        byteBuffer.putShort(stringIndex);
        return byteBuffer.array();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.value);
    }
}
