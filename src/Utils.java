public class Utils {
    public static String StrArraytoString (String[] array) {
        String text = "";

        for (int i = 0; i < array.length; i++) {
            text += array[i];
        }

        return text;
    }
}