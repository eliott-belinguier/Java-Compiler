package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantLong {

    @Test
    public void testConstantLong() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantLong constant = new ConstantLong(8);
        final byte[] serializedConstant = new byte[] {
                ConstantType.LONG.getTag(), 0, 0, 0, 0, 0, 0, 0, 8
        };

        assertEquals(constant.getConstantType(), ConstantType.LONG);
        assertEquals(constant.getValue(), 8);
        assertEquals(constant.serializationSize(), 9);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantLongWithNullConstantPool() {
        final ConstantLong constant = new ConstantLong(8);
        final byte[] serializedConstant = new byte[] {
                ConstantType.LONG.getTag(), 0, 0, 0, 0, 0, 0, 0, 8
        };

        assertEquals(constant.getConstantType(), ConstantType.LONG);
        assertEquals(constant.getValue(), 8);
        assertEquals(constant.serializationSize(), 9);
        assertArrayEquals(constant.serialize(null), serializedConstant);
    }

}
