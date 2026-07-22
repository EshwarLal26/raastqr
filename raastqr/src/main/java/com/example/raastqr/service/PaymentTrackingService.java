package com.example.raastqr.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.raastqr.dto.Pacs008Request;
import com.example.raastqr.dto.PaymentStatusResponse;
import com.example.raastqr.entity.PaymentStatusEntity;
import com.example.raastqr.repository.PaymentStatusRepository;

@Service
public class PaymentTrackingService {

    public static final String TRANSPORT_CREATED = "CREATED";
    public static final String TRANSPORT_SENT_TO_HOST = "SENT_TO_HOST";
    public static final String TRANSPORT_HOST_REJECT = "HOST_REJECT";
    public static final String FINAL_PROCESSING = "PROCESSING";

   private final PaymentStatusRepository paymentStatusRepository;

public PaymentTrackingService(PaymentStatusRepository paymentStatusRepository) {
    this.paymentStatusRepository = paymentStatusRepository;
}

private PaymentStatusEntity toEntity(PaymentStatusResponse status) {
    PaymentStatusEntity entity = new PaymentStatusEntity();
    entity.setTxId(status.getTxId());
    entity.setMsgId(status.getMsgId());
    entity.setInstrId(status.getInstrId());
    entity.setEndToEndId(status.getEndToEndId());
    entity.setRequestId(status.getRequestId());
    entity.setTraceReference(status.getTraceReference());
    entity.setTransportStatus(status.getTransportStatus());
    entity.setFinalStatus(status.getFinalStatus());
    entity.setGroupStatus(status.getGroupStatus());
    entity.setTransactionStatus(status.getTransactionStatus());
    entity.setAccountServiceReference(status.getAccountServiceReference());
    entity.setRejectionReasonCode(status.getRejectionReasonCode());
    entity.setRejectionReasonInfo(status.getRejectionReasonInfo());
    entity.setLatestMessageType(status.getLatestMessageType());
    entity.setLatestSource(status.getLatestSource());
    entity.setLatestRawMessage(status.getLatestRawMessage());
    return entity;
}

private PaymentStatusResponse toResponse(PaymentStatusEntity entity) {
    PaymentStatusResponse status = new PaymentStatusResponse();
    status.setTxId(entity.getTxId());
    status.setMsgId(entity.getMsgId());
    status.setInstrId(entity.getInstrId());
    status.setEndToEndId(entity.getEndToEndId());
    status.setRequestId(entity.getRequestId());
    status.setTraceReference(entity.getTraceReference());
    status.setTransportStatus(entity.getTransportStatus());
    status.setFinalStatus(entity.getFinalStatus());
    status.setGroupStatus(entity.getGroupStatus());
    status.setTransactionStatus(entity.getTransactionStatus());
    status.setAccountServiceReference(entity.getAccountServiceReference());
    status.setRejectionReasonCode(entity.getRejectionReasonCode());
    status.setRejectionReasonInfo(entity.getRejectionReasonInfo());
    status.setLatestMessageType(entity.getLatestMessageType());
    status.setLatestSource(entity.getLatestSource());
    status.setLatestRawMessage(entity.getLatestRawMessage());
    return status;
}

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
      paymentStatusRepository.save(toEntity(status));
return status;
    }

    public Optional<PaymentStatusResponse> findByTxId(String txId) {
       return paymentStatusRepository.findById(txId).map(this::toResponse);
    }

    public PaymentStatusResponse markSubmitted(String txId,
                                               String requestId,
                                               String traceReference,
                                               String rawMessage) {
     PaymentStatusResponse status = paymentStatusRepository.findById(txId)
        .map(this::toResponse)
        .orElseGet(PaymentStatusResponse::new);
        status.setTxId(txId);
        status.setRequestId(requestId);
        status.setTraceReference(traceReference);
        status.setTransportStatus(TRANSPORT_SENT_TO_HOST);
        status.setFinalStatus(FINAL_PROCESSING);
        status.setLatestMessageType("cas-post");
        status.setLatestSource("CAS_POST");
        status.setLatestRawMessage(rawMessage);
        paymentStatusRepository.save(toEntity(status));
        return status;
    }

    public PaymentStatusResponse markHostRejected(String txId,
                                                  String requestId,
                                                  String traceReference,
                                                  String rawMessage) {
      PaymentStatusResponse status = paymentStatusRepository.findById(txId)
        .map(this::toResponse)
        .orElseGet(PaymentStatusResponse::new);
        status.setTxId(txId);
        status.setRequestId(requestId);
        status.setTraceReference(traceReference);
        status.setTransportStatus(TRANSPORT_HOST_REJECT);
        status.setFinalStatus("REJECTED");
        status.setLatestMessageType("cas-post");
        status.setLatestSource("CAS_POST");
        status.setLatestRawMessage(rawMessage);
        paymentStatusRepository.save(toEntity(status));
        return status;
    }

    public PaymentStatusResponse updateFromPacs002(Pacs002CallbackData callbackData,
                                                   String rawMessage,
                                                   String source) {
      PaymentStatusResponse status = paymentStatusRepository.findById(callbackData.getOriginalTxId())
        .map(this::toResponse)
        .orElseGet(PaymentStatusResponse::new);

        status.setMsgId(callbackData.getOriginalMsgId());
        status.setInstrId(callbackData.getOriginalInstrId());
        status.setEndToEndId(callbackData.getOriginalEndToEndId());
        status.setTxId(callbackData.getOriginalTxId());
        status.setGroupStatus(callbackData.getGroupStatus());
        status.setTransactionStatus(callbackData.getTransactionStatus());
        status.setAccountServiceReference(callbackData.getAccountServiceReference());
        status.setRejectionReasonCode(callbackData.getRejectionReasonCode());
        status.setRejectionReasonInfo(callbackData.getRejectionReasonInfo());
        if (status.getTransportStatus() == null) {
            status.setTransportStatus(TRANSPORT_SENT_TO_HOST);
        }
        status.setFinalStatus(resolveFinalStatus(callbackData));
        status.setLatestMessageType("pacs002");
        status.setLatestSource(source);
        status.setLatestRawMessage(rawMessage);
        paymentStatusRepository.save(toEntity(status));
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
        private String rejectionReasonCode;
        private String rejectionReasonInfo;

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

        public String getRejectionReasonCode() { return rejectionReasonCode; }
        public void setRejectionReasonCode(String rejectionReasonCode) { this.rejectionReasonCode = rejectionReasonCode; }

        public String getRejectionReasonInfo() { return rejectionReasonInfo; }
        public void setRejectionReasonInfo(String rejectionReasonInfo) { this.rejectionReasonInfo = rejectionReasonInfo; }
    }
}


