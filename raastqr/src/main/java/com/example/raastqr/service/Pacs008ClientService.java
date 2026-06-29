package com.example.raastqr.service;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

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

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class Pacs008ClientService {

    private final RestTemplate restTemplate;
    private final String bankUrl;

    public Pacs008ClientService(RestTemplate restTemplate,
                                @Value("${app.bank-url}") String bankUrl) {
        this.restTemplate = restTemplate;
        this.bankUrl = bankUrl;
    }

    public void sendPacs008() {
        try {
            DataPDU dataPDU = new DataPDU();
            Body body = new Body();
            AppHdr appHdr = new AppHdr();
            Document doc = new Document();
            FIToFICstmrCdtTrf transfer = new FIToFICstmrCdtTrf();
            GrpHdr grpHdr = new GrpHdr();
            SttlmInf sttlmInf = new SttlmInf();
            CdtTrfTxInf tx = new CdtTrfTxInf();
            PmtId pmtId = new PmtId();
            PmtTpInf pmtTpInf = new PmtTpInf();
            SvcLvl svcLvl = new SvcLvl();
            LclInstrm lclInstrm = new LclInstrm();
            CtgyPurp ctgyPurp = new CtgyPurp();
            IntrBkSttlmAmt intrBkSttlmAmt = new IntrBkSttlmAmt();
            Amount instdAmt = new Amount();

            Party fr = new Party();
            FIId frFiId = new FIId();
            FinInstnId frFinInstnId = new FinInstnId();
            ClrSysMmbId frClrSysMmbId = new ClrSysMmbId();

            Party to = new Party();
            FIId toFiId = new FIId();
            FinInstnId toFinInstnId = new FinInstnId();
            ClrSysMmbId toClrSysMmbId = new ClrSysMmbId();

            InstgAgt instgAgt = new InstgAgt();
            FinInstnId instgFinInstnId = new FinInstnId();
            ClrSysMmbId instgClrSysMmbId = new ClrSysMmbId();

            InstdAgt instdAgt = new InstdAgt();
            FinInstnId instdFinInstnId = new FinInstnId();
            ClrSysMmbId instdClrSysMmbId = new ClrSysMmbId();

            Dbtr dbtr = new Dbtr();
            CtctDtls dbtrCtct = new CtctDtls();
            Othr othr1 = new Othr();
            Othr othr2 = new Othr();

            DbtrAcct dbtrAcct = new DbtrAcct();
            Id dbtrId = new Id();

            DbtrAgt dbtrAgt = new DbtrAgt();
            FinInstnId dbtrAgtFinInstnId = new FinInstnId();
            ClrSysMmbId dbtrAgtClrSysMmbId = new ClrSysMmbId();

            CdtrAgt cdtrAgt = new CdtrAgt();
            FinInstnId cdtrAgtFinInstnId = new FinInstnId();
            ClrSysMmbId cdtrAgtClrSysMmbId = new ClrSysMmbId();

            Cdtr cdtr = new Cdtr();
            PstlAdr pstlAdr = new PstlAdr();
            PartyId partyId = new PartyId();
            OrgId orgId = new OrgId();
            OthrId othrId = new OthrId();
            SchemeNm schemeNm = new SchemeNm();
            CtctDtls cdtrCtct = new CtctDtls();

            CdtrAcct cdtrAcct = new CdtrAcct();
            Id cdtrId = new Id();

            InstrForNxtAgt instrForNxtAgt = new InstrForNxtAgt();
            Purp purp = new Purp();

            RmtInf rmtInf = new RmtInf();
            Strd strd = new Strd();
            RfrdDocInf rfrdDocInf = new RfrdDocInf();
            Tp tp = new Tp();
            CdOrPrtry cdOrPrtry = new CdOrPrtry();
            RfrdDocAmt rfrdDocAmt = new RfrdDocAmt();
            Amount duePyblAmt = new Amount();

            frClrSysMmbId.setMmbId("NAYAPKKA");
            frFinInstnId.setClrSysMmbId(frClrSysMmbId);
            frFiId.setFinInstnId(frFinInstnId);
            fr.setFIId(frFiId);

            toClrSysMmbId.setMmbId("WMBLPKKA");
            toFinInstnId.setClrSysMmbId(toClrSysMmbId);
            toFiId.setFinInstnId(toFinInstnId);
            to.setFIId(toFiId);

            appHdr.setFr(fr);
            appHdr.setTo(to);
            appHdr.setBizMsgIdr("NAYAPKKA5869884010970552");
            appHdr.setMsgDefIdr("pacs.008.001.08");
            appHdr.setBizSvc("IPS");
            appHdr.setCreDt("2026-05-21T16:59:59Z");

            sttlmInf.setSttlmMtd("CLRG");

            grpHdr.setMsgId("NAYAPKKA260521165959501556");
            grpHdr.setCreDtTm("2026-05-21T16:59:59");
            grpHdr.setBtchBookg(false);
            grpHdr.setNbOfTxs("1");
            grpHdr.setSttlmInf(sttlmInf);

            pmtId.setInstrId("NAYA260521165959278535");
            pmtId.setEndToEndId("40485fa3c03c-4839-b608-b10da953b476");
            pmtId.setTxId("NAYA260521165959278535");

            svcLvl.setPrtry("0100");
            lclInstrm.setPrtry("PMCT");
            ctgyPurp.setPrtry("050");

            pmtTpInf.setClrChanl("RTNS");
            pmtTpInf.setSvcLvl(svcLvl);
            pmtTpInf.setLclInstrm(lclInstrm);
            pmtTpInf.setCtgyPurp(ctgyPurp);

            intrBkSttlmAmt.setValue(new BigDecimal("120.0"));
            intrBkSttlmAmt.setCcy("PKR");

            instdAmt.setValue(new BigDecimal("120.0"));
            instdAmt.setCcy("PKR");

            instgClrSysMmbId.setMmbId("NAYAPKKA");
            instgFinInstnId.setClrSysMmbId(instgClrSysMmbId);
            instgAgt.setFinInstnId(instgFinInstnId);

            instdClrSysMmbId.setMmbId("WMBLPKKA");
            instdFinInstnId.setClrSysMmbId(instdClrSysMmbId);
            instdAgt.setFinInstnId(instdFinInstnId);

            dbtr.setNm("ALI RAZA");
            dbtrCtct.setMobNb("+92-30xxxxxx4117");
            dbtrCtct.setEmailAdr("7xali00@gmail.com");
            othr1.setChanlTp("LATD");
            othr2.setChanlTp("LONG");
            dbtrCtct.setOthr(List.of(othr1, othr2));
            dbtr.setCtctDtls(dbtrCtct);

            dbtrId.setIban("PK02NAYA123450xxxxx");
            dbtrAcct.setId(dbtrId);

            dbtrAgtClrSysMmbId.setMmbId("NAYAPKKA");
            dbtrAgtFinInstnId.setClrSysMmbId(dbtrAgtClrSysMmbId);
            dbtrAgt.setFinInstnId(dbtrAgtFinInstnId);

            cdtrAgtClrSysMmbId.setMmbId("WMBLPKKA");
            cdtrAgtFinInstnId.setClrSysMmbId(cdtrAgtClrSysMmbId);
            cdtrAgt.setFinInstnId(cdtrAgtFinInstnId);

            cdtr.setNm("CHAADA PAN SHOP");
            pstlAdr.setSubDept("9830xxxxx39");
            pstlAdr.setTwnNm("Lahore");
            cdtr.setPstlAdr(pstlAdr);

            othrId.setId("5999");
            schemeNm.setPrtry("MCC");
            othrId.setSchmeNm(schemeNm);
            orgId.setOthr(othrId);
            partyId.setOrgId(orgId);
            cdtr.setId(partyId);

            cdtr.setCtryOfRes("PK");
            cdtrCtct.setNm(" PAN SHOP");
            cdtrCtct.setMobNb("+92-1008068330");
            cdtrCtct.setDept(" PAN SHOP");
            cdtr.setCtctDtls(cdtrCtct);

            cdtrId.setIban("PK57JCMA00101xxxxxxxxxxxx");
            cdtrAcct.setId(cdtrId);

            instrForNxtAgt.setInstrInf("P2M Payment");
            purp.setPrtry("Others");

            cdOrPrtry.setPrtry("RSQR");
            tp.setCdOrPrtry(cdOrPrtry);
            rfrdDocInf.setTp(tp);
            rfrdDocInf.setRltdDt("2026-05-21");

            duePyblAmt.setValue(new BigDecimal("120.0"));
            duePyblAmt.setCcy("PKR");
            rfrdDocAmt.setDuePyblAmt(duePyblAmt);

            strd.setRfrdDocInf(rfrdDocInf);
            strd.setRfrdDocAmt(rfrdDocAmt);

            rmtInf.setUstrd("MERCHANT PAYMENT UAT");
            rmtInf.setStrd(strd);

            tx.setPmtId(pmtId);
            tx.setPmtTpInf(pmtTpInf);
            tx.setIntrBkSttlmAmt(intrBkSttlmAmt);
            tx.setIntrBkSttlmDt("2026-05-21");
            tx.setInstdAmt(instdAmt);
            tx.setChrgBr("DEBT");
            tx.setInstgAgt(instgAgt);
            tx.setInstdAgt(instdAgt);
            tx.setDbtr(dbtr);
            tx.setDbtrAcct(dbtrAcct);
            tx.setDbtrAgt(dbtrAgt);
            tx.setCdtrAgt(cdtrAgt);
            tx.setCdtr(cdtr);
            tx.setCdtrAcct(cdtrAcct);
            tx.setInstrForNxtAgt(instrForNxtAgt);
            tx.setPurp(purp);
            tx.setRmtInf(rmtInf);

            transfer.setGrpHdr(grpHdr);
            transfer.setCdtTrfTxInf(tx);

            doc.setFiToFiCstmrCdtTrf(transfer);

            body.setAppHdr(appHdr);
            body.setDocument(doc);

            dataPDU.setBody(body);

            JAXBContext context = JAXBContext.newInstance(DataPDU.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(dataPDU, writer);

            String requestXml = writer.toString();

            System.out.println("=== GENERATED PACS.008 XML ===");
            System.out.println(requestXml);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            HttpEntity<String> request = new HttpEntity<>(requestXml, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(bankUrl, request, String.class);

            System.out.println("=== BANK RESPONSE (PACS.002) ===");
            System.out.println(response.getBody());

        } catch (HttpStatusCodeException ex) {
            System.err.println("HTTP Error: " + ex.getStatusCode());
            System.err.println(ex.getResponseBodyAsString());
        } catch (ResourceAccessException ex) {
            System.err.println("Connection Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}