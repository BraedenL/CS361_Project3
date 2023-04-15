import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TMSimulator {
    //Main class to create and manage the Turing Machine

    public static void main(String[] args) throws FileNotFoundException {

        //Handle the tape using a doubly linked list, using LinkedList java class
        LinkedList<String> tape = new LinkedList<String>();
        //Main position tracker for the tape
        String head = tape.getFirst();

        //Other instance variables
        int totalStates;
        int alphabetCount;
        HashMap<TMState, TranInfo> TM = new HashMap<TMState, TranInfo>();

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
        line = lineScanner.nextLine();

        //Use a loop for remaining lines + addational scanner to build transitions
        Scanner characterScanner;
        int state, writeSymbol;
        String move;
        while(lineScanner.hasNextLine())
        {
            characterScanner = new Scanner(line).useDelimiter(",");
            //For each line, we need to check three characters
            
            //First will tell use what state to start on
            state = characterScanner.nextInt();            
            
            //Check to make sure state is a legal state
            if(state < 0 || state > totalStates - 1)
            {
                lineScanner.close();
                characterScanner.close();
                return;
            }

            //Loop through the total number of states in the machine
            writeSymbol = characterScanner.nextInt();
            //Check if the symbol to be written is legal
            if(writeSymbol < 0 || writeSymbol > alphabetCount)
            {
                lineScanner.close();
                characterScanner.close();
                return;
            }

            //Second state will tell us the transition char

            
            
            characterScanner.close();
        }
          

        lineScanner.close();
    }

    //Just creating space
    public void function()
    {

    }
}
