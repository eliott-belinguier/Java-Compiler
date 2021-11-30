package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantFloat {

    @Test
    public void testConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantFloat constant = new ConstantFloat(8);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.FLOAT.getTag(), 65, 0, 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.FLOAT);
        assertEquals(constant.getValue(), 8);
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testWithNullConstantPool() {
        final ConstantFloat constant = new ConstantFloat(8);
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.FLOAT.getTag(), 65, 0, 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.FLOAT);
        assertEquals(constant.getValue(), 8);
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

}
