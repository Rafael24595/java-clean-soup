package io.configuration.entities.receiver;

import core.java.module.print.interfaces.IPrint;
import io.configuration.exception.ConfigurationException;

import org.w3c.dom.Document;

public class DocumentSystemPrint extends AbstractDocumentSystem {

    private DocumentSystemPrint(Document document) throws ConfigurationException {
        super(document);
        buildPrinterSystem();
    }

    public static DocumentSystemPrint getInstance(Document document) throws ConfigurationException {
        return new DocumentSystemPrint(document);
    }

    private void buildPrinterSystem() throws ConfigurationException {
        buildModule(EntitySystemPrint.class, EntitySystemPrint.PRINTER_SYSTEM);
    }

    public IPrint getModuleInstance() throws ConfigurationException {
        return super.getInstance();
    }

}