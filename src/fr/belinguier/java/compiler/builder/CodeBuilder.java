package fr.belinguier.java.compiler.builder;

import fr.belinguier.java.compiler.builder.code.ASMInstruction;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class CodeBuilder {

    private static int buildSize(Object... instructions) {
        int result = 0;

        if (instructions == null || instructions.length < 1)
            return 0;
        for (Object obj : instructions) {
            if (obj instanceof ASMInstruction)
                result += 1;
            else if (obj instanceof Double || obj instanceof Long)
                result += 8;
            else if (obj instanceof Float || obj instanceof Integer)
                result += 4;
            else if (obj instanceof Short)
                result += 2;
            else if (obj instanceof Byte)
                result += 1;
        }
        return result;
    }

    public static byte[] build(Object... instructions) {
        int allocationSize = buildSize(instructions);
        ByteBuffer byteBuffer;

        if (allocationSize < 1)
            return null;
        byteBuffer = ByteBuffer.allocate(allocationSize);
        for (Object obj : instructions) {
            if (obj instanceof ASMInstruction)
                byteBuffer.put(((ASMInstruction) obj).opCode);
            else if (obj instanceof Double)
                byteBuffer.putDouble((Double) obj);
            else if (obj instanceof Float)
                byteBuffer.putFloat((Float) obj);
            else if (obj instanceof Long)
                byteBuffer.putLong((Long) obj);
            else if (obj instanceof Integer)
                byteBuffer.putInt((Integer) obj);
            else if (obj instanceof Short)
                byteBuffer.putShort((Short) obj);
            else if (obj instanceof Byte)
                byteBuffer.put((Byte) obj);
        }
        return byteBuffer.array();
    }
}
