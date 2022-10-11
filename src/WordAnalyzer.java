import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordAnalyzer {

    /* Colores en ANSI, mediante escapes */
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    // Comprueba si las palabras son idénticas

    public static boolean checkIfWordsAreSame (String word, String userWord) {
        char[] userWordLetters = userWord.toCharArray();
        int totalCoincidences = 0;

        for (int i = 0; i < userWordLetters.length; i++) {
            char currentComparingLetter = word.charAt(i);
            
            if (userWordLetters[i] == currentComparingLetter) {
                totalCoincidences++;
            }
        }

        if (totalCoincidences == word.length())
            return true;

        return false;
    }

    // Devuelve todas las coincidencias de las letras en una colección de la forma llave:valor

    private static Map<Character, ArrayList<Integer>> getLettersCoincidences (String word) {
        Map<Character, ArrayList<Integer>> lettersCoincidences = new HashMap<Character, ArrayList<Integer>>();

        // Coloca todas las coincidencias de cada letra en una colección

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            ArrayList<Integer> coincidences = new ArrayList<Integer>();

            /* Si ya se hizo la comparación de una letra previa, esto impide que se compare la misma
            letra varias veces */
            if (!lettersCoincidences.containsKey(letter)) {
                for (int j = 0; j < word.length(); j++) {
                    if (letter == word.charAt(j)){
                        coincidences.add(j);
                        lettersCoincidences.put(letter, coincidences);
                    }
                }
            }
        }

        return lettersCoincidences;

    }

    /* Imprime en pantalla el estado actual de la palabra ingresada por el usuario
        - Letras amarillas: Posición incorrecta
        - Letras normales: No existe en la palabra
        - Letras verdes: Posición correcta exacta
    */

    public static void showCurrentWordState (String word, String userWord) {
        Map<Character, ArrayList<Integer>> lettersCoincidences = WordAnalyzer.getLettersCoincidences(word);

        char[] userWordLetters = userWord.toCharArray();
        String[] finalText = new String[word.length()];
        
        // Compara las posiciones 1-1 de ambas palabras, y coloca en verde las que coincidan
        // Las que no, se dejan en su color original.

        for (int j = 0; j < word.length(); j++) {
            if (userWordLetters[j] == word.charAt(j)) {
                finalText[j] = WordAnalyzer.ANSI_GREEN + userWordLetters[j];
                lettersCoincidences.get(userWordLetters[j]).remove(0);
            } else {
                finalText[j] = WordAnalyzer.ANSI_RESET + userWordLetters[j];
            }
        }


        for (int i = 0; i < userWordLetters.length; i++) {
            char letter = userWordLetters[i];

            // Comprueba si la letra existe en la palabra original, si no es cierto, se deja el color original.

            if (!word.contains(Character.toString(letter))) {
                finalText[i] = WordAnalyzer.ANSI_RESET + letter;
            
            /* Comprueba si existen coincidencias de una letra, y si la posición de esa coincidencia es distinta
            a la posición del iterador*/

            } else if (lettersCoincidences.get(letter).size() > 0 && lettersCoincidences.get(letter).get(0) != i) {
                finalText[i] = WordAnalyzer.ANSI_YELLOW + letter;
                lettersCoincidences.get(letter).remove(0);
            }
        }
        
        System.out.println(Utils.StrArraytoString(finalText));
    }
}
