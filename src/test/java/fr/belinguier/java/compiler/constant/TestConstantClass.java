package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantClass {

    @Test
    public void testConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantClass constant = new ConstantClass(new ConstantUtf8("MrCubee"));
        final byte[] serializedConstant = new byte[] {
                ConstantType.CLASS.getTag(), 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertEquals(constant.getClassName(), new ConstantUtf8("MrCubee"));
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithNullNameConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantClass constant = new ConstantClass((ConstantUtf8) null);
        final byte[] serializedConstant = new byte[] {
                ConstantType.CLASS.getTag(), 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertNull(constant.getClassName());
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithNullNameString() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantClass constant = new ConstantClass((String) null);
        final byte[] serializedConstant = new byte[] {
                ConstantType.CLASS.getTag(), 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertEquals(constant.getClassName(), new ConstantUtf8(null));
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithNullNameInConstantUTF8() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantClass constant = new ConstantClass(new ConstantUtf8(null));
        final byte[] serializedConstant = new byte[] {
                ConstantType.CLASS.getTag(), 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertEquals(constant.getClassName(), new ConstantUtf8(null));
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

}
