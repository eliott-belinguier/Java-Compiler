package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstantNameAndType {

    @Test
    public void testConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantNameAndType constant = new ConstantNameAndType(new ConstantUtf8("Name"),
                new ConstantUtf8("Descriptor"));
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.NAME_AND_TYPE.getTag(), 0, 1, 0, 2
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.NAME_AND_TYPE);
        assertEquals(constant.getName(), new ConstantUtf8("Name"));
        assertEquals(constant.getDescriptor(), new ConstantUtf8("Descriptor"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantNullName() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantNameAndType constant = new ConstantNameAndType(null,
                new ConstantUtf8("Descriptor"));
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.NAME_AND_TYPE.getTag(), 0, 0, 0, 1
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.NAME_AND_TYPE);
        assertNull(constant.getName());
        assertEquals(constant.getDescriptor(), new ConstantUtf8("Descriptor"));
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantNullDescriptor() {
        final ConstantPool constantPool = new ConstantPool();
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantNameAndType constant = new ConstantNameAndType(new ConstantUtf8("Name"), null);
        final byte[] serializedConstantModel = new byte[] {
                ConstantType.NAME_AND_TYPE.getTag(), 0, 1, 0, 0
        };
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(constantPool, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.NAME_AND_TYPE);
        assertEquals(constant.getName(), new ConstantUtf8("Name"));
        assertNull(constant.getDescriptor());
        assertArrayEquals(serializedConstant, serializedConstantModel);
    }

    @Test
    public void testConstantNullConstantPool() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final ConstantNameAndType constant = new ConstantNameAndType(new ConstantUtf8("Name"),
                new ConstantUtf8("Descriptor"));
        final byte[] serializedConstant;

        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        serializedConstant = arrayOutputStream.toByteArray();

        assertEquals(constant.getConstantType(), ConstantType.NAME_AND_TYPE);
        assertEquals(constant.getName(), new ConstantUtf8("Name"));
        assertEquals(constant.getDescriptor(), new ConstantUtf8("Descriptor"));
        assertArrayEquals(serializedConstant, new byte[0]);
    }
}
