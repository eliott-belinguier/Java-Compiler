package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantInterfaceMethodRef {

    @Test
    public void testConstantWithConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantInterfaceMethodRef constant = new ConstantInterfaceMethodRef(new ConstantClass(TestConstantFieldRef.class),
                new ConstantNameAndType("testMethod", "B()"));
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.INTERFACE_METHOD_REF.getTag(), 0, 1, 0, 2
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.INTERFACE_METHOD_REF);
        assertEquals(constant.getOwnerClass(), new ConstantClass(TestConstantFieldRef.class));
        assertEquals(constant.getNameAndType(), new ConstantNameAndType("testMethod", "B()"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithName() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantInterfaceMethodRef constant = new ConstantInterfaceMethodRef(TestConstantFieldRef.class.getName(),
                "testMethod", "B()");
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.INTERFACE_METHOD_REF.getTag(), 0, 1, 0, 2
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.INTERFACE_METHOD_REF);
        assertEquals(constant.getOwnerClass(), new ConstantClass(TestConstantFieldRef.class));
        assertEquals(constant.getNameAndType(), new ConstantNameAndType("testMethod", "B()"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithNullOwnerClassConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantInterfaceMethodRef constant = new ConstantInterfaceMethodRef(null,
                new ConstantNameAndType("testMethod", "B()"));
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.INTERFACE_METHOD_REF.getTag(), 0, 0, 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.INTERFACE_METHOD_REF);
        assertNull(constant.getOwnerClass());
        assertEquals(constant.getNameAndType(), new ConstantNameAndType("testMethod", "B()"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithNullNameAndTypeConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantInterfaceMethodRef constant = new ConstantInterfaceMethodRef(new ConstantClass(TestConstantFieldRef.class),
                null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.INTERFACE_METHOD_REF.getTag(), 0, 1, 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.INTERFACE_METHOD_REF);
        assertEquals(constant.getOwnerClass(), new ConstantClass(TestConstantFieldRef.class));
        assertNull(constant.getNameAndType());
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantNullConstantPool() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantInterfaceMethodRef constant = new ConstantInterfaceMethodRef(new ConstantClass(TestConstantFieldRef.class),
                new ConstantNameAndType("testMethod", "B()"));
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.INTERFACE_METHOD_REF);
        assertEquals(constant.getOwnerClass(), new ConstantClass(TestConstantFieldRef.class));
        assertEquals(constant.getNameAndType(), new ConstantNameAndType("testMethod", "B()"));
        assertArrayEquals(serializedConstant, new byte[0]);
    }

}
