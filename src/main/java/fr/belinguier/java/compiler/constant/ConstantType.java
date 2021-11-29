package fr.belinguier.java.compiler.constant;

/**
 * Enumeration of all constant types that can be used in the class file structure.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see ConstantPool
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4">Class File Structure's constant</a>
 */
public enum ConstantType {

    CLASS((byte) 7),
    FIELD_REF((byte) 9),
    METHOD_REF((byte) 10),
    INTERFACE_METHOD_REF((byte) 11),
    STRING((byte) 8),
    INTEGER((byte) 3),
    FLOAT((byte) 4),
    LONG((byte) 5),
    DOUBLE((byte) 6),
    NAME_AND_TYPE((byte) 12),
    UTF_8((byte) 1),
    METHOD_HANDLE((byte) 15),
    METHOD_TYPE((byte) 16),
    INVOKE_DYNAMIC((byte) 18);

    /**
     * This {@link java.lang.reflect.Field} contains the Tag corresponding to the selected type.<p>
     * This value will be useful for serialization,
     * writing this value in the first byte of the byte array to define the next type of constant.
     * @since 1.0
     */
    private final byte tag;

    ConstantType(byte tag) {
        this.tag = tag;
    }

    /**
     * Returns the tag value of the desired constant type.
     * @return Tag value of the desired constant type.
     * @since 1.0
     */
    public byte getTag() {
        return this.tag;
    }

}
