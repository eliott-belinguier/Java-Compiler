package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantMethodRef {

    @Test
    public void testConstantWithConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantMethodRef constant = new ConstantMethodRef(new ConstantClass(TestConstantFieldRef.class),
                new ConstantNameAndType("testMethod", "B()"));
        final byte[] serializedConstant = new byte[] {
                ConstantType.METHOD_REF.getTag(), 0, 1, 0, 2
        };

        assertEquals(constant.getConstantType(), ConstantType.METHOD_REF);
        assertEquals(constant.getOwnerClass(), new ConstantClass(TestConstantFieldRef.class));
        assertEquals(constant.getNameAndType(), new ConstantNameAndType("testMethod", "B()"));
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithName() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantMethodRef constant = new ConstantMethodRef(TestConstantFieldRef.class.getName(),
                "testMethod", "B()");
        final byte[] serializedConstant = new byte[] {
                ConstantType.METHOD_REF.getTag(), 0, 1, 0, 2
        };

        assertEquals(constant.getConstantType(), ConstantType.METHOD_REF);
        assertEquals(constant.getOwnerClass(), new ConstantClass(TestConstantFieldRef.class));
        assertEquals(constant.getNameAndType(), new ConstantNameAndType("testMethod", "B()"));
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithNullOwnerClassConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantMethodRef constant = new ConstantMethodRef(null,
                new ConstantNameAndType("testMethod", "B()"));
        final byte[] serializedConstant = new byte[] {
                ConstantType.METHOD_REF.getTag(), 0, 0, 0, 1
        };

        assertEquals(constant.getConstantType(), ConstantType.METHOD_REF);
        assertNull(constant.getOwnerClass());
        assertEquals(constant.getNameAndType(), new ConstantNameAndType("testMethod", "B()"));
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantWithNullNameAndTypeConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantMethodRef constant = new ConstantMethodRef(new ConstantClass(TestConstantFieldRef.class),
                null);
        final byte[] serializedConstant = new byte[] {
                ConstantType.METHOD_REF.getTag(), 0, 1, 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.METHOD_REF);
        assertEquals(constant.getOwnerClass(), new ConstantClass(TestConstantFieldRef.class));
        assertNull(constant.getNameAndType());
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantNullConstantPool() {
        final ConstantMethodRef constant = new ConstantMethodRef(new ConstantClass(TestConstantFieldRef.class),
                new ConstantNameAndType("testMethod", "B()"));

        assertEquals(constant.getConstantType(), ConstantType.METHOD_REF);
        assertEquals(constant.getOwnerClass(), new ConstantClass(TestConstantFieldRef.class));
        assertEquals(constant.getNameAndType(), new ConstantNameAndType("testMethod", "B()"));
        assertEquals(constant.serializationSize(), 5);
        assertNull(constant.serialize(null));
    }

}
