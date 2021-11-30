package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantLong {

    @Test
    public void testConstantLong() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantLong constant = new ConstantLong(8);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.LONG.getTag(), 0, 0, 0, 0, 0, 0, 0, 8
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.LONG);
        assertEquals(constant.getValue(), 8);
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testWithNullConstantPool() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantLong constant = new ConstantLong(8);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.LONG.getTag(), 0, 0, 0, 0, 0, 0, 0, 8
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.LONG);
        assertEquals(constant.getValue(), 8);
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

}
