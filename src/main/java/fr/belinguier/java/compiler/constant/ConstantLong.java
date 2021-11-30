package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * This Constant class represents a long constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.5">ConstantLong in ClassFile's structure</a>
 */
public class ConstantLong extends Constant {

    /**
     * This field contains the {@link Long} number, that this constant should represent.
     * @since 1.0
     */
    private final long value;

    /**
     * Initializes a newly created Constant object to represent {@link Long} number.
     * @param value The {@link Long} number that the constant should represent.
     * @since 1.0
     */
    public ConstantLong(long value) {
        super(ConstantType.LONG);
        this.value = value;
    }

    /**
     * Get the {@link Long} number that the constant represents.
     * @return The {@link Long} number.
     * @since 1.0
     */
    public long getValue() {
        return this.value;
    }

    @Override
    public void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException {
        if (out == null)
            return;
        super.serialize(constantPool, out);
        out.writeLong(this.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), this.value);
    }
}
