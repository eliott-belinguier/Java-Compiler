package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents a class constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/sperializationSize(this);ecs/jvms/se7/html/jvms-4.html#jvms-4.4.1">ConstantClass in ClassFile's structure</a>
 */
public class ConstantClass extends Constant {

    /**
     * This field contains the {@link Class}'s name as a constant.
     * @since 1.0
     * @see ConstantUtf8
     */
    private final ConstantUtf8 className;

    /**
     * Initializes a newly created Constant object to represent {@link Class}.
     * @param className The {@link Class}'s name to represent as a constant.
     * @since 1.0
     */
    public ConstantClass(ConstantUtf8 className) {
        super(ConstantType.CLASS);
        this.className = className;
    }

    /**
     * Initializes a newly created Constant object to represent {@link Class}.
     * @param className The {@link Class}'s name to represent.
     * @since 1.0
     */
    public ConstantClass(String className) {
        this(new ConstantUtf8(className));
    }

    /**
     * Initializes a newly created Constant object to represent {@link Class}.
     * @param clazz The {@link Class} to represent.
     * @since 1.0
     */
    public ConstantClass(Class<?> clazz) {
        this(clazz != null ? new ConstantUtf8(clazz.getName()) : null);
    }

    /**
     * Get the represented class's name, as a constant.
     * @return The class's name, as a constant.
     * @since 1.0
     * @see ConstantUtf8
     */
    public ConstantUtf8 getClassName() {
        return this.className;
    }

    @Override
    public void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException {
        if (constantPool == null || out == null)
            return;
        super.serialize(constantPool, out);
        out.writeShort(constantPool.getOrRegister(this.className));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.className);
    }
}
