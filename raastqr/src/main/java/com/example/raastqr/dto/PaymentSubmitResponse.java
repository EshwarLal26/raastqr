package com.example.raastqr.dto;

public class PaymentSubmitResponse {

    private boolean success;
    private String message;
    private String txId;
    private String msgId;
    private String instrId;
    private String endToEndId;
    private String requestId;
    private String traceReference;
    private String status;
    private String nextAction;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getTxId() { return txId; }
    public void setTxId(String txId) { this.txId = txId; }

    public String getMsgId() { return msgId; }
    public void setMsgId(String msgId) { this.msgId = msgId; }

    public String getInstrId() { return instrId; }
    public void setInstrId(String instrId) { this.instrId = instrId; }

    public String getEndToEndId() { return endToEndId; }
    public void setEndToEndId(String endToEndId) { this.endToEndId = endToEndId; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getTraceReference() { return traceReference; }
    public void setTraceReference(String traceReference) { this.traceReference = traceReference; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNextAction() { return nextAction; }
    public void setNextAction(String nextAction) { this.nextAction = nextAction; }
}