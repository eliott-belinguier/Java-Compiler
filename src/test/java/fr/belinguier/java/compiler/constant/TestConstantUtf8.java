package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestConstantUtf8 {

    @Test
    public void testConstantUTF8Null() {
        final ConstantPool constantPool = new ConstantPool();
        final ConstantUtf8 constant = new ConstantUtf8(null);
        final byte[] serializedConstant = new byte[] {
                ConstantType.UTF_8.getTag(), 0, 0
        };

        assertEquals(constant.getConstantType(), ConstantType.UTF_8);
        assertEquals(constant.length(), 0);
        assertNull(constant.getString());
        assertEquals(constant.serializationSize(), 3);
        assertArrayEquals(constant.serialize(constantPool), serializedConstant);
    }

    @Test
    public void testConstantUTF8WithNullConstantPool() {
        final ConstantUtf8 constant = new ConstantUtf8("MrCubee");
        final byte[] serializedConstant = new byte[] {
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };

        assertEquals(constant.getConstantType(), ConstantType.UTF_8);
        assertEquals(constant.length(), 7);
        assertEquals(constant.getString(), "MrCubee");
        assertEquals(constant.serializationSize(), 10);
        assertArrayEquals(constant.serialize(null), serializedConstant);
    }

}
