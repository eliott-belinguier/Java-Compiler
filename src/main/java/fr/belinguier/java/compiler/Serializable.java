package fr.belinguier.java.compiler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 */
public interface Serializable {

    /**
     * This function allows you to serialize an object in the given byte stream.
     * @param out The byte stream.
     * @since 1.0
     * @see DataOutputStream
     * @see ByteArrayOutputStream
     */
    void serialize(final DataOutputStream out) throws IOException;

    /**
     * Get the bytecode of this {@link Object}.
     * @return This {@link Object} serialized in bytecode.
     * @since 1.0
     */
    default byte[] serialize() {
        final ByteArrayOutputStream arrayOutputStream;

        arrayOutputStream = new ByteArrayOutputStream();
        try {
            serialize(new DataOutputStream(arrayOutputStream));
        } catch (IOException ignored) {
            return null;
        }
        return arrayOutputStream.toByteArray();
    }

}
