package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestConstantPool {

    @Test
    public void testVoidSerialization() {
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPool = new byte[] {0, 1};

        assertEquals(constantPool.serializationSize(), 2);
        assertArrayEquals(constantPool.serialize(), serializedConstantPool);
    }

    @Test
    public void testSerializationWithString() {
        final ConstantUtf8 constantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPool = new byte[] {0, 2,
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };

        assertEquals(constantPool.getOrRegister(constantUtf8), 1);
        assertEquals(constantPool.serializationSize(), 2 + 1 + 2 + 7);
        assertArrayEquals(constantPool.serialize(), serializedConstantPool);
    }

    @Test
    public void testSerializationWithStringAndLong() {
        final ConstantLong constantLong = new ConstantLong(4);
        final ConstantUtf8 constantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPool = new byte[] {0, 4,
                ConstantType.LONG.getTag(), 0, 0, 0, 0, 0, 0, 0, 4,
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };

        assertEquals(constantPool.getOrRegister(constantLong), 1);
        assertEquals(constantPool.getOrRegister(constantUtf8), 3);
        assertEquals(constantPool.serializationSize(), 2 + 1 + 8 + 1 + 2 + 7);
        assertArrayEquals(constantPool.serialize(), serializedConstantPool);
    }

    @Test
    public void testSerializationWithStringAndDouble() {
        final ConstantDouble constantDouble = new ConstantDouble(4);
        final ConstantUtf8 constantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPool = new byte[] {0, 4,
                ConstantType.DOUBLE.getTag(), 64, 16, 0, 0, 0, 0, 0, 0,
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };

        assertEquals(constantPool.getOrRegister(constantDouble), 1);
        assertEquals(constantPool.getOrRegister(constantUtf8), 3);
        assertEquals(constantPool.serializationSize(), 2 + 1 + 8 + 1 + 2 + 7);
        assertArrayEquals(constantPool.serialize(), serializedConstantPool);
    }

    @Test
    public void testDuplicateRegisterConstant() {
        final ConstantUtf8 firstConstantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantUtf8 secondConstantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();

        assertEquals(constantPool.getOrRegister(firstConstantUtf8), 1);
        assertFalse(constantPool.registerConstant(secondConstantUtf8));
        assertEquals(constantPool.getOrRegister(secondConstantUtf8), 1);
    }

    @Test
    public void testUnregisterAll() {
        final ConstantUtf8 constantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedVoidConstantPool = new byte[] {0, 1};

        assertEquals(constantPool.getOrRegister(constantUtf8), 1);
        constantPool.unRegisterAll();
        assertArrayEquals(constantPool.serialize(), serializedVoidConstantPool);
    }

}