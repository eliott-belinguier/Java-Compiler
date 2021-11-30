package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents a field reference constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.2">ConstantField in ClassFile's structure</a>
 */
public class ConstantFieldRef extends Constant {

    /**
     * This {@link java.lang.reflect.Field} contains a {@link Constant} that represents
     * the class that contains the referred {@link java.lang.reflect.Field}.
     * @since 1.0
     * @see ConstantClass
     */
    private final ConstantClass ownerClass;

    /**
     * This {@link java.lang.reflect.Field} contains a {@link Constant} which represents
     * the name and type of the referred {@link java.lang.reflect.Field}.
     * @since 1.0
     * @see ConstantNameAndType
     */
    private final ConstantNameAndType nameAndType;

    /**
     * Initialize a newly created Constant object to represent the field reference.
     * @param ownerClass The constant of the class that owns the field.
     * @param nameAndType The constant that represents the name and description of the field.
     * @since 1.0
     * @see ConstantClass
     * @see ConstantNameAndType
     */
    public ConstantFieldRef(ConstantClass ownerClass, ConstantNameAndType nameAndType) {
        super(ConstantType.FIELD_REF);
        this.ownerClass = ownerClass;
        this.nameAndType = nameAndType;
    }

    /**
     * Initialize a newly created Constant object to represent the field reference.
     * @param ownerClass The name of the class that owns the field.
     * @param fieldName The field's name.
     * @param fieldDescriptor The field's descriptor.
     * @since 1.0
     */
    public ConstantFieldRef(String ownerClass, String fieldName, String fieldDescriptor) {
        this(new ConstantClass(ownerClass), new ConstantNameAndType(fieldName, fieldDescriptor));
    }

    /**
     * Returns the constant of the class that owns the field.
     * @return The constant of the class that owns the field.
     * @since 1.0
     * @see ConstantClass
     */
    public ConstantClass getOwnerClass() {
        return this.ownerClass;
    }

    /**
     * Returns the constant that represents the name and description of the field.
     * @return The constant that represents the name and description of the field.
     * @since 1.0
     * @see ConstantNameAndType
     */
    public ConstantNameAndType getNameAndType() {
        return this.nameAndType;
    }

    @Override
    public void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException {
        if (constantPool == null || out == null)
            return;
        super.serialize(constantPool, out);
        out.writeShort(constantPool.getOrRegister(this.ownerClass));
        out.writeShort(constantPool.getOrRegister(this.nameAndType));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.ownerClass, this.nameAndType);
    }
}
