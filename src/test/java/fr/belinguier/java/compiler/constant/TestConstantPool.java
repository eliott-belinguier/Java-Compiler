package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantPool {

    @Test
    public void testVoidSerialization() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPoolModel = new byte[] {0, 1};
        final byte[] serializedConstantPool;

        assertDoesNotThrow(() -> constantPool.serialize(new DataOutputStream(arrayOutputStream)));
        serializedConstantPool = arrayOutputStream.toByteArray();

        assertArrayEquals(serializedConstantPool, serializedConstantPoolModel);
    }

    @Test
    public void testSerializationWithString() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantUtf8 constantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPoolModel = new byte[] {0, 2,
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };
        final byte[] serializedConstantPool;

        assertEquals(constantPool.getOrRegister(constantUtf8), 1);
        assertDoesNotThrow(() -> constantPool.serialize(new DataOutputStream(arrayOutputStream)));
        serializedConstantPool = arrayOutputStream.toByteArray();
        assertArrayEquals(serializedConstantPool, serializedConstantPoolModel);
    }

    @Test
    public void testSerializationWithStringAndLong() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantLong constantLong = new ConstantLong(4);
        final ConstantUtf8 constantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPoolModel = new byte[] {0, 4,
                ConstantType.LONG.getTag(), 0, 0, 0, 0, 0, 0, 0, 4,
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };
        final byte[] serializedConstantPool;

        assertEquals(constantPool.getOrRegister(constantLong), 1);
        assertEquals(constantPool.getOrRegister(constantUtf8), 3);
        assertDoesNotThrow(() -> constantPool.serialize(new DataOutputStream(arrayOutputStream)));
        serializedConstantPool = arrayOutputStream.toByteArray();
        assertArrayEquals(serializedConstantPool, serializedConstantPoolModel);
    }

    @Test
    public void testSerializationWithStringAndDouble() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantDouble constantDouble = new ConstantDouble(4);
        final ConstantUtf8 constantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedConstantPoolModel = new byte[] {0, 4,
                ConstantType.DOUBLE.getTag(), 64, 16, 0, 0, 0, 0, 0, 0,
                ConstantType.UTF_8.getTag(), 0, 7, 'M', 'r', 'C', 'u', 'b', 'e', 'e'
        };
        final byte[] serializedConstantPool;

        assertEquals(constantPool.getOrRegister(constantDouble), 1);
        assertEquals(constantPool.getOrRegister(constantUtf8), 3);
        assertDoesNotThrow(() -> constantPool.serialize(new DataOutputStream(arrayOutputStream)));
        serializedConstantPool = arrayOutputStream.toByteArray();
        assertArrayEquals(serializedConstantPool, serializedConstantPoolModel);
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
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantUtf8 constantUtf8 = new ConstantUtf8("MrCubee");
        final ConstantPool constantPool = new ConstantPool();
        final byte[] serializedVoidConstantPoolModel = new byte[] {0, 1};
        final byte[] serializedConstantPool;

        assertEquals(constantPool.getOrRegister(constantUtf8), 1);
        constantPool.unRegisterAll();
        assertDoesNotThrow(() -> constantPool.serialize(new DataOutputStream(arrayOutputStream)));
        serializedConstantPool = arrayOutputStream.toByteArray();

        assertArrayEquals(serializedConstantPool, serializedVoidConstantPoolModel);
    }

    @Test
    public void testRegisterConstantWithNullType() {
        final ConstantPool constantPool = new ConstantPool();

        assertFalse(constantPool.registerConstant(new Constant(ConstantType.UTF_8) {
            @Override
            public ConstantType getConstantType() {
                return null;
            }
        }));
    }

    @Test
    public void testRegisterNullConstant() {
        final ConstantPool constantPool = new ConstantPool();

        assertFalse(constantPool.registerConstant(null));
    }

}