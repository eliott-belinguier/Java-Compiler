package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents a float constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.4">ConstantFloat in ClassFile's structure</a>
 */
public class ConstantFloat extends Constant {

    /**
     * This field contains the {@link Float} number, that this constant should represent.
     * @since 1.0
     */
    private final float value;

    /**
     * Initializes a newly created Constant object to represent {@link Float} number.
     * @param value The {@link Float} number that the constant should represent.
     * @since 1.0
     */
    public ConstantFloat(float value) {
        super(ConstantType.FLOAT);
        this.value = value;
    }

    /**
     * Get the {@link Float} number that the constant represents.
     * @return The {@link Float} number.
     * @since 1.0
     */
    public float getValue() {
        return this.value;
    }

    @Override
    public void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException {
        if (out == null)
            return;
        super.serialize(constantPool, out);
        out.writeFloat(this.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.value);
    }
}
