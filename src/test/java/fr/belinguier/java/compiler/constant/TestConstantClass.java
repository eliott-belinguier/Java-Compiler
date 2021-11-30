package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantClass {

    @Test
    public void testConstantWithConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantClass constant = new ConstantClass(new ConstantUtf8("MrCubee"));
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.CLASS.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertEquals(constant.getClassName(), new ConstantUtf8("MrCubee"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithName() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantClass constant = new ConstantClass("MrCubee");
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.CLASS.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertEquals(constant.getClassName(), new ConstantUtf8("MrCubee"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithClass() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantClass constant = new ConstantClass(TestConstantClass.class);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.CLASS.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertEquals(constant.getClassName(), new ConstantUtf8(TestConstantClass.class.getName()));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithNullNameConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantClass constant = new ConstantClass((ConstantUtf8) null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.CLASS.getTag(), 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertNull(constant.getClassName());
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithNullNameString() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantClass constant = new ConstantClass((String) null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.CLASS.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertEquals(constant.getClassName(), new ConstantUtf8(null));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithNullClass() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantClass constant = new ConstantClass((Class<?>) null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.CLASS.getTag(), 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertNull(constant.getClassName());
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantNullConstantPool() {
        final ConstantClass constant = new ConstantClass(new ConstantUtf8("MrCubee"));
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.CLASS);
        assertEquals(constant.getClassName(), new ConstantUtf8("MrCubee"));
        assertArrayEquals(serializedConstant, new byte[0]);
    }

}
