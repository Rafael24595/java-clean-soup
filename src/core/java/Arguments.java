package core.java;

import io.configuration.exception.ConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

class Arguments {

    private static final String DEFAULT_PATH_CONFIG = "src/io/configuration/resources/Configuration.xml";

    static Arguments instance;

    String[] args;

    private Arguments(String[] args){
        this.args = args;
    }

    static void initialize(String[] args) {
        instance = new Arguments(args);
    }

    static Document customDocument() throws ConfigurationException {
        if(instance != null && instance.args.length > 0){
            String arg = instance.args[0];
            try {
                return parse(arg);
            } catch (ConfigurationException e) {
                return read(arg);
            }
        }
        throw new ConfigurationException("Not defined.");
    }

    private static boolean isDefaultConfigFile(File file) {
        String fileDef = new File(DEFAULT_PATH_CONFIG).getAbsolutePath();
        String filePro = file.getAbsolutePath();
        return fileDef.equals(filePro);
    }

    private static Document read(String path) throws ConfigurationException {
        File file = new File(path);
        try {
            return read(file);
        } catch (ConfigurationException e) {
            throw new ConfigurationException(e);
        }
    }

    private static Document read(File filePro) throws ConfigurationException {
        if(filePro != null && filePro.exists()) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                DocumentBuilder builder;
                builder = factory.newDocumentBuilder();

                Document doc = builder.parse(filePro);
                doc.getDocumentElement().normalize();
                return doc;
            } catch (ParserConfigurationException e) {
                throw new ConfigurationException(e);
            } catch (SAXException | IOException e){
                if(isDefaultConfigFile(filePro))
                    return null;
            }
        }

        return read(DEFAULT_PATH_CONFIG);
    }

    private static Document parse(String rawConfig) throws ConfigurationException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(rawConfig)));
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ConfigurationException(e);
        }
    }

}