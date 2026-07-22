package com.example.raastqr.service;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.example.raastqr.service.PaymentTrackingService.Pacs002CallbackData;

@Service
public class Pacs002Service {

    public Pacs002CallbackData parseIncomingPacs002(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            org.w3c.dom.Document document = factory
                    .newDocumentBuilder()
                    .parse(new InputSource(new StringReader(xml)));

            Pacs002CallbackData data = new Pacs002CallbackData();
            data.setOriginalMsgId(readFirstTag(document, "OrgnlMsgId"));
            data.setOriginalInstrId(readFirstTag(document, "OrgnlInstrId"));
            data.setOriginalEndToEndId(readFirstTag(document, "OrgnlEndToEndId"));
            data.setOriginalTxId(readFirstTag(document, "OrgnlTxId"));
            data.setGroupStatus(readFirstTag(document, "GrpSts"));
            data.setTransactionStatus(readFirstTag(document, "TxSts"));
            data.setAccountServiceReference(readFirstTag(document, "AcctSvcrRef"));
            data.setRejectionReasonCode(readFirstChildTagUnder(document, "StsRsnInf", "Cd"));
            data.setRejectionReasonInfo(readFirstChildTagUnder(document, "StsRsnInf", "AddtlInf"));
            return data;
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to parse incoming pacs002 callback", ex);
        }
    }

    private String readFirstChildTagUnder(org.w3c.dom.Document document, String parentTagName, String childTagName) {
        org.w3c.dom.NodeList parents = document.getElementsByTagNameNS("*", parentTagName);
        if (parents.getLength() == 0) {
            parents = document.getElementsByTagName(parentTagName);
        }
        for (int i = 0; i < parents.getLength(); i++) {
            org.w3c.dom.Node parent = parents.item(i);
            org.w3c.dom.NodeList children = parent.getChildNodes();
            for (int j = 0; j < children.getLength(); j++) {
                org.w3c.dom.Node child = children.item(j);
                if (childTagName.equals(child.getLocalName()) || childTagName.equals(child.getNodeName())) {
                    return child.getTextContent();
                }
                org.w3c.dom.NodeList nestedChildren = child.getChildNodes();
                for (int k = 0; k < nestedChildren.getLength(); k++) {
                    org.w3c.dom.Node nestedChild = nestedChildren.item(k);
                    if (childTagName.equals(nestedChild.getLocalName()) || childTagName.equals(nestedChild.getNodeName())) {
                        return nestedChild.getTextContent();
                    }
                }
            }
        }
        return null;
    }

    private String readFirstTag(org.w3c.dom.Document document, String tagName) {
        org.w3c.dom.NodeList nodes = document.getElementsByTagNameNS("*", tagName);
        if (nodes.getLength() == 0) {
            nodes = document.getElementsByTagName(tagName);
        }
        if (nodes.getLength() == 0) {
            return null;
        }
        return nodes.item(0).getTextContent();
    }
}
