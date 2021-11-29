package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConstantDouble {

    @Test
    public void testConstantDouble() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantDouble constant = new ConstantDouble(8);
        final byte[] serializedConstant = new byte[] {
                ConstantType.DOUBLE.getTag(), 64, 32, 0, 0, 0, 0, 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.DOUBLE);
        assertEquals(constant.getValue(), 8);
        assertEquals(constant.serializationSize(), 9);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantDoubleWithNullConstantPool() {
        final ConstantDouble constant = new ConstantDouble(8);
        final byte[] serializedConstant = new byte[] {
                ConstantType.DOUBLE.getTag(), 64, 32, 0, 0, 0, 0, 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.DOUBLE);
        assertEquals(constant.getValue(), 8);
        assertEquals(constant.serializationSize(), 9);
        assertArrayEquals(constant.serialize(null
        ), serializedConstant);
    }

}
