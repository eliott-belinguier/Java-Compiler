package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents name and type constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.6">ConstantNameAndType in ClassFile's structure</a>
 */
public class ConstantNameAndType extends Constant {

    /**
     * This field contains a constant that represents the name of a {@link java.lang.reflect.Field} or a {@link java.lang.reflect.Method}.
     * @since 1.0
     * @see ConstantUtf8
     */
    private final ConstantUtf8 name;

    /**
     * This field contains a constant that represents the description of a {@link java.lang.reflect.Field} or a {@link java.lang.reflect.Method}.
     * @since 1.0
     * @see ConstantUtf8
     */
    private final ConstantUtf8 descriptor;

    /**
     * Initializes a newly created Constant object to represent the name and description of a {@link java.lang.reflect.Field} or a {@link java.lang.reflect.Method}.
     * @param name Name of the field or the method to be represented, as a {@link ConstantUtf8}.
     * @param descriptor Description of the field or the method to be as a {@link ConstantUtf8}.
     * @since 1.0
     */
    public ConstantNameAndType(ConstantUtf8 name, ConstantUtf8 descriptor) {
        super(ConstantType.NAME_AND_TYPE);
        this.name = name;
        this.descriptor = descriptor;
    }

    /**
     * Initializes a newly created Constant object to represent the name and description of a {@link java.lang.reflect.Field} or a {@link java.lang.reflect.Method}.
     * @param name Name of the {@link java.lang.reflect.Field} or the {@link java.lang.reflect.Method} to be represented.
     * @param descriptor Description of the {@link java.lang.reflect.Field} or the {@link java.lang.reflect.Method} to be.
     * @since 1.0
     */
    public ConstantNameAndType(String name, String descriptor) {
        this(new ConstantUtf8(name), new ConstantUtf8(descriptor));
    }

    /**
     * Get the name of the {@link java.lang.reflect.Field} or {@link java.lang.reflect.Method} represented.
     * @return The name of the {@link java.lang.reflect.Field} or {@link java.lang.reflect.Method} as a constant.
     * @since 1.0
     */
    public ConstantUtf8 getName() {
        return this.name;
    }

    /**
     * Get the description of the {@link java.lang.reflect.Field} or {@link java.lang.reflect.Method} represented.
     * @return The description of the {@link java.lang.reflect.Field} or {@link java.lang.reflect.Method} as a constant.
     * @since 1.0
     */
    public ConstantUtf8 getDescriptor() {
        return this.descriptor;
    }

    @Override
    public void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException {
        if (constantPool == null || out == null)
            return;
        super.serialize(constantPool, out);
        out.writeShort(constantPool.getOrRegister(this.name));
        out.writeShort(constantPool.getOrRegister(this.descriptor));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getConstantType(), this.name, this.descriptor);
    }
}
