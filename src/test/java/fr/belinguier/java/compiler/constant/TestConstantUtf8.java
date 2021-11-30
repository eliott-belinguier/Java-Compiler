package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestConstantUtf8 {

    @Test
    public void testConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantUtf8 constant = new ConstantUtf8("MrCubee");
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.UTF_8);
        assertEquals(constant.length(), 7);
        assertEquals(constant.getString(), "MrCubee");
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantNull() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantUtf8 constant = new ConstantUtf8(null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.UTF_8.getTag(), 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.UTF_8);
        assertEquals(constant.length(), 0);
        assertNull(constant.getString());
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testWithNullConstantPool() {
        final ConstantUtf8 constant = new ConstantUtf8("MrCubee");
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.UTF_8);
        assertEquals(constant.length(), 7);
        assertEquals(constant.getString(), "MrCubee");
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }
}
