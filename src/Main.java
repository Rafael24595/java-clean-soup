import dependency.DependencyContainer;
import entities.*;
import print.IPrint;
import receiver.dimensions.IDimensionsReceiver;
import receiver.word.IWordsReceiver;

public class Main {

    public static void main(String[] args) throws Exception {
        IDimensionsReceiver dimensionsReceiver = DependencyContainer.getInstance(IDimensionsReceiver.class);
        IWordsReceiver wordsReceiver = DependencyContainer.getInstance(IWordsReceiver.class);
        IPrint printer = DependencyContainer.getInstance(IPrint.class);

        Dimensions dimensions = dimensionsReceiver.getDimensions();
        String[] words = wordsReceiver.getWords();

        Panel panel = new Panel(dimensions, words);
        panel.print(printer);
    }

}