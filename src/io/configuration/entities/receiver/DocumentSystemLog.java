package io.configuration.entities.receiver;

import core.java.module.log.interfaces.ILog;
import io.configuration.exception.ConfigurationException;
import org.w3c.dom.Document;

public class DocumentSystemLog extends AbstractDocumentSystem {

    static final String LOG_SYSTEM = "log";

    private DocumentSystemLog(Document document) throws ConfigurationException {
        super(document);
        buildLogSystem();
    }

    public static DocumentSystemLog getInstance(Document document) throws ConfigurationException{
        return new DocumentSystemLog(document);
    }

    private void buildLogSystem() throws ConfigurationException {
        buildModule(EntitySystemModule.class, DocumentSystemLog.LOG_SYSTEM);
    }

    public ILog getModuleInstance() throws ConfigurationException {
        return super.getInstance();
    }

}