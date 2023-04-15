import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class TMSimulator {
    //Main class to create and manage the Turing Machine

    //Handle the tape using a doubly linked list, using LinkedList java class

    public static void main(String[] args) throws FileNotFoundException {

        //Handle the tape using a doubly linked list, using LinkedList java class
        LinkedList<String> tape = new LinkedList<String>();
        //Main position tracker for the tape
        String head = tape.getFirst();

        //Other instance variables
        int totalStates;
        int alphabetCount;

        //First thing, check that a file has been given
        if(args[0].isEmpty())
        {
            return;
        }
        File inputFile = new File(args[0]);
        Scanner lineScanner = new Scanner(inputFile);
        String line = lineScanner.nextLine();

        //Use first scanned line for states
        totalStates = Integer.parseInt(line);
        line = lineScanner.nextLine();

        //Use second line for how many symbols in alphabet
        alphabetCount = Integer.parseInt(line);
        line = lineScanner.nextLine()

        //Use a loop for remaining lines + addational scanner to build transitions
        Scanner characterScanner;
        int state;
        Character LorR;
        while(lineScanner.hasNextLine())
        {
            characterScanner = new Scanner(line).useDelimiter(",");
            state = characterScanner.nextInt();
            //For each line, we need to check three characters
            //First will tell use what state to start on
            if(state < 0 || state > totalStates - 1)
            {
                return;
            }


            //Second state will tell us the transition char
            state = characterScanner.nextInt();
            

        }
          

    }

    //Just creating space
    public void function()
    {

    }
}
