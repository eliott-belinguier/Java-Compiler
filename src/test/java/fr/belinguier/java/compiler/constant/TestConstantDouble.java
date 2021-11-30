package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantDouble {

    @Test
    public void testConstantDouble() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantDouble constant = new ConstantDouble(8);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.DOUBLE.getTag(), 64, 32, 0, 0, 0, 0, 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.DOUBLE);
        assertEquals(constant.getValue(), 8);
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantDoubleWithNullConstantPool() {
        final ConstantDouble constant = new ConstantDouble(8);
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.DOUBLE.getTag(), 64, 32, 0, 0, 0, 0, 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.DOUBLE);
        assertEquals(constant.getValue(), 8);
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

}
