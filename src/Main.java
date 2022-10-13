public class Main {
    
    public static void main (String [] args)
    {
        boolean userChoice = GameProcess.wannaPlay();

        if (userChoice == true)
        {
            GameProcess.game();
        }
        
        else
        {
            System.out.println("Está bien.\n¡Nos vemos luego!");
        }


    }
}