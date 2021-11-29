package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestConstant {

    @Test
    public void testNullType() {
        assertThrows(NullPointerException.class, () -> new Constant(null) {
            @Override
            public byte[] serialize(ConstantPool constantPool) {
                return null;
            }
        });
    }

    @Test
    public void testBasic() {
        final Constant constant = new Constant(ConstantType.UTF_8) {
            @Override
            public byte[] serialize(ConstantPool constantPool) {
                return null;
            }
        };
        assertEquals(constant.serializationSize(), 1);
    }

    @Test
    public void testEqualsWithAnotherObject() {
        final String str = "MrCubee";
        final Constant constant = new Constant(ConstantType.UTF_8) {
            @Override
            public byte[] serialize(ConstantPool constantPool) {
                return null;
            }
        };
        assertFalse(constant.equals(str));
    }

    @Test
    public void testEqualsWithAnotherConstant() {
        final Constant firstConstant = new Constant(ConstantType.STRING) {
            @Override
            public byte[] serialize(ConstantPool constantPool) {
                return null;
            }
        };
        final Constant secondConstant = new Constant(ConstantType.UTF_8) {
            @Override
            public byte[] serialize(ConstantPool constantPool) {
                return null;
            }
        };
        assertFalse(firstConstant.equals(secondConstant));
    }

    @Test
    public void testEqualsSameConstant() {
        final Constant firstConstant = new Constant(ConstantType.UTF_8) {
            @Override
            public byte[] serialize(ConstantPool constantPool) {
                return null;
            }
        };
        final Constant secondConstant = new Constant(ConstantType.UTF_8) {
            @Override
            public byte[] serialize(ConstantPool constantPool) {
                return null;
            }
        };
        assertTrue(firstConstant.equals(secondConstant));
    }

}
