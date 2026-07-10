package com.example.raastqr.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.raastqr.dto.Pacs008Request;
import com.example.raastqr.dto.PaymentStatusResponse;

@Service
public class PaymentTrackingService {

    public static final String TRANSPORT_CREATED = "CREATED";
    public static final String TRANSPORT_SENT_TO_HOST = "SENT_TO_HOST";
    public static final String TRANSPORT_HOST_REJECT = "HOST_REJECT";
    public static final String FINAL_PROCESSING = "PROCESSING";

    private final Map<String, PaymentStatusResponse> paymentsByTxId = new ConcurrentHashMap<>();

    public PaymentStatusResponse registerOutgoingPayment(Pacs008Request request, String rawMessage) {
        PaymentStatusResponse status = new PaymentStatusResponse();
        status.setMsgId(request.getMsgId());
        status.setInstrId(request.getInstrId());
        status.setEndToEndId(request.getEndToEndId());
        status.setTxId(request.getTxId());
        status.setTransportStatus(TRANSPORT_CREATED);
        status.setFinalStatus(FINAL_PROCESSING);
        status.setGroupStatus("PENDING");
        status.setTransactionStatus("RCVD");
        status.setLatestMessageType("pacs008");
        status.setLatestSource("OUTBOUND_PACS008");
        status.setLatestRawMessage(rawMessage);
        paymentsByTxId.put(request.getTxId(), status);
        return status;
    }

    public Optional<PaymentStatusResponse> findByTxId(String txId) {
        return Optional.ofNullable(paymentsByTxId.get(txId));
    }

    public PaymentStatusResponse markSubmitted(String txId,
                                               String requestId,
                                               String traceReference,
                                               String rawMessage) {
        PaymentStatusResponse status = paymentsByTxId.computeIfAbsent(txId, key -> new PaymentStatusResponse());
        status.setTxId(txId);
        status.setRequestId(requestId);
        status.setTraceReference(traceReference);
        status.setTransportStatus(TRANSPORT_SENT_TO_HOST);
        status.setFinalStatus(FINAL_PROCESSING);
        status.setLatestMessageType("cas-post");
        status.setLatestSource("CAS_POST");
        status.setLatestRawMessage(rawMessage);
        return status;
    }

    public PaymentStatusResponse markHostRejected(String txId,
                                                  String requestId,
                                                  String traceReference,
                                                  String rawMessage) {
        PaymentStatusResponse status = paymentsByTxId.computeIfAbsent(txId, key -> new PaymentStatusResponse());
        status.setTxId(txId);
        status.setRequestId(requestId);
        status.setTraceReference(traceReference);
        status.setTransportStatus(TRANSPORT_HOST_REJECT);
        status.setFinalStatus("REJECTED");
        status.setLatestMessageType("cas-post");
        status.setLatestSource("CAS_POST");
        status.setLatestRawMessage(rawMessage);
        return status;
    }

    public PaymentStatusResponse updateFromPacs002(Pacs002CallbackData callbackData,
                                                   String rawMessage,
                                                   String source) {
        PaymentStatusResponse status = paymentsByTxId.computeIfAbsent(
                callbackData.getOriginalTxId(),
                key -> new PaymentStatusResponse()
        );

        status.setMsgId(callbackData.getOriginalMsgId());
        status.setInstrId(callbackData.getOriginalInstrId());
        status.setEndToEndId(callbackData.getOriginalEndToEndId());
        status.setTxId(callbackData.getOriginalTxId());
        status.setGroupStatus(callbackData.getGroupStatus());
        status.setTransactionStatus(callbackData.getTransactionStatus());
        status.setAccountServiceReference(callbackData.getAccountServiceReference());
        if (status.getTransportStatus() == null) {
            status.setTransportStatus(TRANSPORT_SENT_TO_HOST);
        }
        status.setFinalStatus(resolveFinalStatus(callbackData));
        status.setLatestMessageType("pacs002");
        status.setLatestSource(source);
        status.setLatestRawMessage(rawMessage);
        return status;
    }

    public PaymentStatusResponse updateFromPacs002(Pacs002CallbackData callbackData, String rawMessage) {
        return updateFromPacs002(callbackData, rawMessage, "PACS002");
    }

    private String resolveFinalStatus(Pacs002CallbackData callbackData) {
        if (callbackData.getTransactionStatus() != null && !callbackData.getTransactionStatus().isBlank()) {
            return callbackData.getTransactionStatus();
        }
        if (callbackData.getGroupStatus() != null && !callbackData.getGroupStatus().isBlank()) {
            return callbackData.getGroupStatus();
        }
        return FINAL_PROCESSING;
    }

    public static class Pacs002CallbackData {
        private String originalMsgId;
        private String originalInstrId;
        private String originalEndToEndId;
        private String originalTxId;
        private String groupStatus;
        private String transactionStatus;
        private String accountServiceReference;

        public String getOriginalMsgId() { return originalMsgId; }
        public void setOriginalMsgId(String originalMsgId) { this.originalMsgId = originalMsgId; }

        public String getOriginalInstrId() { return originalInstrId; }
        public void setOriginalInstrId(String originalInstrId) { this.originalInstrId = originalInstrId; }

        public String getOriginalEndToEndId() { return originalEndToEndId; }
        public void setOriginalEndToEndId(String originalEndToEndId) { this.originalEndToEndId = originalEndToEndId; }

        public String getOriginalTxId() { return originalTxId; }
        public void setOriginalTxId(String originalTxId) { this.originalTxId = originalTxId; }

        public String getGroupStatus() { return groupStatus; }
        public void setGroupStatus(String groupStatus) { this.groupStatus = groupStatus; }

        public String getTransactionStatus() { return transactionStatus; }
        public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }

        public String getAccountServiceReference() { return accountServiceReference; }
        public void setAccountServiceReference(String accountServiceReference) { this.accountServiceReference = accountServiceReference; }
    }
}
