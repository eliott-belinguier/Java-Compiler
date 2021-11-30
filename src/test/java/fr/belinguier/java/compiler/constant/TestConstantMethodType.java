package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantMethodType {
    @Test
    public void testConstantWithConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantMethodType constant = new ConstantMethodType(new ConstantUtf8("B()"));
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.METHOD_TYPE.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertEquals(constant.getDescriptor(), new ConstantUtf8("B()"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithName() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantMethodType constant = new ConstantMethodType("B()");
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.METHOD_TYPE.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertEquals(constant.getDescriptor(), new ConstantUtf8("B()"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithNullNameConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantMethodType constant = new ConstantMethodType((ConstantUtf8) null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.METHOD_TYPE.getTag(), 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertNull(constant.getDescriptor());
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithNullNameString() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantMethodType constant = new ConstantMethodType((String) null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.METHOD_TYPE.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertEquals(constant.getDescriptor(), new ConstantUtf8(null));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantNullConstantPool() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantMethodType constant = new ConstantMethodType(new ConstantUtf8("B()"));
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.METHOD_TYPE);
        assertEquals(constant.getDescriptor(), new ConstantUtf8("B()"));
        assertArrayEquals(serializedConstant, new byte[0]);
    }

}
