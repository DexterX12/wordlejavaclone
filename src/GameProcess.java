import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class GameProcess {
    
    
    public static String[] wordArray;
    public static String word;
    public static String userWord;
    public static Scanner entrada = new Scanner(System.in);


    public static boolean wannaPlay ()
    {
        System.out.println("¡Hola! ¿Deseas iniciar una nueva partida?");
        String userChoose = entrada.next(); 
        userChoose = userChoose.toLowerCase(); 

        if ( userChoose.equals("si"))
        {
          return true; 
        }

        else 
        {
            return false;
        }


    }

    private static void loadWords () {
        File file = new File("src/words.txt");
        String words = "";

        try {

            Scanner lector = new Scanner(file);

            while (lector.hasNextLine()) {
                words += lector.nextLine() + " ";
            }

            wordArray = words.split(" ");

            int randomNumber = (int) Math.floor((Math.random()*wordArray.length));
            word = wordArray[randomNumber].toUpperCase();

        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo de palabras. El programa no puede continuar...");
            System.exit(0);
        }
        
    }

    public static void game()
    {

      loadWords();

      System.out.print("\033[H\033[2J");
      System.out.println("Bienvenido. La palabra por adivinar ha sido generada, tiene un total de 6 intentos para adivinarla, ¡Buena suerte!.");
      System.out.println(" |".repeat(word.length()));
      System.out.print("Tu intento: ");

      int limite = 6; 
      int intentos = 0;
      boolean ganado = false;
      
      while (intentos<limite)
      {
        userWord = entrada.next();
        userWord = userWord.toUpperCase();

        
        if (userWord.length() == word.length())
        {
            boolean check1 = WordAnalyzer.checkIfWordsAreSame(GameProcess.word, GameProcess.userWord);

            if (check1 != true)
            {
                WordAnalyzer.showCurrentWordState(GameProcess.word, GameProcess.userWord);
                System.out.print("Tu intento: ");
                intentos++;
            }
        else 
            {
            WordAnalyzer.showCurrentWordState(GameProcess.word, GameProcess.userWord);
            ganado = true;
            break;
            }
        }

        else
        {
            System.out.print("\033[H\033[2J" + WordAnalyzer.ANSI_RESET);
            System.out.println(WordAnalyzer.graphicTries);
            System.out.println("El numero de letras de las palabras no coincide, vuelve a intentarlo");
            System.out.print("Tu intento: ");
        }  
      }
      
      if (!ganado)
      {
        System.out.println("Buen intento, pero no has podido esta vez. La palabra era: " + word);
      }

      else
      {
        System.out.println("Maquina, has ganado.");
      }

    }  
}