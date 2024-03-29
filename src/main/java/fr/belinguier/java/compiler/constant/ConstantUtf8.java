package fr.belinguier.java.compiler.constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * This Constant class represents a UTF-8 string constant in the structure of the ClassFile.
 * @author Eliott Belinguier
 * @since 1.0
 * @version 1.0
 * @see Constant
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.4.7">ConstantUTF8 in ClassFile's structure</a>
 */
public class ConstantUtf8 extends Constant {

    /**
     * This field contains the string encoded in a byte array, which this constant should represent.
     * @since 1.0
     */
    private byte[] bytes;

    /**
     * Initializes a newly created Constant object to represent {@link String}.
     * @param str The {@link String} that the constant should represent.
     * @since 1.0
     */
    public ConstantUtf8(String str) {
        super(ConstantType.UTF_8);
        if (str != null)
            this.bytes = str.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decode the byte array into a {@link String} object that the constant represents.
     * @return Decoded {@link String}.
     * @since 1.0
     */
    public String getString() {
        if (this.bytes == null)
            return null;
        return new String(this.bytes, StandardCharsets.UTF_8);
    }

    /**
     * Returns the length of this {@link String}. The length is equal to the number of byte in the string.
     * @return The length of this {@link String}.
     * @since 1.0
     */
    public int length() {
        return (this.bytes != null) ? this.bytes.length : 0;
    }

    @Override
    public void serialize(final ConstantPool constantPool, final DataOutputStream out) throws IOException {
        final int byteArrayLength = length();

        if (out == null)
            return;
        super.serialize(constantPool, out);
        out.writeShort((short) byteArrayLength);
        if (this.bytes != null)
            out.write(this.bytes, 0, byteArrayLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConstantType(), getString());
    }
}
