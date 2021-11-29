package fr.belinguier.java.compiler.constant;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * This ConstantPool class represents the pool of constants in ClassFile structure.<p>
 * The constant pool is a table of structures representing various string constants, class and interface names,
 * field names, and other constants that are referred to within the ClassFile structure and its substructures.
 * The format of each constant_pool table entry is indicated by its first "tag" byte.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see ConstantType
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4">ClassFile Structure's Constant Pool</a>
 */
public class ConstantPool implements Serializable, Iterable<Constant> {

    /**
     * List of registered constants.
     * @since 1.0
     */
    private final LinkedHashSet<Constant> constants;

    /**
     * This field saves the offset produced by {@link ConstantLong} and {@link ConstantDouble}.<p>
     * All 8-byte constants take up two entries in the ConstantPool table of the ClassFile.
     * If a {@link ConstantLong} or {@link ConstantDouble} structure is the item in the ConstantPool table at index n,
     * then the next usable item in the pool is located at index n+2.
     * The constant_pool index n+1 must be valid but is considered unusable.
     * @since 1.0
     * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.5">Constant Long and Double in ClassFile Structure's Constant Pool</a>
     */
    private int offset;

    /**
     * Constructs an empty constants pool.
     * @since 1.0
     */
    public ConstantPool() {
        this.constants = new LinkedHashSet<Constant>();
        this.offset = 0;
    }

    /**
     * Returns the array of registered constants with no offset.
     * @return The array of registered constants.
     * @since 1.0
     */
    public Constant[] getConstants() {
        return this.constants.toArray(new Constant[0]);
    }

    /**
     * Find the index of a constant while respecting the offset.
     * @param constant The constant sought.
     * @return The index in the constant pool array.
     * @since 1.0
     * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.5">Constant Long and Double in ClassFile Structure's Constant Pool</a>
     */
    public short indexOf(Constant constant) {
        int targetHashCode;
        Constant[] constants;
        ConstantType constantType;
        int offset = 0;

        if (constant == null)
            return 0;
        targetHashCode = constant.hashCode();
        constants = getConstants();
        for (int i = 0; i < constants.length; i++) {
            constantType = constants[i].getConstantType();
            if (constants[i].hashCode() == targetHashCode)
                return (short) (i + offset + 1);
            if (constantType == ConstantType.DOUBLE || constantType == ConstantType.LONG)
                ++offset;
        }
        return 0;
    }

    /**
     * Register the desired constant in the constant pool.
     * @param constant The constant to register.
     * @return true if the constant has been registered, otherwise false if the constant is already registered or null.
     * @since 1.0
     */
    public boolean registerConstant(Constant constant) {
        ConstantType constantType;

        if (constant == null)
            return false;
        constantType = constant.getConstantType();
        if (constantType == null)
            return false;
        if (this.constants.add(constant)) {
            if (constantType == ConstantType.DOUBLE || constantType == ConstantType.LONG)
                ++this.offset;
            return true;
        }
        return false;
    }

    /**
     * Unregister the desired constant from the constant pool.
     * @param constant The constant to unregister.
     * @return true if the constant was successfully unregistered otherwise false if the constant was not registered or null.
     * @since 1.0
     */
    public boolean unRegisterConstant(Constant constant) {
        if (constant == null)
            return false;
        return this.constants.remove(constant);
    }

    /**
     * Register the constant if not, otherwise get its index.
     * @param constant The constant to register/sought.
     * @return The index of the constant in the array if it has been registered or already exists, otherwise returns 0.
     * @since 1.0
     */
    public short getOrRegister(Constant constant) {
        short index;

        if (constant == null)
            return 0;
        index = (short) (this.constants.size() + this.offset + 1);
        if (!registerConstant(constant))
            return indexOf(constant);
        return index;
    }

    /**
     * Unregister all constants in the constant pool.
     * @since 1.0
     */
    public void unRegisterAll() {
        this.constants.clear();
        this.offset = 0;
    }

    @Override
    public Iterator<Constant> iterator() {
        return Collections.unmodifiableSet(this.constants).iterator();
    }

    @Override
    public int serializationSize() {
        int total = 2;

        for (Constant constant : this.constants)
            total += constant.serializationSize();
        return total;
    }

    @Override
    public byte[] serialize() {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(serializationSize());
        byte[] serializedConstant;

        byteBuffer.putShort((short) (this.constants.size() + offset + 1));
        for (Constant constant : this.constants) {
            serializedConstant = constant.serialize(this);
            if (serializedConstant != null)
                byteBuffer.put(serializedConstant);
        }
        return byteBuffer.array();
    }

}
