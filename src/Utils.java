public class Utils {
    public static String StrArraytoString (String[] array) {
        String text = "";

        for (int i = 0; i < array.length; i++) {
            text += array[i];
        }

        return text;
    }
    public static String stringArraySeparator (String[] array) {
        String text = "";

        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                text += array[i] + WordAnalyzer.ANSI_RESET + "|";
            }  else {
                text +=  array[i] + WordAnalyzer.ANSI_RESET;
            }
        }

        return text;
    }
}