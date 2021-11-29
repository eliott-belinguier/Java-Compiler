package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantMethodType {
    @Test
    public void testConstantWithConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantMethodType constant = new ConstantMethodType(new ConstantUtf8("B()"));
        final byte[] serializedConstant = new byte[] {
                ConstantType.METHOD_TYPE.getTag(), 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertEquals(constant.getDescriptor(), new ConstantUtf8("B()"));
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithName() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantMethodType constant = new ConstantMethodType("B()");
        final byte[] serializedConstant = new byte[] {
                ConstantType.METHOD_TYPE.getTag(), 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertEquals(constant.getDescriptor(), new ConstantUtf8("B()"));
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithNullNameConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantMethodType constant = new ConstantMethodType((ConstantUtf8) null);
        final byte[] serializedConstant = new byte[] {
                ConstantType.METHOD_TYPE.getTag(), 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertNull(constant.getDescriptor());
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithNullNameString() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantMethodType constant = new ConstantMethodType((String) null);
        final byte[] serializedConstant = new byte[] {
                ConstantType.METHOD_TYPE.getTag(), 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertEquals(constant.getDescriptor(), new ConstantUtf8(null));
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantNullConstantPool() {
        final ConstantMethodType constant = new ConstantMethodType(new ConstantUtf8("B()"));

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertEquals(constant.getDescriptor(), new ConstantUtf8("B()"));
        assertEquals(constant.serializationSize(), 3);
        assertNull(constant.serialize(null));
    }

}
