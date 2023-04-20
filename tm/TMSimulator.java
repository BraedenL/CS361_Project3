import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TMSimulator {
    //Main class to create and manage the Turing Machine

    public static void main(String[] args) throws FileNotFoundException {

        //Handle the tape using a doubly linked list, using LinkedList java class
        LinkedList<String> tape = new LinkedList<String>();
        //Main position tracker for the tape
        //String head = tape.getFirst();

        //Other instance variables
        int totalStates, alphabetCount, totalTransitionCount, transitionPerState;
        //HashMap<TMState, TranInfo> TM = new HashMap<TMState, TranInfo>();
        ArrayList<TMState> TM;

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

        totalTransitionCount = (totalStates-1) * alphabetCount;
        transitionPerState = alphabetCount + 1;

        //Read needed info for array
        TM = new ArrayList<TMState>(totalStates);
        int stateCounter = 0;
        //first fill the array with empty states
        for (int i = 0; i < totalStates; i++) {
            TMState s = new TMState();
            TM.add(s);
        }
        for (TMState tmState : TM) 
        {
            tmState.setName(stateCounter);
            stateCounter++;    
        }
        TM.get(0).setIsStart(true);
        TM.get(totalStates-1).setIsAcc(true);

        //Use a loop for remaining lines + addational scanner to build transitions
        Scanner characterScanner;
        int state, writeSymbol;
        String move;
        int transitionCharacterCounter = 0, fromStateTransitionCounter = 0;
        while(lineScanner.hasNextLine())
        {
            TMState tempState;
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

            //Reading the tape direction
            move = characterScanner.next();
            if(move.equals("L") || move.equals("R"))
            {
                lineScanner.close();
                characterScanner.close();
                return;
            }    
            
            TM.get(fromStateTransitionCounter).addNewTranInfo(move, transitionCharacterCounter, writeSymbol, TM.get(state));
            transitionCharacterCounter++;
            if(transitionCharacterCounter > transitionPerState)
            { 
                transitionCharacterCounter = 0;
                fromStateTransitionCounter++;
            }

            characterScanner.close();
        }
          
        //Read through input string


        lineScanner.close();
    }

    //Just creating space
    public void function()
    {

    }
}
