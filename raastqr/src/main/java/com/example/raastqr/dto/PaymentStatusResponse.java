package com.example.raastqr.dto;

public class PaymentStatusResponse {

    private String msgId;
    private String instrId;
    private String endToEndId;
    private String txId;
    private String requestId;
    private String traceReference;
    private String transportStatus;
    private String finalStatus;
    private String groupStatus;
    private String transactionStatus;
    private String accountServiceReference;
    private String rejectionReasonCode;
    private String rejectionReasonInfo;
    private String latestMessageType;
    private String latestSource;
    private String latestRawMessage;

    public String getMsgId() { return msgId; }
    public void setMsgId(String msgId) { this.msgId = msgId; }

    public String getInstrId() { return instrId; }
    public void setInstrId(String instrId) { this.instrId = instrId; }

    public String getEndToEndId() { return endToEndId; }
    public void setEndToEndId(String endToEndId) { this.endToEndId = endToEndId; }

    public String getTxId() { return txId; }
    public void setTxId(String txId) { this.txId = txId; }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }

    public String getTraceReference() { return traceReference; }
    public void setTraceReference(String traceReference) { this.traceReference = traceReference; }

    public String getTransportStatus() { return transportStatus; }
    public void setTransportStatus(String transportStatus) { this.transportStatus = transportStatus; }

    public String getFinalStatus() { return finalStatus; }
    public void setFinalStatus(String finalStatus) { this.finalStatus = finalStatus; }

    public String getGroupStatus() { return groupStatus; }
    public void setGroupStatus(String groupStatus) { this.groupStatus = groupStatus; }

    public String getTransactionStatus() { return transactionStatus; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }

    public String getAccountServiceReference() { return accountServiceReference; }
    public void setAccountServiceReference(String accountServiceReference) { this.accountServiceReference = accountServiceReference; }

    public String getRejectionReasonCode() { return rejectionReasonCode; }
    public void setRejectionReasonCode(String rejectionReasonCode) { this.rejectionReasonCode = rejectionReasonCode; }

    public String getRejectionReasonInfo() { return rejectionReasonInfo; }
    public void setRejectionReasonInfo(String rejectionReasonInfo) { this.rejectionReasonInfo = rejectionReasonInfo; }

    public String getLatestMessageType() { return latestMessageType; }
    public void setLatestMessageType(String latestMessageType) { this.latestMessageType = latestMessageType; }

    public String getLatestSource() { return latestSource; }
    public void setLatestSource(String latestSource) { this.latestSource = latestSource; }

    public String getLatestRawMessage() { return latestRawMessage; }
    public void setLatestRawMessage(String latestRawMessage) { this.latestRawMessage = latestRawMessage; }
}

