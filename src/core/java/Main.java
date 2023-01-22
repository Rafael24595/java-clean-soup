package core.java;

import core.java.entities.Panel;
import core.java.tools.Tools;
import io.configuration.Configuration;
import core.java.dependency.DependencyContainer;
import core.java.print.IPrint;
import core.java.receiver.dimensions.instance.IDimensionsReceiver;
import core.java.receiver.strict.instance.IStrictReceiver;
import core.java.receiver.word.instance.IWordReceiver;

public class Main {

    public static void main(String[] args) throws Exception {
        loadAppConfiguration(args);
        loadGlobalDependencies();

        for (int i = 0; i < Configuration.wordReceiverLength(); i++) {
            loadInstanceDependencies(i);
            launch();
        }
    }

    private static void loadAppConfiguration(String[] args) throws Exception {
        Arguments.initialize(args);
        Configuration.initialize(Arguments.customConfigurationFile());
    }

    private static void loadGlobalDependencies() throws Exception {
        IStrictReceiver strictReceiver = Configuration.getStrictReceiverInstance();
        IPrint print = Configuration.getPrinterInstance();

        DependencyContainer.addInstance(IStrictReceiver.class, strictReceiver);
        DependencyContainer.addInstance(IPrint.class, print);
    }

    private static void loadInstanceDependencies(int index) throws Exception {
        IWordReceiver[] wordReceivers = Configuration.getWordReceiverInstances();
        IDimensionsReceiver[] dimensionsReceivers = Configuration.getDimensionsReceiverInstances();

        IWordReceiver wordReceiver = Tools.getPosition(wordReceivers, index);
        IDimensionsReceiver dimensionsReceiver = Tools.getPosition(dimensionsReceivers, index);

        DependencyContainer.addInstance(IDimensionsReceiver.class, dimensionsReceiver);
        DependencyContainer.addInstance(IWordReceiver.class, wordReceiver);
    }

    private static void launch() throws Exception {
        IDimensionsReceiver dimensionsReceiver = DependencyContainer.getInstance(IDimensionsReceiver.class);
        IWordReceiver wordsReceiver = DependencyContainer.getInstance(IWordReceiver.class);
        IPrint printer = DependencyContainer.getInstance(IPrint.class);
        IStrictReceiver strictReceiver = DependencyContainer.getInstance(IStrictReceiver.class);

        Panel panel = new Panel(dimensionsReceiver, wordsReceiver, strictReceiver);
        panel.print(printer);
    }

}