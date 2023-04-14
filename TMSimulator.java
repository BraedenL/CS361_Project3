import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class TMSimulator {
    //Main class to create and manage the Turing Machine

    //Handle the tape using a doubly linked list, using LinkedList java class

    public static void main(String[] args) throws FileNotFoundException {

        //Handle the tape using a doubly linked list, using LinkedList java class
        LinkedList tape = new LinkedList<>();

        //First thing, check that a file has been given
        if(args[0].isEmpty())
        {
            return;
        }
        File inputFile = new File(args[0]);
        Scanner lineScanner = new Scanner(inputFile);
        String line = lineScanner.nextLine();

        //Use first scanned line for states


        line = lineScanner.nextLine();
        //Use second line for how many symbols in alphabet


        line = lineScanner.nextLine()
        //Use a loop for remaining lines + addational scanner to build transitions
        while(lineScanner.hasNextLine())
        {
            
        }
          

    }

    //Just creating space
    public void function()
    {

    }
}
