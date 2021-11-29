package fr.belinguier.java.compiler.constant;

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
     * Get the bytecode size of this {@link Constant} when it is serialized.
     * @return The size of the bytecode when this {@link Constant} is serialized.
     * @since 1.0
     */
    int serializationSize();

    /**
     * Get the bytecode of this {@link Constant}.
     * @param constantPool The registered {@link Constant} list to retrieve their reference.
     * @return This {@link Constant} serialized in bytecode.
     * @since 1.0
     */
    byte[] serialize(ConstantPool constantPool);

}
