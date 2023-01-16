import dependency.DependencyContainer;
import entities.*;
import print.IPrint;
import receiver.dimensions.IDimensionsReceiver;
import receiver.strict.DisableStrictReceiver;
import receiver.strict.IStrictReceiver;
import receiver.word.DefaultNumberWordReceiver;
import receiver.word.IWordsReceiver;

public class Main {

    public static void main(String[] args) throws Exception {
        load();
        start();
    }

    private static void load(){
        //DependencyContainer.addInstance(IWordsReceiver.class, new DefaultNumberWordReceiver());
        DependencyContainer.addInstance(IStrictReceiver.class, new DisableStrictReceiver());
    }

    private static void start() throws Exception {
        IDimensionsReceiver dimensionsReceiver = DependencyContainer.getInstance(IDimensionsReceiver.class);
        IWordsReceiver wordsReceiver = DependencyContainer.getInstance(IWordsReceiver.class);
        IPrint printer = DependencyContainer.getInstance(IPrint.class);
        IStrictReceiver strictReceiver = DependencyContainer.getInstance(IStrictReceiver.class);

        Dimensions dimensions = dimensionsReceiver.getDimensions();
        String[] words = wordsReceiver.getWords();
        Boolean strict = strictReceiver.getStrict();

        Panel panel = new Panel(dimensions, words, strict);
        panel.print(printer);
    }

}