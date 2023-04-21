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
        LinkedList<Integer> tape = new LinkedList<Integer>();
        //Main position tracker for the tape

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
        TM.get(totalStates-1).setIsAccept(true);

        
        //Use a loop for remaining lines + addational scanner to build transitions
        Scanner characterScanner;
        int state, writeSymbol;
        String move;
        int transitionCharacterCounter = 0, fromStateTransitionCounter = 0;
        for (int i = 0; i < (totalStates-1)*(transitionPerState); i++)
        {
            // System.out.println("here");

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
            if(!move.equals("L") && !move.equals("R"))
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
            // System.out.println("here");
        }
        
        //debuging
        //System.out.println("here");

        //Read through input string
        String inputString = lineScanner.next();

        //Set the tape to the values inside of the input string
        for (int c : inputString.toCharArray())
        {
            tape.add(c);
        }
        int head = 0;
        int currentState = 0;

        //////////////////////
        //// Reading Tape ////
        //////////////////////
        //Head will track where we are on tape, currentState track which state in the TM we are in
        
        //TM halts upon reaching accept no matter what, so why not just track where our state is and if we reach the end (biggest) state
        //Can add a check inside of the machine in case we run out of tape
        while(currentState != totalStates-1)
        {
            //Print out each position in tape visited in the machine
            System.out.println(tape.get(head).toString());

            if(TM.get(currentState).isTransition(head))
            {
                if(TM.get(currentState).getTapeDirection(head).equals("L"))
                {
                    head--;
                }
                if(TM.get(currentState).getTapeDirection(head).equals("R"))
                {
                    head++;
                }
                currentState = TM.get(head).getName();

                //Might need handle so tape doesn't end up going off into nowhere?

                if(TM.get(currentState).isAcceptState())
                {
                    //Program has reached the accepted state and rest of the tape and string can be ignored 
                    break;
                }
            }
        }

        //Print a blank line
        System.out.println();

        //Round out and close
        lineScanner.close();
    }

    //Just creating space
    public void function()
    {

    }
}
