package fr.belinguier.java.compiler.constant;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Implementing this interface allows a {@link Constant}
 * to be serialized in bytecode compatible with the <a href="https://en.wikipedia.org/wiki/Java_virtual_machine">Java Virtual Machine</a>.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see ConstantPool
 */
public interface ConstantSerializable {

    /**
     * This function allows you to serialize a constant in the given byte stream.
     * @param out The byte stream.
     * @since 1.0
     * @see DataOutputStream
     * @see ByteArrayOutputStream
     */
    void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException;

    /**
     * Get the bytecode of this {@link Constant}.
     * @return This {@link Constant} serialized in bytecode.
     * @since 1.0
     */
    default byte[] serialize(final ConstantPool constantPool) {
        final ByteArrayOutputStream arrayOutputStream;

        if (constantPool == null)
            return null;
        arrayOutputStream = new ByteArrayOutputStream();
        try {
            serialize(constantPool, new DataOutputStream(arrayOutputStream));
        } catch (IOException ignored) {
            return null;
        }
        return arrayOutputStream.toByteArray();
    }

}
