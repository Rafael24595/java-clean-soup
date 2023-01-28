package io.configuration.entities.receiver;

import io.configuration.entities.AbstractDocument;
import io.configuration.entities.receiver.interfaces.IModule;
import io.configuration.exception.ConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.lang.reflect.InvocationTargetException;

import static io.configuration.tools.XmlTools.*;

abstract class AbstractDocumentModule extends AbstractDocument {

    private String code;

    protected AbstractDocumentModule(Document document) {
        super(document);
    }

    public String getCode() {
        return code;
    }

    protected void buildDefaultModule(String tag) {
        buildName(tag);
    }
    protected <T extends IModule> T buildModule(Class<? extends IModule> clazz, Element element) throws ConfigurationException {
        boolean status = getElementStatus(element);
        T instance = null;

        if(status){
            try {
                instance = (T) clazz.getDeclaredConstructor().newInstance();
                instance.build(element);
                setParameters(element, instance);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                     | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                throw new ConfigurationException(e);
            }
        }

        return instance;
    }

    protected String buildName(String tag) {
        Element receiverTag = getModuleTagElement(tag);
        this.code = getTagChildText(receiverTag, AbstractEntityModule.NAME);
        return this.code;
    }

    protected abstract Element getModuleTagElement(String tag);

}