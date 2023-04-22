import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
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
        int previousState;
        ListIterator<Integer> headIter = tape.listIterator(0);
        int currentElement = headIter.next();
        int lastMove = 1; // 0 represents the last move being a previous and a 1 represents the last move being a next
        while(currentState != totalStates-1)
        {
            //Print out each position in tape visited in the machine
            //System.out.println(tape.get(head).toString());
            // if(tape.size() == 138){
            //     System.out.println("made it");
            // }
            // if(head == 10){
            //     System.out.println("made it back");
            // }
            testTracker++;
            if(TM.get(currentState).isTransition(currentElement))
            {
                //Move the tape
                if(TM.get(currentState).getTapeDirection(currentElement).equals("L"))
                {
                    // System.out.print(tape.get(head));

                    previousState = currentState;
                    //Checked if using a previous state counter was issue!!!
                    currentState = TM.get(currentState).getNextState(currentElement);
                    headIter.set(TM.get(previousState).getWriteValue(currentElement));
                    if(TM.get(currentState).isAcceptState())
                    {
                        //Program has reached the accepted state and rest of the tape and string can be ignored 
                        break;
                    }
                    if(head == 0) {
                        //if last call was a next, need to make sure iter is before the first element in list before adding, so call a previous
                        if(lastMove == 1){
                            currentElement = headIter.previous();//techincally this shouldn't change the value of currentElement (use for debugging)
                            lastMove = 0; //since we just went previous, need to update the last move variable
                        }
                        headIter.add(0);
                        // tape.addFirst(0);
                    }
                    else{
                        head--; 
                        //since we are moving back by one, we need to call prev on iter once no matter what
                        currentElement = headIter.previous();
                        //but if the last move used to find currentElement, was a next then we need to call previous twice to update currentElement correctly
                        if(lastMove == 1) {
                            currentElement = headIter.previous();
                        }
                        lastMove = 0; //update that we moved back wether if statement was executed or not
                    }
                }
                else if(TM.get(currentState).getTapeDirection(currentElement).equals("R"))
                {
                    // System.out.print(tape.get(head));

                    previousState = currentState;
                    //CHECKED IF USING PREVIOUS STATE COUNTER WAS ISSUE !!!
                    currentState = TM.get(currentState).getNextState(currentElement);
                    
                    headIter.set(TM.get(previousState).getWriteValue(currentElement));
                    if(TM.get(currentState).isAcceptState())
                    {
                        //Program has reached the accepted state and rest of the tape and string can be ignored 
                        break;
                    }
                    if(head == (tape.size() - 1)) {
                        //if last call was a previous, need to make sure iter is after the last element in list before adding, so call a next
                        if(lastMove == 0){
                            currentElement = headIter.next();//techincally this shouldn't change the value of currentElement (use for debugging)
                            lastMove = 1; //since we just went next, need to update the last move variable
                        }
                        headIter.add(0);
                        // tape.addLast(0);
                    }
                    head++;//since were adding a zero at the end we still need to shift the head to move into the new spot
                    //since we are moving forward by one, we need to call next on iter once no matter what
                    currentElement = headIter.next();
                    //but if the last move used to find currentElement, was a previous then we need to call next twice to update currentElement correctly
                    if(lastMove == 0) {
                        currentElement = headIter.next();
                    }
                    lastMove = 1; //update that we moved forward wether if statement was executed or not
                }
                //Need to move the current state
                //System.out.println(currentState + "and head is located at: " + head);
                //Might need handle so tape doesn't end up going off into nowhere?
                //System.out.print(head);
                // System.out.println(tape);
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

        System.out.println("");
        System.out.println(tape);
        int count = 0;
        for (int t : tape) {
            count = count + t;
        }
        System.out.println("\n" + count);
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
