package fr.belinguier.java.attribute.variabletable;

import fr.belinguier.java.compiler.Serializable;

import java.nio.ByteBuffer;

/**
 * @author Eliott Belinguier
 */
public class LocalVariableType implements Serializable {

    public short startPc;
    public short length;
    public short nameIndex;
    public short signatureIndex;
    public short index;

    @Override
    public int sizeOfByteArray() {
        return 10;
    }

    @Override
    public byte[] toByte() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(sizeOfByteArray());

        byteBuffer.putShort(this.startPc);
        byteBuffer.putShort(this.length);
        byteBuffer.putShort(this.nameIndex);
        byteBuffer.putShort(this.signatureIndex);
        byteBuffer.putShort(this.index);
        return byteBuffer.array();
    }
}
