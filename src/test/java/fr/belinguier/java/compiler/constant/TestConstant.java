package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestConstant {

    @Test
    public void testNullType() {
        assertThrows(NullPointerException.class, () -> new Constant(null) {

        });
    }

    @Test
    public void testBasic() {
        final ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        final Constant constant = new Constant(ConstantType.UTF_8) {

        };
        assertDoesNotThrow(() -> constant.serialize(null, new DataOutputStream(arrayOutputStream)));
        assertArrayEquals(arrayOutputStream.toByteArray(), new byte[] {ConstantType.UTF_8.getTag()});
    }

    @Test
    public void testEqualsWithAnotherObject() {
        final String str = "MrCubee";
        final Constant constant = new Constant(ConstantType.UTF_8) {

        };
        assertNotEquals(constant, str);
    }

    @Test
    public void testEqualsWithAnotherConstant() {
        final Constant firstConstant = new Constant(ConstantType.STRING) {

        };
        final Constant secondConstant = new Constant(ConstantType.UTF_8) {

        };
        assertNotEquals(firstConstant, secondConstant);
    }

    @Test
    public void testEqualsSameConstant() {
        final Constant firstConstant = new Constant(ConstantType.UTF_8) {

        };
        final Constant secondConstant = new Constant(ConstantType.UTF_8) {

        };
        assertEquals(firstConstant, secondConstant);
    }

    @Test
    public void testDuplicateAllConstant() {
        final ConstantPool constantPool = new ConstantPool();
        final Constant[] constants = new Constant[] {
                new ConstantClass(TestConstant.class),
                new ConstantDouble(42),
                new ConstantFieldRef(TestConstant.class.getName(), "test", "B"),
                new ConstantFloat(42),
                new ConstantInteger(42),
                new ConstantInterfaceMethodRef(TestConstant.class.getName(), "test", "B"),
                new ConstantLong(42),
                new ConstantMethodRef(TestConstant.class.getName(), "test", "B"),
                new ConstantMethodType("B"),
                new ConstantNameAndType("test", "B"),
                new ConstantString("test"),
                new ConstantUtf8("test")
        };
        for (Constant constant : constants)
            assertTrue(constantPool.registerConstant(constant));
        for (Constant constant : constants)
            assertFalse(constantPool.registerConstant(constant));
        for (Constant constant : constantPool.getConstants())
            assertTrue(constantPool.unRegisterConstant(constant));
        assertFalse(constantPool.unRegisterConstant(null));
    }

    @Test
    public void testSerializeWithNullStream() {
        final ConstantPool constantPool = new ConstantPool();
        final Constant[] constants = new Constant[] {
                new ConstantClass(TestConstant.class),
                new ConstantDouble(42),
                new ConstantFieldRef(TestConstant.class.getName(), "test", "B"),
                new ConstantFloat(42),
                new ConstantInteger(42),
                new ConstantInterfaceMethodRef(TestConstant.class.getName(), "test", "B"),
                new ConstantLong(42),
                new ConstantMethodRef(TestConstant.class.getName(), "test", "B"),
                new ConstantMethodType("B"),
                new ConstantNameAndType("test", "B"),
                new ConstantString("test"),
                new ConstantUtf8("test")
        };
        for (Constant constant : constants)
            assertDoesNotThrow(() -> constant.serialize(constantPool, null));
    }

}
