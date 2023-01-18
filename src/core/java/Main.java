package core.java;

import core.java.entities.Dimensions;
import core.java.entities.Panel;
import io.configuration.Configuration;
import org.xml.sax.SAXException;
import core.java.dependency.DependencyContainer;
import core.java.print.IPrint;
import core.java.receiver.dimensions.IDimensionsReceiver;
import core.java.receiver.strict.IStrictReceiver;
import core.java.receiver.word.IWordReceiver;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws Exception {
        load();
        //start();
    }

    private static void load() throws IOException, ParserConfigurationException, SAXException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Configuration.initialize();
        IWordReceiver[] iwr = Configuration.getWordReceiverInstances();
        IStrictReceiver[] isr = Configuration.getStrictReceiverInstances();
        //DependencyContainer.addInstance(IWordsReceiver.class, new RAEWordReceiver(5));
        //DependencyContainer.addInstance(IWordsReceiver.class, new DefaultNumberWordReceiver());
        //DependencyContainer.addInstance(IStrictReceiver.class, new DisableStrictReceiver());
    }

    private static void start() throws Exception {
        IDimensionsReceiver dimensionsReceiver = DependencyContainer.getInstance(IDimensionsReceiver.class);
        IWordReceiver wordsReceiver = DependencyContainer.getInstance(IWordReceiver.class);
        IPrint printer = DependencyContainer.getInstance(IPrint.class);
        IStrictReceiver strictReceiver = DependencyContainer.getInstance(IStrictReceiver.class);

        Dimensions dimensions = dimensionsReceiver.getDimensions();
        String[] words = wordsReceiver.getWords();
        Boolean strict = strictReceiver.getStrict();

        Panel panel = new Panel(dimensions, words, strict);
        panel.print(printer);
    }

}