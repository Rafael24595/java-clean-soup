package core.java;

import core.java.entities.Panel;
import core.java.tools.Tools;
import io.configuration.Configuration;
import core.java.dependency.DependencyContainer;
import core.java.print.IPrint;
import core.java.receiver.dimensions.instance.IDimensionsReceiver;
import core.java.receiver.strict.instance.IStrictReceiver;
import core.java.receiver.word.instance.IWordReceiver;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        start(args);
    }

    private static void start(String[] args) throws Exception {
        File file = getArgsCustomConfigFile(args);
        Configuration.initialize(file);
        IWordReceiver[] wordReceivers = Configuration.getWordReceiverInstances();
        IDimensionsReceiver[] dimensionsReceivers = Configuration.getDimensionsReceiverInstances();
        IStrictReceiver strictReceiver = Configuration.getStrictReceiverInstance();
        IPrint print = Configuration.getPrinterInstance();

        DependencyContainer.addInstance(IStrictReceiver.class, strictReceiver);
        DependencyContainer.addInstance(IPrint.class, print);

        for (int i = 0; i < wordReceivers.length; i++) {
            IWordReceiver wordReceiver = wordReceivers[i];
            IDimensionsReceiver dimensionsReceiver = Tools.getPosition(dimensionsReceivers, i);

            DependencyContainer.addInstance(IDimensionsReceiver.class, dimensionsReceiver);
            DependencyContainer.addInstance(IWordReceiver.class, wordReceiver);

            launch();
        }
    }

    private static void launch() throws Exception {
        IDimensionsReceiver dimensionsReceiver = DependencyContainer.getInstance(IDimensionsReceiver.class);
        IWordReceiver wordsReceiver = DependencyContainer.getInstance(IWordReceiver.class);
        IPrint printer = DependencyContainer.getInstance(IPrint.class);
        IStrictReceiver strictReceiver = DependencyContainer.getInstance(IStrictReceiver.class);

        Panel panel = new Panel(dimensionsReceiver, wordsReceiver, strictReceiver);
        panel.print(printer);
    }

    private static File getArgsCustomConfigFile(String[] args) {
        File file = null;
        if(args.length > 0){
            String path = args[0];
            file = new File(path);
        }

        return file != null && file.exists() ? file : null;
    }

}