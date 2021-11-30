package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents a basic constantin the structure of the ClassFile.
 * Java Virtual Machine instructions do not rely on the run-time layout of classes, interfaces, class instances, or arrays.
 * Instead, instructions refer to symbolic information in the {@link ConstantPool} table.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see ConstantPool
 * @see ConstantType
 * @see ConstantSerializable
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4">ClassFile Structure's constant</a>
 */
public abstract class Constant implements ConstantSerializable {

    /**
     * This {@link java.lang.reflect.Field} defines the type of the constant to represent.<p>
     * This will allow it to be defined in the bytecode once serialized.
     * @since 1.0
     */
    private final ConstantType constantType;

    /**
     * Initializes a newly created Constant object so that it represents the type given to it.
     * @param type The type that the newly created Constant object should represent.
     * @since 1.0
     */
    protected Constant(ConstantType type) {
        if (type == null)
            throw new NullPointerException("Constant must have a type.");
        this.constantType = type;
    }

    /**
     * Get the type of the constant.<p>
     * This will allow it to be defined in the bytecode once serialized.
     * @return The constant type.
     * @since 1.0
     */
    public ConstantType getConstantType() {
        return this.constantType;
    }

    @Override
    public void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException {
        if (out == null)
            return;
        out.writeByte(constantType.getTag());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Constant) && obj.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.constantType);
    }

}
