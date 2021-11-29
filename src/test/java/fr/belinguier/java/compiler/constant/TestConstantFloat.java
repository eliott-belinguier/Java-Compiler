package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConstantFloat {

    @Test
    public void testConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantFloat constant = new ConstantFloat(8);
        final byte[] serializedConstant = new byte[] {
                ConstantType.FLOAT.getTag(), 65, 0, 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.FLOAT);
        assertEquals(constant.getValue(), 8);
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testWithNullConstantPool() {
        final ConstantFloat constant = new ConstantFloat(8);
        final byte[] serializedConstant = new byte[] {
                ConstantType.FLOAT.getTag(), 65, 0, 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.FLOAT);
        assertEquals(constant.getValue(), 8);
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(null), serializedConstant);
    }

}
