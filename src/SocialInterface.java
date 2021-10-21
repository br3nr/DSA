// FILE: SocialInterface.java
// AUTHOR: Max Barker (19624729)
// USERNAME: BigMoney
// UNIT: DSA
// PURPOSE: To act as the Menu for SocialSim 
// REQUIRES: Graph.java, SocialSim.java 
// Last Mod: 28 OCT 2019

import java.io.*;
import java.util.*;
public class SocialInterface
{
     
    public static void main(String[] args)
    {
        SocialSim sim;
        //checks for empty args
        if(args.length < 1)
        {
            System.out.println("SocialSim:\n   -i  Interactive Mode\n   -s Simulation Mode");
        }
        //if -i, enter interactive mode
        else if(args[0].equals("-i")) 
        {
            sim = new SocialSim();
            initialize(sim);
        }

        //if -s, enter simulation mode
        else if((args[0]).equals("-s"))
        {
            clear();
            //used for printing execution time
            long startTime, endTime;
            startTime = System.currentTimeMillis();
            
            double like = Double.parseDouble(args[3]);
            double follow = Double.parseDouble(args[4]);
            
            //creates sim based off like and follow probabilities
            sim = new SocialSim(like, follow);
            initSimulator(sim, args);    

            endTime = System.currentTimeMillis();
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
        }
    }


    // NAME: initSimulator 
    // PURPOSE: loads the network and event files into the Simulation object
    // IMPORTS:  SocialSim object as initialised above, String[] array of command line args
    // EXPORTS:  void
    public static void initSimulator(SocialSim sim, String[] simArgs)
    {
        sim.loadNetwork(simArgs[1]);  
        sim.loadEvent(simArgs[2]);
    }
 
    // NAME:
    // PURPOSE:
    // IMPORTS:
    // EXPORTS:

    // NAME: initialize
    // PURPOSE: Initializes the SocialSim object for interactive mode
    // IMPORTS: Social sim object
    // EXPORTS: void
    public static void initialize(SocialSim sim)
    {
        clear();
        System.out.println("Before we begin, I need a text file:");
        String fileName = getStringInput();

        if(checkFile(fileName) == true)
        {
            System.out.println("The file was not found. Check your directory and try again.") ;
        }
        else
        {
            sim.loadNetwork(fileName);
            menu(sim);
        }
    }

    // NAME: displayMenu
    // PURPOSE: Simple set of print statements for the menu
    // IMPORTS: none 
    // EXPORTS: void
    public static void displayMenu()
    {
        //menu for displaying interactive mode options
        
        //clears terminal
        clear();

        System.out.println("~~ Welcome to SocialSim ~~");
        System.out.println("  (1) Set Probabilities");
        System.out.println("  (2) Node operations");
        System.out.println("  (3) Edge Operations");
        System.out.println("  (4) New post");
        System.out.println("  (5) Display network");
        System.out.println("  (6) Display Statistics");
        System.out.println("  (7) Update");
        System.out.println("  (8) Save network");
        System.out.println("  (9) Exit");
    }

    // NAME: menu
    // PURPOSE: Loops in switch case statement to interact with the SocialSim object 
    // IMPORTS: SocialSim object
    // EXPORTS: void
    public static void menu(SocialSim sim)
    {
        int choice;
        boolean exit = false; 

        //keep calling the menu until exit statement
        do
        {
            displayMenu();
            choice = getInput();
            displayMenu();
            
            //switch the choices in the menu 
            switch(choice)
            {
                case 1 :
                    sim.setProbability();
                    break;
                case 2 :
                    sim.nodeOperation();
                    break;
                case 3 :
                    sim.edgeOperation();
                    break;
                case 4 :
                    sim.newPost();
                    pressEnter();
                    break;
                case 5 :
                    sim.displayNetwork();
                    pressEnter();
                    break;
                case 6 : 
                    sim.displayStatistics();
                    pressEnter();
                    break;
                case 7 : 
                    sim.timeStep();
                    pressEnter();
                    break;
                case 8 :
                    sim.saveNetwork();
                    break;
                case 9 :
                    exit = true;
                    break;
                
                default : 
                    break;
            }
        }
        while(!exit);
    }


    // NAME: clear
    // PURPOSE: clears the terminal for pretty UI
    // IMPORTS: none
    // EXPORTS: void
    public static void clear()
    {
        System.out.print("\033[H\033[2J");
    }


    // NAME: getInput
    // PURPOSE: gets an integer input from the user using Scanner
    // IMPORTS: none
    // EXPORTS: int from terminal
    public static int getInput()
    {
        int readInt = 0;
        try
        {
            Scanner sc = new Scanner(System.in);
            readInt = sc.nextInt();
        }
        catch(InputMismatchException e)
        {
            System.out.println("Illegal input. Try again:\n");
            pressEnter();
        }
        return readInt;
    }
    

    // NAME: getStringInput
    // PURPOSE: gets a string input from user
    // IMPORTS: none
    // EXPORTS: String from terminal
    public static String getStringInput()
    {
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        return input;
    }

       
    // NAME: checkFile
    // PURPOSE: checks if the fileName exists
    // IMPORTS: String
    // EXPORTS: Boolean
    public static boolean checkFile(String fileName)
    {

        boolean found;
        File tempFile = new File("fileName");
        found = tempFile.exists();
        return found;
    }
    

    // NAME: pressEnter
    // PURPOSE: waits for an input in the buffer
    // IMPORTS: none
    // EXPORTS: void
    public static void pressEnter()
    {
        System.out.println("\n\nPress ENTER to continue.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

}
