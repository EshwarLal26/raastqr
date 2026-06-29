package com.example.raastqr.service;

import java.io.StringWriter;
import java.math.BigDecimal;

import com.example.raastqr.model.pacs008.CdtTrfTxInf;
import com.example.raastqr.model.pacs008.CdtrAcct;
import com.example.raastqr.model.pacs008.Dbtr;
import com.example.raastqr.model.pacs008.DbtrAcct;
import com.example.raastqr.model.pacs008.DbtrAgt;
import com.example.raastqr.model.pacs008.Document;
import com.example.raastqr.model.pacs008.FIToFICstmrCdtTrf;
import com.example.raastqr.model.pacs008.FinInstnId;
import com.example.raastqr.model.pacs008.GrpHdr;
import com.example.raastqr.model.pacs008.Id;
import com.example.raastqr.model.pacs008.IntrBkSttlmAmt;
import com.example.raastqr.model.pacs008.PmtId;
import com.example.raastqr.model.pacs008.RmtInf;
import com.example.raastqr.model.pacs008.SttlmInf;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

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
            Document doc = new Document();
            FIToFICstmrCdtTrf transfer = new FIToFICstmrCdtTrf();
            GrpHdr grpHdr = new GrpHdr();
            SttlmInf sttlmInf = new SttlmInf();
            CdtTrfTxInf tx = new CdtTrfTxInf();
            PmtId pmtId = new PmtId();
            IntrBkSttlmAmt amt = new IntrBkSttlmAmt();
            Dbtr dbtr = new Dbtr();
            DbtrAcct dbtrAcct = new DbtrAcct();
            CdtrAcct cdtrAcct = new CdtrAcct();
            Id dbtrId = new Id();
            Id cdtrId = new Id();
            DbtrAgt dbtrAgt = new DbtrAgt();
            FinInstnId finInstnId = new FinInstnId();
            RmtInf rmtInf = new RmtInf();

            // Set values in child objects
            sttlmInf.setSttlmMtd("CLRG");



           // grpHdr.setMsgId("MSG20260629001");
            grpHdr.setCreDtTm("2026-06-29T10:30:00");
            grpHdr.setNbOfTxs("1");
            grpHdr.setSttlmInf(sttlmInf);

            pmtId.setInstrId("INST0001");
            pmtId.setEndToEndId("E2E0001");
            pmtId.setTxId("TX0001");

            amt.setValue(new BigDecimal("1000.00"));
            amt.setCcy("EUR");

            dbtr.setNm("Ali Khan");

            dbtrId.setIban("PK12BANK00012345678901");
            dbtrAcct.setId(dbtrId);

            finInstnId.setBicfi("BANKPKKA");
            dbtrAgt.setFinInstnId(finInstnId);

            cdtrId.setIban("DE44500105175407324931");
            cdtrAcct.setId(cdtrId);

            rmtInf.setUstrd("Invoice Payment June");

            // Attach child objects into transaction
            tx.setPmtId(pmtId);
            tx.setIntrBkSttlmAmt(amt);
            tx.setDbtr(dbtr);
            tx.setDbtrAcct(dbtrAcct);
            tx.setDbtrAgt(dbtrAgt);
            tx.setCdtrAcct(cdtrAcct);
            tx.setRmtInf(rmtInf);
            tx.setChrgBr("SLEV");

            // Attach transaction and group header into top-level transfer
            transfer.setGrpHdr(grpHdr);
            transfer.setCdtTrfTxInf(tx);

            // Attach top-level transfer into document root
            doc.setFiToFiCstmrCdtTrf(transfer);

            // Convert Java object to XML
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(doc, writer);

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