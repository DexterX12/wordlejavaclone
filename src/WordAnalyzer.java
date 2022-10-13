import java.util.HashMap;
import java.util.Map;

public class WordAnalyzer {

    /* Colores en ANSI, mediante escapes */
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static String graphicTries = "";

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

    // Devuelve todas las coincidencias de las letras en una colección de la forma letra:coincidencias

    private static Map<Character, Integer> getLettersCoincidences (String word) {
        Map<Character, Integer> lettersCoincidences = new HashMap<Character, Integer>();

        // Coloca todas las coincidencias de cada letra en una colección
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            /* Si ya se hizo la comparación de una letra previa, esto impide que se compare la misma
            letra varias veces */

            if (!lettersCoincidences.containsKey(letter)) {
                for (int j = 0; j < word.length(); j++) {
                    if (letter == word.charAt(j)){
                        lettersCoincidences.put(letter, lettersCoincidences.getOrDefault(letter, 0) + 1);
                    }
                }
            }
        }

        return lettersCoincidences;

    }

    /* 
        Imprime en pantalla el estado actual de la palabra ingresada por el usuario
        - Letras amarillas: Posición incorrecta
        - Letras normales: No existe en la palabra
        - Letras verdes: Posición correcta exacta
    */

    public static void showCurrentWordState (String word, String userWord) {
        Map<Character, Integer> lettersCoincidences = WordAnalyzer.getLettersCoincidences(word);

        char[] userWordLetters = userWord.toCharArray();
        String[] finalText = new String[word.length()];
        
        // Compara las posiciones 1-1 de ambas palabras, y coloca en verde las que coincidan
        // Las que no, se dejan en su color original.
        for (int i = 0; i < userWordLetters.length; i++) {
            if (userWordLetters[i] == word.charAt(i)) {
                finalText[i] = WordAnalyzer.ANSI_GREEN + userWordLetters[i];

                // Se disminuye la cantidad de coincidencias existentes
                lettersCoincidences.put(userWordLetters[i], lettersCoincidences.get(userWordLetters[i]) - 1);
            } else {
                finalText[i] = WordAnalyzer.ANSI_RESET + userWordLetters[i];
            }
        }


        for (int i = 0; i < userWordLetters.length; i++) {
            char letter = userWordLetters[i];    
            boolean doesLetterExist = word.contains(Character.toString(letter));
            
            // Comprueba si la letra no existe en la palabra original
            if (!doesLetterExist) {
                finalText[i] = WordAnalyzer.ANSI_RESET + letter;
            
            // Comprueba si existen coincidencias de una letra
            } else if (lettersCoincidences.get(letter) > 0) {
                boolean letterAlreadyInRightPos = finalText[i].contains(WordAnalyzer.ANSI_GREEN);

                // Verifica si ya se comprobó antes que la letra en la posición actual sea exacta a la original
                if (!letterAlreadyInRightPos) {
                    finalText[i] = WordAnalyzer.ANSI_YELLOW + letter;
                    lettersCoincidences.put(userWordLetters[i], lettersCoincidences.get(userWordLetters[i]) - 1);                
                }

            }
        }

        WordAnalyzer.graphicTries += Utils.stringArraySeparator(finalText) + "\n";
        System.out.print("\033[H\033[2J" + ANSI_RESET);
        System.out.println(WordAnalyzer.graphicTries);
        
    }
}
