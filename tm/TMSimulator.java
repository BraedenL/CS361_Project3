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
        if(args[0] == null)
        {
            return;
        }
        File inputFile = new File(args[0]);
        Scanner lineScanner = new Scanner(inputFile);
        if(!lineScanner.hasNext()){ 
            lineScanner.close();
            return;
        }
        String line = lineScanner.nextLine();

        //Use first scanned line for states
        totalStates = Integer.parseInt(line);
        if(!lineScanner.hasNext()){ 
            lineScanner.close();
            return;
        }
        line = lineScanner.nextLine();

        //Use second line for how many symbols in alphabet
        alphabetCount = Integer.parseInt(line);
        if(!lineScanner.hasNext()){ 
            lineScanner.close();
            return;
        }
        line = lineScanner.nextLine();

        totalTransitionCount = (totalStates-1) * (alphabetCount + 1);
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

        System.out.println(totalStates + " - " + alphabetCount + " - " + totalTransitionCount + " - " + transitionPerState);
        
        //Use a loop for remaining lines + addational scanner to build transitions
        Scanner characterScanner;
        int state, writeSymbol;
        String move;
        int transitionCharacterCounter = 0, fromStateTransitionCounter = 0;
        for (int i = 0; i < totalTransitionCount; i++)
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
            if(!move.equals("L") && !move.equals("R"))
            {
                lineScanner.close();
                characterScanner.close();
                return;
            }    
            
            TM.get(fromStateTransitionCounter).addNewTranInfo(move, transitionCharacterCounter, writeSymbol, TM.get(state));
            transitionCharacterCounter++;
            if(transitionCharacterCounter == transitionPerState)
            { 
                transitionCharacterCounter = 0;
                fromStateTransitionCounter++;
            }

            characterScanner.close();

            
            if(!lineScanner.hasNext()){ //this should only be possible after all transitions are read in and there is no string given
                //The file has no input string check printout 
                System.out.println("There is no provided input string, initializing string with all 0's");
                System.out.print("\n");//print a newline so user knows that the program didn't have an unexpected failure
                //lineScanner.close();
                break;
            }
            else
            {
                line = lineScanner.nextLine();
            }
            
            //System.out.println(i);
        }
        //Read through input string
        String inputString;
        int head = 0; 
        if(!line.contains(","))
        {
            //The machine has a string for input
            inputString = line;
            for (int c : inputString.toCharArray())
            {
                tape.add(Character.getNumericValue(c));
            }
        }
        else
        {
            //The machine does not have an input string
            tape.add(0);
        }
        
        //Set the tape to the values inside of the input string
        int currentState = 0;

        //System.out.println(tape);
        
        //////////////////////
        //// Reading Tape ////
        //////////////////////
        //Head will track where we are on tape, currentState track which state in the TM we are in
        
        //TM halts upon reaching accept no matter what, so why not just track where our state is and if we reach the end (biggest) state
        //Can add a check inside of the machine in case we run out of tape
        int testTracker = 0;
        while(currentState != totalStates-1)
        {
            //Print out each position in tape visited in the machine
            //System.out.println(tape.get(head).toString());
            testTracker++;
            if(TM.get(currentState).isTransition(tape.get(head)))
            {
                //Move the tape
                if(TM.get(currentState).getTapeDirection(tape.get(head)).equals("L"))
                {
                    //Checked if using a previous state counter was issue !!!
                    currentState = TM.get(currentState).getNextState(tape.get(head));
                    if(TM.get(currentState).isAcceptState())
                    {
                        //Program has reached the accepted state and rest of the tape and string can be ignored 
                        break;
                    }
                    tape.set(head, TM.get(currentState).getWriteValue(tape.get(head)));
                    //I THINK THIS ADD IS WRONG (changed to indexOf(head))
                    if(head == 0) {
                        tape.addFirst(0);
                    }
                    else{//I think this head-- needs checking where it is to decide if to move it, mainly when adding new 0
                    head--;
                    }
                }
                else if(TM.get(currentState).getTapeDirection(tape.get(head)).equals("R"))
                {
                    //CHECKED IF USING PREVIOUS STATE COUNTER WAS ISSUE !!!
                    currentState = TM.get(currentState).getNextState(tape.get(head));
                    if(TM.get(currentState).isAcceptState())
                    {
                        //Program has reached the accepted state and rest of the tape and string can be ignored 
                        break;
                    }
                    tape.set(head, TM.get(currentState).getWriteValue(tape.get(head)));
                    if(head == (tape.size() - 1)) {
                        tape.addLast(0);
                    }
                    head++;//since were adding a zero at the end we still need to shift the head to move into the new spot
                }
                //Need to move the current state
                //System.out.println(currentState + "and head is located at: " + head);
                //Might need handle so tape doesn't end up going off into nowhere?
                //System.out.print(head);
                System.out.println(tape);

                /*
                Moved to check immediatly when we change states. Don't need to change anything else in the machine once the proper state is reached
                if(TM.get(currentState).isAcceptState())
                {
                    //Program has reached the accepted state and rest of the tape and string can be ignored 
                    break;
                }
                */
                //Check for a loop, halt machine
                /*
                if(timeCheck >= totalTransitionCount)
                {
                    break;
                }
                */
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
