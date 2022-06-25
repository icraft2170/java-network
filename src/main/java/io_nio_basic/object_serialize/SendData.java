package io_nio_basic.object_serialize;

import java.io.Serializable;

public class SendData implements Serializable {
    private int op1;
    private int op2;
    private String opCode;

    public SendData(int op1, int op2, String opCode) {
        this.op1 = op1;
        this.op2 = op2;
        this.opCode = opCode;
    }

    public int getOp1() {
        return op1;
    }

    public int getOp2() {
        return op2;
    }

    public String getOpCode() {
        return opCode;
    }
}
