package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantNameAndType {

    @Test
    public void testConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantNameAndType constant = new ConstantNameAndType(new ConstantUtf8("Name"),
                new ConstantUtf8("Descriptor"));
        final byte[] serializedConstant = new byte[] {
                ConstantType.NAME_AND_TYPE.getTag(), 0, 1, 0, 2
        };

        assertEquals(constant.getConstantType(), ConstantType.NAME_AND_TYPE);
        assertEquals(constant.getName(), new ConstantUtf8("Name"));
        assertEquals(constant.getDescriptor(), new ConstantUtf8("Descriptor"));
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantNullName() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantNameAndType constant = new ConstantNameAndType(null,
                new ConstantUtf8("Descriptor"));
        final byte[] serializedConstant = new byte[] {
                ConstantType.NAME_AND_TYPE.getTag(), 0, 0, 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.NAME_AND_TYPE);
        assertNull(constant.getName());
        assertEquals(constant.getDescriptor(), new ConstantUtf8("Descriptor"));
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantNullDescriptor() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantNameAndType constant = new ConstantNameAndType(new ConstantUtf8("Name"), null);
        final byte[] serializedConstant = new byte[] {
                ConstantType.NAME_AND_TYPE.getTag(), 0, 1, 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.NAME_AND_TYPE);
        assertEquals(constant.getName(), new ConstantUtf8("Name"));
        assertNull(constant.getDescriptor());
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantNullConstantPool() {
        final ConstantNameAndType constant = new ConstantNameAndType(new ConstantUtf8("Name"),
                new ConstantUtf8("Descriptor"));

        assertEquals(constant.getConstantType(), ConstantType.NAME_AND_TYPE);
        assertEquals(constant.getName(), new ConstantUtf8("Name"));
        assertEquals(constant.getDescriptor(), new ConstantUtf8("Descriptor"));
        assertEquals(constant.serializationSize(), 5);
        assertNull(constant.serialize(null));
    }
}
