package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents a double constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.5">ConstantDouble in ClassFile's structure</a>
 */
public class ConstantDouble extends Constant {

    /**
     * This field contains the {@link Double} number, that this constant should represent.
     * @since 1.0
     */
    private final double value;

    /**
     * Initializes a newly created Constant object to represent {@link Double} number.
     * @param value The {@link Double} number that the constant should represent.
     * @since 1.0
     */
    public ConstantDouble(double value) {
        super(ConstantType.DOUBLE);
        this.value = value;
    }

    /**
     * Get the {@link Double} number that the constant represents.
     * @return The {@link Double} number.
     * @since 1.0
     */
    public double getValue() {
        return this.value;
    }

    @Override
    public void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException {
        if (out == null)
            return;
        super.serialize(constantPool, out);
        out.writeDouble(this.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.value);
    }
}
