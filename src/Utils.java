public class Utils {
    public static int countLetterCoincidences (String word, char letter) {
        int totalCoincidences = 0;

        for (int i = 0; i < word.length(); i++) {
            char currentLetter = word.charAt(i);

            if (currentLetter == letter) {
                totalCoincidences++;
            }
        }

        return totalCoincidences;
    }

    public static String StrArraytoString (String[] array) {
        String text = "";

        for (int i = 0; i < array.length; i++) {
            text += array[i];
        }

        return text;
    }
}