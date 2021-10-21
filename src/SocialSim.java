//FILE: SocialSim.java                                                                            
//AUTHOR: Max Barker (19624729)                                                                    
//USERNAME: BigMoney                                                                    
//UNIT: DSA                                                              
//PURPOSE: Contains the functions to manipulate 
//the network and run a simulation                                                 
//REQUIRES: SocialInterface.java Graph.java              
//Last Mod: 28 OCT 2019

import java.io.*;
import java.util.*;
public class SocialSim
{
    
    private Graph network;
    private int counter;

    //default constructor
    public SocialSim()
    {
        network = new Graph();
    }

    //alternate constructor for simulation mode 
    public SocialSim(double probLike, double probFollow)
    {
        network = new Graph(probLike, probFollow);
        counter = 0;
    }

    //calls displayList in network
    public void displayNetwork()
    {
        network.displayList();
    }

    // NAME: setProbability
    // PURPOSE: alters the network probability class fields from user
    // IMPORTS: none
    // EXPORTS: void
    public void setProbability()
    {
        Scanner sc = new Scanner(System.in);     
        double probLike, probFollow;
        //Scan for probability like 
        System.out.println("Enter the probability that a post is liked::");
        probLike = sc.nextDouble();
        //Scan for probability follow 
        System.out.println("Enter the probability that a User is followed:");
        probFollow = sc.nextDouble();
        //set the variables as class fields
        network.setProbLike(probLike);
        network.setProbFollow(probFollow); 
    }

    public void nodeOperation() 
    {
        System.out.println("What kind of Operation would you like to perform:");
        System.out.println(" 1) Insert\n 2) Find Node\n 3) Delete");
        int input = getIntInput();
        if(input == 1)
        {
            System.out.println("Who do you want to add to the list?");
            String name = getInput();
            network.addVertex(name); 
        }
        if(input == 2)
        {
            System.out.println("Who are you looking for?");
            String name = getInput();
            boolean found = network.hasVertex(name);
            if(found)
            {
                System.out.println(name + " is in the network!");
                pressEnter();
            }
            else
            {
                System.out.println(name + " is not in the network.");
                pressEnter();
            }
        }
    }   

    public void edgeOperation() 
    {
        System.out.println("What kind of Operation would you like to perform:");
        System.out.println(" 1) Follow a user with another\n 2) Unfollow a user from another");
        int input = getIntInput();
        if(input == 1)
        {
            System.out.println("Enter the first persons name");
            String name1, name2;
            name1 = getInput();
            System.out.println("Enter the second persons name");
            name2 = getInput();
            network.addEdge(name1, name2, name1+":"+name2);
        }
        else if(input == 2)
        {
            System.out.println("Not yet implemented");
        }
        else
        {
            System.out.println("Option does not exist");
        }
    }


    // NAME: newPost
    // PURPOSE: Creates a Post object to add to the networks post list
    // IMPORTS: none
    // EXPORTS: void
    public void newPost() 
    {
        Post post;
        System.out.println("Enter the user who will be posting"); 
        String user = getInput();
        //if the user exists in the network
        if(network.hasVertex(user))
        {
            System.out.println("And what will they be posting?"); 
            String postString = getInput();
            System.out.println("Posting: " + postString + "\nFor user: " + user);
            post = new Post(postString, user);
            //addto post list
            network.newPost(post);
        }
        else
        {
            System.out.println("The user does not exist");
        }
    }

    // NAME: displayStatistics
    // PURPOSE: calls the networks display method
    // IMPORTS: none
    // EXPORTS: void
    public void displayStatistics() 
    {
        network.printMostLiked();
    }

    // NAME: timeStep()
    // PURPOSE: calls the networks timeStep method
    // IMPORTS: none
    // EXPORTS: void
    public void timeStep() 
    {
        network.timeStep();
    } 

    // NAME: saveNetwork
    // PURPOSE: gets the users chosen fileName and sends it to the network for writing 
    // IMPORTS: none
    // EXPORTS: none
    public void saveNetwork() 
    {
        System.out.println("What do you want to call the file?");
        String filename = getInput();
        network.writeList(filename,0);
    }
 
    // NAME: getInput
    // PURPOSE: Method for getting String input
    // IMPORTS: none
    // EXPORTS: void
    public String getInput()
    {
        String inString;
        Scanner sc = new Scanner(System.in);
        inString = sc.nextLine();
        return inString;
    }
 
    // NAME: getIntInput
    // PURPOSE: Method for getting Integer input
    // IMPORTS: none
    // EXPORTS: void
    public static int getIntInput()
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
        }
        return readInt;
    }
 
    // NAME: loadNetwork
    // PURPOSE: reads the file into the networks vertices
    // IMPORTS: Filename provided by user
    // EXPORTS: void
    public void loadNetwork(String fileName)
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
	    try
        {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) 
            {
                //send each line to processNetwork function
		        processNetwork(line);
                line = bufferedReader.readLine();
            }
            network.writeList("Files/timeStep", counter);
	    }
        
        //Catch io exception that may occur
        catch (IOException e) 
        {
            System.out.println("File does not exist");
        }
    }


    // NAME: processNetwork 
    // PURPOSE: Processes each line in the network file into the network
    // IMPORTS: String line from file
    // EXPORTS: void
    public void processNetwork(String line) 
    {
        String[] lineArr;
        if(line.contains(":"))
        {
            //if its an edge, add edge
            lineArr = line.split(":");
            network.addEdge(lineArr[0], lineArr[1], line);
        }
        else
        {
            //else its a vertex, so add vertex
            network.addVertex(line);
        }
    }

    // NAME: loadEvent
    // PURPOSE: Seperate file reader for the event file
    // IMPORTS: String filename
    // EXPORTS: void
    public void loadEvent(String fileName)
    {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
	    try
        {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) 
            {
		        processEvent(line);
                line = bufferedReader.readLine();
            }
	    }
        catch (IOException e) 
        {
            System.out.println("File does not exist");
        }
    }


    // NAME: processEvent
    // PURPOSE: Analyses each line and calls methods depending on the lines contents
    // IMPORTS: String line
    // EXPORTS: void
    public void processEvent(String line)
    { 
        String[] lineArr = line.split(":");
        //if the line contains an F, then add an edge to represent the follow
        if(lineArr[0].equals("F"))
        {
            network.addEdge(lineArr[1], lineArr[2], (lineArr[1] + ":" + lineArr[2]));
        }
        //if the line contains a P, then create a new Post and add it to the list
        // then call a timestep to radiate the post through the network
        else if(lineArr[0].equals("P"))
        {
            long start = System.currentTimeMillis();
            Post p = new Post(lineArr[2], lineArr[1]);
            network.newPost(p);
            network.timeStep();
            long end = System.currentTimeMillis();

            //write the file based on the number of timesteps completed
            network.writeList("Files/timeStep", counter);
            counter++;
            //print the elapsed time for the timeStep
            System.out.println("TimeStep[" + counter + "]: " + (end - start) +" miliseconds");
        }
        //add vertex 
        else if(lineArr[0].equals("A"))
        {
            network.addVertex(lineArr[1]);
        }
        else if(lineArr[0].equals(""))
        {

        }
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
