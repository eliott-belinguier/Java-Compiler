package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantString {

    @Test
    public void testConstantWithConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantString constant = new ConstantString(new ConstantUtf8("MrCubee"));
        final byte[] serializedConstant = new byte[] {
                ConstantType.STRING.getTag(), 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.STRING);
        assertEquals(constant.getValue(), new ConstantUtf8("MrCubee"));
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithString() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantString constant = new ConstantString("MrCubee");
        final byte[] serializedConstant = new byte[] {
                ConstantType.STRING.getTag(), 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.STRING);
        assertEquals(constant.getValue(), new ConstantUtf8("MrCubee"));
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantNullUTF8() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantString constant = new ConstantString((ConstantUtf8) null);
        final byte[] serializedConstant = new byte[] {
                ConstantType.STRING.getTag(), 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.STRING);
        assertNull(constant.getValue());
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testWithNullConstantPool() {
        final ConstantString constant = new ConstantString(new ConstantUtf8("MrCubee"));

        assertEquals(constant.getConstantType(), ConstantType.STRING);
        assertEquals(constant.getValue(), new ConstantUtf8("MrCubee"));
        assertEquals(constant.serializationSize(), 3);
        assertNull(constant.serialize(null));
    }

}
