package core.java.tools;

public class Tools {

    public static  <T> T getPosition(T[] list, int i) {
        if(list != null){
            int index = Tools.fitIndex(list, i);
            if(index < list.length)
                return list[index];
        }
        return null;
    }

    public static int fitIndex(Object[] list, int index) {
        if(list.length == 0)
            return 0;
        if(index < list.length)
            return index;
        double percentageX00 = index / list.length;
        double percentage100 = Math.floor(percentageX00);
        return (int) (list.length * (percentageX00 - percentage100));
    }

}