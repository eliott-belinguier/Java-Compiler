package fr.belinguier.java.compiler;

/**
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 */
public interface Serializable {

    /**
     * Get the bytecode size of this {@link Object} when it is serialized.
     * @return The size of the bytecode when this {@link Object} is serialized.
     * @since 1.0
     */
    int serializationSize();

    /**
     * Get the bytecode of this {@link Object}.
     * @return This {@link Object} serialized in bytecode.
     */
    byte[] serialize();

}
