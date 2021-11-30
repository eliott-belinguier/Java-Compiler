package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents a method reference constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.2">ConstantMethodRef in ClassFile's structure</a>
 */
public class ConstantMethodRef extends Constant {

    /**
     * This {@link java.lang.reflect.Field} contains a {@link Constant} that represents
     * the class that contains the referred {@link java.lang.reflect.Method}.
     * @since 1.0
     * @see ConstantClass
     */
    private final ConstantClass ownerClass;

    /**
     * This {@link java.lang.reflect.Field} contains a {@link Constant} which represents
     * the name and type of the referred {@link java.lang.reflect.Method}.
     * @since 1.0
     * @see ConstantNameAndType
     */
    private final ConstantNameAndType nameAndType;

    /**
     * Initialize a newly created Constant object to represent the {@link java.lang.reflect.Method} reference.
     * @param ownerClass The constant of the class that owns the {@link java.lang.reflect.Method}.
     * @param nameAndType The constant that represents the name and description of the {@link java.lang.reflect.Method}.
     * @since 1.0
     * @see ConstantClass
     * @see ConstantNameAndType
     */
    public ConstantMethodRef(ConstantClass ownerClass, ConstantNameAndType nameAndType) {
        super(ConstantType.METHOD_REF);
        this.ownerClass = ownerClass;
        this.nameAndType = nameAndType;
    }

    /**
     * Initialize a newly created Constant object to represent the field reference.
     * @param ownerClass The name of the class that owns the {@link java.lang.reflect.Method}.
     * @param fieldName The {@link java.lang.reflect.Method}'s name.
     * @param fieldDescriptor The {@link java.lang.reflect.Method}'s descriptor.
     * @since 1.0
     */
    public ConstantMethodRef(String ownerClass, String fieldName, String fieldDescriptor) {
        this(new ConstantClass(ownerClass), new ConstantNameAndType(fieldName, fieldDescriptor));
    }

    /**
     * Returns the constant of the class that owns the {@link java.lang.reflect.Method}.
     * @return The constant of the class that owns the {@link java.lang.reflect.Method}.
     * @since 1.0
     * @see ConstantClass
     */
    public ConstantClass getOwnerClass() {
        return this.ownerClass;
    }

    /**
     * Returns the constant that represents the name and description of the {@link java.lang.reflect.Method}.
     * @return The constant that represents the name and description of the {@link java.lang.reflect.Method}.
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
