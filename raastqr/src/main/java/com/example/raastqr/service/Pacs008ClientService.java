package com.example.raastqr.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.raastqr.dto.Pacs008Request;
import com.example.raastqr.dto.PaymentStatusResponse;
import com.example.raastqr.model.pacs008.Amount;
import com.example.raastqr.model.pacs008.AppHdr;
import com.example.raastqr.model.pacs008.Body;
import com.example.raastqr.model.pacs008.CdOrPrtry;
import com.example.raastqr.model.pacs008.CdtTrfTxInf;
import com.example.raastqr.model.pacs008.Cdtr;
import com.example.raastqr.model.pacs008.CdtrAcct;
import com.example.raastqr.model.pacs008.CdtrAgt;
import com.example.raastqr.model.pacs008.ClrSysMmbId;
import com.example.raastqr.model.pacs008.CtctDtls;
import com.example.raastqr.model.pacs008.CtgyPurp;
import com.example.raastqr.model.pacs008.DataPDU;
import com.example.raastqr.model.pacs008.Dbtr;
import com.example.raastqr.model.pacs008.DbtrAcct;
import com.example.raastqr.model.pacs008.DbtrAgt;
import com.example.raastqr.model.pacs008.Document;
import com.example.raastqr.model.pacs008.FIId;
import com.example.raastqr.model.pacs008.FIToFICstmrCdtTrf;
import com.example.raastqr.model.pacs008.FinInstnId;
import com.example.raastqr.model.pacs008.GrpHdr;
import com.example.raastqr.model.pacs008.Id;
import com.example.raastqr.model.pacs008.InstdAgt;
import com.example.raastqr.model.pacs008.InstgAgt;
import com.example.raastqr.model.pacs008.InstrForNxtAgt;
import com.example.raastqr.model.pacs008.IntrBkSttlmAmt;
import com.example.raastqr.model.pacs008.LclInstrm;
import com.example.raastqr.model.pacs008.OrgId;
import com.example.raastqr.model.pacs008.Othr;
import com.example.raastqr.model.pacs008.OthrId;
import com.example.raastqr.model.pacs008.Party;
import com.example.raastqr.model.pacs008.PartyId;
import com.example.raastqr.model.pacs008.PmtId;
import com.example.raastqr.model.pacs008.PmtTpInf;
import com.example.raastqr.model.pacs008.PstlAdr;
import com.example.raastqr.model.pacs008.Purp;
import com.example.raastqr.model.pacs008.RfrdDocAmt;
import com.example.raastqr.model.pacs008.RfrdDocInf;
import com.example.raastqr.model.pacs008.RmtInf;
import com.example.raastqr.model.pacs008.SchemeNm;
import com.example.raastqr.model.pacs008.Strd;
import com.example.raastqr.model.pacs008.SttlmInf;
import com.example.raastqr.model.pacs008.SvcLvl;
import com.example.raastqr.model.pacs008.Tp;
import com.example.raastqr.utils.RaastUtils;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class Pacs008ClientService {

    private final RestTemplate restTemplate;
    private final String bankUrl;
    private final PaymentTrackingService paymentTrackingService;
    private final Pacs002Service pacs002Service;

   public Pacs008ClientService(RestTemplate restTemplate,
                            @Value("${app.bank-url}") String bankUrl,
                            PaymentTrackingService paymentTrackingService,
                            Pacs002Service pacs002Service) {
    this.restTemplate = restTemplate;
    this.bankUrl = bankUrl;
    this.paymentTrackingService = paymentTrackingService;
    this.pacs002Service = pacs002Service;
}

    public String buildPacs008Xml(Pacs008Request request) throws Exception {
        validateAndNormalize(request);

        DataPDU dataPdu = new DataPDU();
        Body body = new Body();
        Document document = new Document();
        FIToFICstmrCdtTrf transfer = new FIToFICstmrCdtTrf();

        body.setAppHdr(buildAppHdr(request));
        transfer.setGrpHdr(buildGroupHeader(request));
        transfer.setCdtTrfTxInf(buildCreditTransfer(request));

        document.setFiToFiCstmrCdtTrf(transfer);
        body.setDocument(document);
        dataPdu.setBody(body);

        return marshalXml(dataPdu);
    }

    private void validateAndNormalize(Pacs008Request request) {
        request.setFromMemberId(RaastUtils.formatMemberId("fromMemberId", request.getFromMemberId()));
        request.setToMemberId(RaastUtils.formatMemberId("toMemberId", request.getToMemberId()));
        request.setInstgAgtMmbId(RaastUtils.formatMemberId("instgAgtMmbId", request.getInstgAgtMmbId()));
        request.setInstdAgtMmbId(RaastUtils.formatMemberId("instdAgtMmbId", request.getInstdAgtMmbId()));
        request.setDebtorAgentMmbId(RaastUtils.formatMemberId("debtorAgentMmbId", request.getDebtorAgentMmbId()));
        request.setCreditorAgentMmbId(RaastUtils.formatMemberId("creditorAgentMmbId", request.getCreditorAgentMmbId()));

        request.setAppHdrCreDt(RaastUtils.formatIsoOffsetDateTime("appHdrCreDt", request.getAppHdrCreDt()));
        request.setGrpHdrCreDtTm(RaastUtils.formatIsoDateTime("grpHdrCreDtTm", request.getGrpHdrCreDtTm()));
        request.setIntrBkSttlmDt(RaastUtils.formatIsoDate("intrBkSttlmDt", request.getIntrBkSttlmDt()));
        request.setReferredDocRelatedDate(RaastUtils.formatIsoDate("referredDocRelatedDate", request.getReferredDocRelatedDate()));

        request.setIntrBkSttlmAmt(RaastUtils.requireAmount("intrBkSttlmAmt", request.getIntrBkSttlmAmt()));
        request.setInstdAmt(RaastUtils.requireAmount("instdAmt", request.getInstdAmt()));
        request.setDuePayableAmt(RaastUtils.requireAmount("duePayableAmt", request.getDuePayableAmt()));

        request.setIntrBkSttlmAmtCcy(RaastUtils.formatCurrency("intrBkSttlmAmtCcy", request.getIntrBkSttlmAmtCcy()));
        request.setInstdAmtCcy(RaastUtils.formatCurrency("instdAmtCcy", request.getInstdAmtCcy()));
        request.setDuePayableAmtCcy(RaastUtils.formatCurrency("duePayableAmtCcy", request.getDuePayableAmtCcy()));

        request.setDebtorMobile(RaastUtils.requireValidMobile("debtorMobile", request.getDebtorMobile()));
        request.setCreditorMobile(RaastUtils.requireValidMobile("creditorMobile", request.getCreditorMobile()));

        request.setDebtorIban(RaastUtils.formatIban("debtorIban", request.getDebtorIban()));
        request.setCreditorIban(RaastUtils.formatIban("creditorIban", request.getCreditorIban()));

        request.setBizMsgIdr(RaastUtils.requireText("bizMsgIdr", request.getBizMsgIdr()));
        request.setMsgDefIdr(RaastUtils.requireText("msgDefIdr", request.getMsgDefIdr()));
        request.setBizSvc(RaastUtils.requireText("bizSvc", request.getBizSvc()));
        request.setMsgId(RaastUtils.requireText("msgId", request.getMsgId()));
        request.setNbOfTxs(RaastUtils.requireText("nbOfTxs", request.getNbOfTxs()));
        request.setSttlmMtd(RaastUtils.requireText("sttlmMtd", request.getSttlmMtd()));
        request.setInstrId(RaastUtils.requireText("instrId", request.getInstrId()));
        request.setEndToEndId(RaastUtils.requireText("endToEndId", request.getEndToEndId()));
        request.setTxId(RaastUtils.requireText("txId", request.getTxId()));
        request.setSvcLvl(RaastUtils.requireText("svcLvl", request.getSvcLvl()));
        request.setLclInstrm(RaastUtils.requireText("lclInstrm", request.getLclInstrm()));
        request.setCtgyPurp(RaastUtils.requireText("ctgyPurp", request.getCtgyPurp()));
        request.setClrChanl(RaastUtils.requireText("clrChanl", request.getClrChanl()));
        request.setChrgBr(RaastUtils.requireText("chrgBr", request.getChrgBr()));
        request.setDebtorName(RaastUtils.requireText("debtorName", request.getDebtorName()));
        request.setCreditorName(RaastUtils.requireText("creditorName", request.getCreditorName()));
        request.setCreditorMcc(RaastUtils.requireText("creditorMcc", request.getCreditorMcc()));
        request.setInstrInf(RaastUtils.requireText("instrInf", request.getInstrInf()));
        request.setPurp(RaastUtils.requireText("purp", request.getPurp()));
        request.setReferredDocType(RaastUtils.requireText("referredDocType", request.getReferredDocType()));
        request.setUstrd(RaastUtils.requireText("ustrd", request.getUstrd()));

        request.setDebtorEmail(RaastUtils.normalizeOptionalText(request.getDebtorEmail()));
        request.setCreditorSubDept(RaastUtils.normalizeOptionalText(request.getCreditorSubDept()));
        request.setCreditorTownName(RaastUtils.normalizeOptionalText(request.getCreditorTownName()));
        request.setCreditorCountryOfRes(RaastUtils.normalizeOptionalText(request.getCreditorCountryOfRes()));
        request.setCreditorContactName(RaastUtils.normalizeOptionalText(request.getCreditorContactName()));
        request.setCreditorDepartment(RaastUtils.normalizeOptionalText(request.getCreditorDepartment()));
    }

    public String sendPacs008(Pacs008Request request) throws Exception {
        String requestXml = buildPacs008Xml(request);
        return postXml(requestXml).getBody();
    }

  public PaymentStatusResponse sendAndTrackPacs008(Pacs008Request request) throws Exception {
    String requestXml = buildPacs008Xml(request);
    PaymentStatusResponse status = paymentTrackingService.registerOutgoingPayment(request, requestXml);

    String requestId = UUID.randomUUID().toString();
    String traceReference = buildTraceReference(request);
    ResponseEntity<String> response = postXml(requestXml);

    if (response.getStatusCode().is2xxSuccessful()) {
        paymentTrackingService.markSubmitted(
                request.getTxId(),
                requestId,
                traceReference,
                response.getBody()
        );

        PaymentStatusResponse pacs002Status = waitForPacs002(request.getTxId());
        if (pacs002Status != null) {
            return pacs002Status;
        }
    } else {
        paymentTrackingService.markHostRejected(
                request.getTxId(),
                requestId,
                traceReference,
                response.getBody()
        );
    }

    return paymentTrackingService.findByTxId(request.getTxId()).orElse(status);
}

    private AppHdr buildAppHdr(Pacs008Request request) {
        AppHdr appHdr = new AppHdr();
        appHdr.setFr(buildParty(request.getFromMemberId()));
        appHdr.setTo(buildParty(request.getToMemberId()));
        appHdr.setBizMsgIdr(request.getBizMsgIdr());
        appHdr.setMsgDefIdr(request.getMsgDefIdr());
        appHdr.setBizSvc(request.getBizSvc());
        appHdr.setCreDt(request.getAppHdrCreDt());
        return appHdr;
    }

    private Party buildParty(String memberId) {
        Party party = new Party();
        FIId fiId = new FIId();
        fiId.setFinInstnId(buildFinInstnId(memberId));
        party.setFIId(fiId);
        return party;
    }

    private GrpHdr buildGroupHeader(Pacs008Request request) {
        GrpHdr grpHdr = new GrpHdr();
        grpHdr.setMsgId(request.getMsgId());
        grpHdr.setCreDtTm(request.getGrpHdrCreDtTm());
        grpHdr.setBtchBookg(request.isBtchBookg());
        grpHdr.setNbOfTxs(request.getNbOfTxs());
        grpHdr.setSttlmInf(buildSettlementInfo(request));
        return grpHdr;
    }

    private SttlmInf buildSettlementInfo(Pacs008Request request) {
        SttlmInf sttlmInf = new SttlmInf();
        sttlmInf.setSttlmMtd(request.getSttlmMtd());
        return sttlmInf;
    }

    private CdtTrfTxInf buildCreditTransfer(Pacs008Request request) {
        CdtTrfTxInf tx = new CdtTrfTxInf();
        tx.setPmtId(buildPaymentId(request));
        tx.setPmtTpInf(buildPaymentTypeInfo(request));
        tx.setIntrBkSttlmAmt(buildIntrBkSttlmAmt(request));
        tx.setIntrBkSttlmDt(request.getIntrBkSttlmDt());
        tx.setInstdAmt(buildInstdAmt(request));
        tx.setChrgBr(request.getChrgBr());
        tx.setInstgAgt(buildInstgAgt(request));
        tx.setInstdAgt(buildInstdAgt(request));
        tx.setDbtr(buildDebtor(request));
        tx.setDbtrAcct(buildDbtrAcct(request));
        tx.setDbtrAgt(buildDbtrAgt(request));
        tx.setCdtrAgt(buildCdtrAgt(request));
        tx.setCdtr(buildCreditor(request));
        tx.setCdtrAcct(buildCdtrAcct(request));
        tx.setInstrForNxtAgt(buildInstrForNxtAgt(request));
        tx.setPurp(buildPurpose(request));
        tx.setRmtInf(buildRemittance(request));
        return tx;
    }

    private PmtId buildPaymentId(Pacs008Request request) {
        PmtId pmtId = new PmtId();
        pmtId.setInstrId(request.getInstrId());
        pmtId.setEndToEndId(request.getEndToEndId());
        pmtId.setTxId(request.getTxId());
        return pmtId;
    }

    private PmtTpInf buildPaymentTypeInfo(Pacs008Request request) {
        PmtTpInf pmtTpInf = new PmtTpInf();
        pmtTpInf.setClrChanl(request.getClrChanl());
        pmtTpInf.setSvcLvl(buildSvcLvl(request));
        pmtTpInf.setLclInstrm(buildLclInstrm(request));
        pmtTpInf.setCtgyPurp(buildCtgyPurp(request));
        return pmtTpInf;
    }

    private SvcLvl buildSvcLvl(Pacs008Request request) {
        SvcLvl svcLvl = new SvcLvl();
        svcLvl.setPrtry(request.getSvcLvl());
        return svcLvl;
    }

    private LclInstrm buildLclInstrm(Pacs008Request request) {
        LclInstrm lclInstrm = new LclInstrm();
        lclInstrm.setPrtry(request.getLclInstrm());
        return lclInstrm;
    }

    private CtgyPurp buildCtgyPurp(Pacs008Request request) {
        CtgyPurp ctgyPurp = new CtgyPurp();
        ctgyPurp.setPrtry(request.getCtgyPurp());
        return ctgyPurp;
    }

    private IntrBkSttlmAmt buildIntrBkSttlmAmt(Pacs008Request request) {
        IntrBkSttlmAmt amount = new IntrBkSttlmAmt();
        amount.setValue(request.getIntrBkSttlmAmt());
        amount.setCcy(request.getIntrBkSttlmAmtCcy());
        return amount;
    }

    private Amount buildInstdAmt(Pacs008Request request) {
        Amount amount = new Amount();
        amount.setValue(request.getInstdAmt());
        amount.setCcy(request.getInstdAmtCcy());
        return amount;
    }

    private InstgAgt buildInstgAgt(Pacs008Request request) {
        InstgAgt instgAgt = new InstgAgt();
        instgAgt.setFinInstnId(buildFinInstnId(request.getInstgAgtMmbId()));
        return instgAgt;
    }

    private InstdAgt buildInstdAgt(Pacs008Request request) {
        InstdAgt instdAgt = new InstdAgt();
        instdAgt.setFinInstnId(buildFinInstnId(request.getInstdAgtMmbId()));
        return instdAgt;
    }

    private DbtrAgt buildDbtrAgt(Pacs008Request request) {
        DbtrAgt dbtrAgt = new DbtrAgt();
        dbtrAgt.setFinInstnId(buildFinInstnId(request.getDebtorAgentMmbId()));
        return dbtrAgt;
    }

    private CdtrAgt buildCdtrAgt(Pacs008Request request) {
        CdtrAgt cdtrAgt = new CdtrAgt();
        cdtrAgt.setFinInstnId(buildFinInstnId(request.getCreditorAgentMmbId()));
        return cdtrAgt;
    }

    private FinInstnId buildFinInstnId(String memberId) {
        FinInstnId finInstnId = new FinInstnId();
        ClrSysMmbId clrSysMmbId = new ClrSysMmbId();
        clrSysMmbId.setMmbId(memberId);
        finInstnId.setClrSysMmbId(clrSysMmbId);
        return finInstnId;
    }

    private Dbtr buildDebtor(Pacs008Request request) {
        Dbtr dbtr = new Dbtr();
        CtctDtls ctctDtls = new CtctDtls();
        ctctDtls.setMobNb(request.getDebtorMobile());
        ctctDtls.setEmailAdr(request.getDebtorEmail());
        ctctDtls.setOthr(buildDebtorOtherContacts(request.getDebtorChannelTypes()));
        dbtr.setNm(request.getDebtorName());
        dbtr.setCtctDtls(ctctDtls);
        return dbtr;
    }

    private DbtrAcct buildDbtrAcct(Pacs008Request request) {
        DbtrAcct dbtrAcct = new DbtrAcct();
        Id id = new Id();
        id.setIban(request.getDebtorIban());
        dbtrAcct.setId(id);
        return dbtrAcct;
    }

    private Cdtr buildCreditor(Pacs008Request request) {
        Cdtr cdtr = new Cdtr();
        cdtr.setNm(request.getCreditorName());
        cdtr.setPstlAdr(buildCreditorAddress(request));
        cdtr.setId(buildCreditorPartyId(request));
        cdtr.setCtryOfRes(request.getCreditorCountryOfRes());
        cdtr.setCtctDtls(buildCreditorContact(request));
        return cdtr;
    }

    private PstlAdr buildCreditorAddress(Pacs008Request request) {
        PstlAdr pstlAdr = new PstlAdr();
        pstlAdr.setSubDept(request.getCreditorSubDept());
        pstlAdr.setTwnNm(request.getCreditorTownName());
        return pstlAdr;
    }

    private PartyId buildCreditorPartyId(Pacs008Request request) {
        PartyId partyId = new PartyId();
        OrgId orgId = new OrgId();
        OthrId othrId = new OthrId();
        SchemeNm schemeNm = new SchemeNm();
        othrId.setId(request.getCreditorMcc());
        schemeNm.setPrtry("MCC");
        othrId.setSchmeNm(schemeNm);
        orgId.setOthr(othrId);
        partyId.setOrgId(orgId);
        return partyId;
    }

    private CtctDtls buildCreditorContact(Pacs008Request request) {
        CtctDtls ctctDtls = new CtctDtls();
        ctctDtls.setNm(request.getCreditorContactName());
        ctctDtls.setMobNb(request.getCreditorMobile());
        ctctDtls.setDept(request.getCreditorDepartment());
        return ctctDtls;
    }

    private CdtrAcct buildCdtrAcct(Pacs008Request request) {
        CdtrAcct cdtrAcct = new CdtrAcct();
        Id id = new Id();
        id.setIban(request.getCreditorIban());
        cdtrAcct.setId(id);
        return cdtrAcct;
    }

    private InstrForNxtAgt buildInstrForNxtAgt(Pacs008Request request) {
        InstrForNxtAgt instrForNxtAgt = new InstrForNxtAgt();
        instrForNxtAgt.setInstrInf(request.getInstrInf());
        return instrForNxtAgt;
    }

    private Purp buildPurpose(Pacs008Request request) {
        Purp purp = new Purp();
        purp.setPrtry(request.getPurp());
        return purp;
    }

    private RmtInf buildRemittance(Pacs008Request request) {
        RmtInf rmtInf = new RmtInf();
        rmtInf.setUstrd(request.getUstrd());
        rmtInf.setStrd(buildStructuredRemittance(request));
        return rmtInf;
    }

    private Strd buildStructuredRemittance(Pacs008Request request) {
        Strd strd = new Strd();
        strd.setRfrdDocInf(buildReferredDocInfo(request));
        strd.setRfrdDocAmt(buildReferredDocAmt(request));
        return strd;
    }

    private RfrdDocInf buildReferredDocInfo(Pacs008Request request) {
        RfrdDocInf rfrdDocInf = new RfrdDocInf();
        Tp tp = new Tp();
        CdOrPrtry cdOrPrtry = new CdOrPrtry();
        cdOrPrtry.setPrtry(request.getReferredDocType());
        tp.setCdOrPrtry(cdOrPrtry);
        rfrdDocInf.setTp(tp);
        rfrdDocInf.setRltdDt(request.getReferredDocRelatedDate());
        return rfrdDocInf;
    }

    private RfrdDocAmt buildReferredDocAmt(Pacs008Request request) {
        RfrdDocAmt rfrdDocAmt = new RfrdDocAmt();
        Amount duePyblAmt = new Amount();
        duePyblAmt.setValue(request.getDuePayableAmt());
        duePyblAmt.setCcy(request.getDuePayableAmtCcy());
        rfrdDocAmt.setDuePyblAmt(duePyblAmt);
        return rfrdDocAmt;
    }

    private String marshalXml(DataPDU dataPdu) throws Exception {
        JAXBContext context = JAXBContext.newInstance(DataPDU.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(dataPdu, writer);
        return writer.toString();
    }

  private ResponseEntity<String> postXml(String requestXml) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_XML);

    HttpEntity<String> httpRequest = new HttpEntity<>(requestXml, headers);

    return restTemplate.postForEntity(
            bankUrl + "/pacs008",
            httpRequest,
            String.class
    );
}

private PaymentStatusResponse waitForPacs002(String txId) throws InterruptedException {
    int attempts = 0;

    while (attempts < 12) {
        String pacs002Xml = restTemplate.getForObject(
                bankUrl + "/output/pacs002",
                String.class
        );

        if (pacs002Xml != null && !pacs002Xml.isBlank()) {
            PaymentTrackingService.Pacs002CallbackData callbackData =
                    pacs002Service.parseIncomingPacs002(pacs002Xml);

            if (txId.equals(callbackData.getOriginalTxId())) {
                return paymentTrackingService.updateFromPacs002(
                        callbackData,
                        pacs002Xml,
                        "BANK_OUTPUT_PACS002"
                );
            }
        }

        attempts++;
        Thread.sleep(10000);
    }

    return null;
}

    private String buildTraceReference(Pacs008Request request) {
        return "TRACE-" + request.getTxId();
    }

    private List<Othr> buildDebtorOtherContacts(List<String> channelTypes) {
        List<Othr> others = new ArrayList<>();
        if (channelTypes == null) {
            return others;
        }

        for (String channelType : channelTypes) {
            Othr othr = new Othr();
            othr.setChanlTp(channelType);
            others.add(othr);
        }

        return others;
    }
}
