package print;

import entities.Panel;

public class ConsolePrint implements IPrint {

    public ConsolePrint() {
    }
    @Override
    public void print(Panel panel) {
        printPanel(panel);
        printWordList(panel);
    }

    private void printPanel(Panel panel) {
        int height = panel.getHeight();

        System.out.println();
        System.out.println("Soup panel: ");
        System.out.println();

        for (int i = 0; i < height; i++) {
            Character[] column = panel.getColumn(i);
            for (int k = 0; k < column.length; k++) {
                Character character = column[k];
                if(k == 0)
                    System.out.print(" ");
                System.out.print(character);
                if(k < column.length - 1)
                    System.out.print('-');
            }
            System.out.println();
        }
    }

    private void printWordList(Panel panel) {

        System.out.println();
        System.out.println("Word list: ");

        for (String word: panel.getWords()) {
            System.out.println(" -" + word);
        }
    }

}
