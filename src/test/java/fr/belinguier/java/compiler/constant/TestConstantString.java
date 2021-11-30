package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantString {

    @Test
    public void testConstantWithConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantString constant = new ConstantString(new ConstantUtf8("MrCubee"));
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.STRING.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.STRING);
        assertEquals(constant.getValue(), new ConstantUtf8("MrCubee"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantWithString() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantString constant = new ConstantString("MrCubee");
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.STRING.getTag(), 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.STRING);
        assertEquals(constant.getValue(), new ConstantUtf8("MrCubee"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantNullUTF8() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantString constant = new ConstantString((ConstantUtf8) null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.STRING.getTag(), 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.STRING);
        assertNull(constant.getValue());
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testWithNullConstantPool() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantString constant = new ConstantString(new ConstantUtf8("MrCubee"));
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.STRING);
        assertEquals(constant.getValue(), new ConstantUtf8("MrCubee"));
        assertArrayEquals(serializedConstant, new byte[0]);
    }

}
