package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantInteger {

    @Test
    public void testConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantInteger constant = new ConstantInteger(8);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.INTEGER.getTag(), 0, 0, 0, 8
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.INTEGER);
        assertEquals(constant.getValue(), 8);
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testWithNullConstantPool() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantInteger constant = new ConstantInteger(8);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.INTEGER.getTag(), 0, 0, 0, 8
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.INTEGER);
        assertEquals(constant.getValue(), 8);
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

}
