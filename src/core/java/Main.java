package core.java;

import core.java.entities.Dimensions;
import core.java.entities.Panel;
import core.java.tools.Tools;
import io.configuration.Configuration;
import core.java.dependency.DependencyContainer;
import core.java.print.IPrint;
import core.java.receiver.dimensions.IDimensionsReceiver;
import core.java.receiver.strict.IStrictReceiver;
import core.java.receiver.word.IWordReceiver;

public class Main {

    public static void main(String[] args) throws Exception {
        load();
    }

    private static void load() throws Exception {
        Configuration.initialize();
        IWordReceiver[] iwr = Configuration.getWordReceiverInstances();
        IDimensionsReceiver[] idr = Configuration.getDimensionsReceiverInstances();
        IStrictReceiver isr = Configuration.getStrictReceiverInstance();
        IPrint icr = Configuration.getPrinterInstance();

        DependencyContainer.addInstance(IStrictReceiver.class, isr);
        DependencyContainer.addInstance(IPrint.class, icr);

        for (int i = 0; i < iwr.length; i++) {
            IWordReceiver wordReceiver = iwr[i];
            IDimensionsReceiver dimensionsReceiver = Tools.getPosition(idr, i);

            DependencyContainer.addInstance(IDimensionsReceiver.class, dimensionsReceiver);
            DependencyContainer.addInstance(IWordReceiver.class, wordReceiver);

            start();
        }
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