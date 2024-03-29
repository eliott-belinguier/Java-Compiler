package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents method type constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.9">ConstantMethodType in ClassFile's structure</a>
 */
public class ConstantMethodType extends Constant {

    /**
     * This field contains a constant that represents the description of a {@link java.lang.reflect.Method}.
     * @since 1.0
     * @see ConstantUtf8
     */
    private final ConstantUtf8 descriptor;

    /**
     * Initializes a newly created Constant object to represent the description of a {@link java.lang.reflect.Method}.
     * @param descriptor Description of the method to be as a {@link ConstantUtf8}.
     * @since 1.0
     * @see ConstantUtf8
     */
    public ConstantMethodType(ConstantUtf8 descriptor) {
        super(ConstantType.METHOD_TYPE);
        this.descriptor = descriptor;
    }

    /**
     * Initializes a newly created Constant object to represent the description of a {@link java.lang.reflect.Method}.
     * @param descriptor Description of the {@link java.lang.reflect.Method} to be.
     * @since 1.0
     */
    public ConstantMethodType(String descriptor) {
        this(new ConstantUtf8(descriptor));
    }

    /**
     * Get the description of the {@link java.lang.reflect.Method} represented.
     * @return The description of the {@link java.lang.reflect.Method} as a constant.
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
        out.writeShort(constantPool.getOrRegister(this.descriptor));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.descriptor);
    }
}
