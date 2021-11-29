package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestConstantPool {

    @Test
    public void testVoidSerialization() {
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPool = new byte[] {0, 1};

        assertEquals(constantPool.serializationSize(), 2);
        assertArrayEquals(constantPool.serialize(), serializedConstantPool);
    }

}