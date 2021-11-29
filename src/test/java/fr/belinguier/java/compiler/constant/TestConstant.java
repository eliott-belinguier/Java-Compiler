package fr.belinguier.java.compiler.constant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

}
