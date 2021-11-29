package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConstantInteger {

    @Test
    public void testConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantInteger constant = new ConstantInteger(8);
        final byte[] serializedConstant = new byte[] {
                ConstantType.INTEGER.getTag(), 0, 0, 0, 8
        };

        assertEquals(constant.getConstantType(), ConstantType.INTEGER);
        assertEquals(constant.getValue(), 8);
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testWithNullConstantPool() {
        final ConstantInteger constant = new ConstantInteger(8);
        final byte[] serializedConstant = new byte[] {
                ConstantType.INTEGER.getTag(), 0, 0, 0, 8
        };

        assertEquals(constant.getConstantType(), ConstantType.INTEGER);
        assertEquals(constant.getValue(), 8);
        assertEquals(constant.serializationSize(), 5);
        assertArrayEquals(constant.serialize(null), serializedConstant);
    }

}
