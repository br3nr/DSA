// FILE: Graph.java                                                                            
// AUTHOR: Max Barker (19624729)                                                                   
// USERNAME: BigMoney                                                                    
// UNIT: DSA                                                              
// PURPOSE: Creates the Graph data structure to store the network                                                      
// REQUIRES: SocialInterface.java SocialSim.java              
// Last Mod: 28 OCT 2019<Paste>

import java.io.*;
import java.util.*;
public class Graph
{
    private LnkList vertices, edges, posts;
    private double probLike, probFollow;
    private int steps;
    
    //default constructor
    public Graph()
    {
        vertices = new LnkList();
        edges = new LnkList();
        posts = new LnkList();
        probLike = 0.2;
        probFollow = 0.2;
        steps = 0;
    }

    public Graph(double inLike, double inProb)
    {
        vertices = new LnkList();
        edges = new LnkList();
        posts = new LnkList();
        probLike = inLike;
        probFollow = inProb;
    }

   public void initNetwork(double inProbLike, double inProbFollow)
    {
        setProbLike(inProbLike);
        setProbFollow(inProbFollow);
    }

    //accessors
    public double getProbLike()
    {
        return probLike;
    }

    public double getProbFollow()
    {
        return probFollow;
    }//accessor   
    
    //setters
    public void setProbLike(double like)
    {
        probLike = like;
    }

    public void setProbFollow(double follow)
    {
        probFollow = follow;
    }//setters

    public void addVertex(String label)
    {
        Vertex v = new Vertex(label);
        vertices.insertLast(v);
    }


    public void newPost(Post inPost)
    {
        posts.insertLast(inPost);
    }

    //NAME: getEdge
    //PURPOSE: searches the Edge network a matching edge
    //IMPORTS: String edgeLabel
    //EXPORTS: matching Edge object or null
    public Edge getEdge(String edgeLabel)
    {
        Iterator edgeIter;
        edgeIter = edges.iterator();
        Edge edge = null;
        Object edgeObj;
        //while the edgeList has an edge
        while(edgeIter.hasNext())
        {
            //check to see if a matching edge exists
            edgeObj = edgeIter.next();
            if(((Edge)edgeObj).getLabel().equals(edgeLabel))
            {
                edge = ((Edge)edgeObj);
            }
        }
        return edge;
    }

    //NAME: addEdge
    //PURPOSE: adds an edge to the edge LinkedList
    //IMPORTS: String labels, edgelabel
    //EXPORTS:
    public void addEdge(String label1, String label2, String edgeLabel)
    {
        Iterator iter = vertices.iterator();

        Object inObj, adjObj;
        Vertex vertex = null;
        Vertex adjVer = null;
        Edge edge = null;
        
        //IF the labels do not match && the edge between label1 and label2 doesnt exist
        if(!label1.equals(label2) && !label2.equals(label1) && getEdge(label1 + ":" + label2) == null && getEdge(label2 + ":" +label1) == null)
        {
            if(!(label1.equals(label2)))
            {
                while(iter.hasNext())
                {
                    //iterate through the vertice list
                    inObj = iter.next();
                    adjObj = inObj;
                    if((((Vertex) inObj).getLabel()).equals(label1))
                    {
                        vertex = (Vertex) inObj;
                    }
                    if((((Vertex) adjObj).getLabel()).equals(label2))
                    {
                        adjVer = (Vertex) adjObj;
                    }
                }

                //if both vertex' are found in the list
                if(vertex !=null && adjVer != null)
                {
                    //create an edge between the two
                    edge = new Edge(edgeLabel);
                    //add each vertex to their adjacency lists
                    vertex.addAdjacent(adjVer);
                    adjVer.addAdjacent(vertex);
                    //add edges to each vertexs edge list
                    vertex.addEdge(edge);
                    adjVer.addEdge(edge);
                    //add edge to the Graphs edge linked list
                    edges.insertLast(edge);
                }
            }
        }
    }

    //NAME: hasVertex
    //PURPOSE: checks if the vertex exists in the list
    //IMPORTS: String label
    //EXPORTS: Boolean to represent if vertex was found
    public boolean hasVertex(String label)
    {
        boolean found = false;
        Iterator iter = vertices.iterator();
        Object obj; 
        //iterate through vertice list till vertex found or end reached
        while(iter.hasNext())
        {
            obj = iter.next();
            if((((Vertex)obj).getLabel()).equals(label))
            {
                found = true;
            }
        }
        return found;
    }
    

    //NAME: probability
    //PURPOSE: calculates the probability with Math class
    //IMPORTS: Double probability
    //EXPORTS: boolean
    public boolean probability(double prob)
    {
        //If math.randoms return is less than the probability
        return (Math.random() < prob);
    }
    
    //NAME: timeStep
    //PURPOSE: Simulates a timeStep in the network
    //IMPORTS: none
    //EXPORTS: void
    public void timeStep()
    {
        //Initiliazing all the iterators needed
        Iterator verIter;
        Iterator postIter = posts.iterator();
        Iterator followIter, friendIter;
        //Linked lists for storing the vertice and post lists
        LnkList followLinks, friends;
        //objects to typecast
        Object verObj, followObj, friendObj, postObj;
        String user;
        //Iterate through every post in the post list
        while(postIter.hasNext())
        {
            postObj = postIter.next();
            user = ((Post)postObj).getUser();
            verIter = vertices.iterator();
            //while loop for vertex list
            while(verIter.hasNext())
            {
                verObj = verIter.next();
                //if the vertex is the poster
                if(((Vertex)verObj).getLabel().equals(user))
                {
                    //create a copy of the Posters adjacent followers
                    followLinks = ((Vertex)verObj).getAdjacent(); 
                    followIter = followLinks.iterator(); 
                    //if the the probability of like is true
                    if(probability(probLike))
                    {
                        //iterate through the Posters 
                        while(followIter.hasNext())
                        {
                            followObj = followIter.next();
                            friends = ((Vertex)followObj).getAdjacent();
                            friendIter = friends.iterator();
                            String liker = ((Vertex)followObj).getLabel();
                            //if the Users follower has NOT liked their post already
                            if( ((Post)postObj).checkLiked(liker) == false)
                            {
                                //like the post, add the user to the posts liked list
                                ((Post)postObj).likePost();
                                ((Post)postObj).addLiker(liker);
                            }
                            //iterate through the mutual friends (adjacent vertex' adjacency lists)
                            while(friendIter.hasNext())
                            {
                                friendObj = friendIter.next();
                                if(probability(probLike))
                                {
                                    String friendName = ((Vertex)friendObj).getLabel();
                                    if(!friendName.equals(user))
                                    {
                                        //Check to see if the mutual friend has already liked the post
                                        String friendLike = ((Vertex)friendObj).getLabel();
                                        if( ((Post)postObj).checkLiked(liker) == false)
                                        {
                                            //like post and add mutual friend to the post
                                            ((Post)postObj).likePost();
                                            ((Post)postObj).addLiker(liker);
                                        }
                                        if(probability(probFollow))
                                        {
                                            //if they liked the post, then they have a chance to follow
                                            if(getEdge(user+":"+friendName) == null)
                                            {
                                                addEdge(user, friendName, user+":"+friendName);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    //NAME: getMostLiked
    //PURPOSE: Searches the posts list and grabs the highest liked post
    //IMPORTS: none
    //EXPORTS: String representing the post
    public String getMostLiked()
    {
        Iterator postIter = posts.iterator();
        String mostLiked;
        Object post;
        Post p;
        int counter = 0;
        int likes = 0;
        //initialize a post array based off the posts list length
        Post[] postArr = new Post[getPostCount()];
        while(postIter.hasNext())
        {
            //populate the array with the post objects
            post = postIter.next();
            postArr[counter] = ((Post)post);
            counter++;
        }
        //call selection sort to sort the array
        postArr = selectionSort(postArr);
        p = postArr[0];
        //create the formatted String
        mostLiked = (p.getUser() + ": " + p.getPost() + " ["+p.getLikes()+"]");
        return mostLiked;

    }

    //NAME: printMostLiked
    //PURPOSE: prints the posts in descending order (based off likes)
    //IMPORTS: none
    //EXPORTS: void
    public void printMostLiked()
    {
        Iterator postIter = posts.iterator();
        Object post;
        int counter = 0;
        int likes = 0;
        Post[] postArr = new Post[getPostCount()];
        while(postIter.hasNext())
        {
            post = postIter.next();
            postArr[counter] = ((Post)post);
            counter++;
        }
        postArr = selectionSort(postArr);
        for(int i = 0; i < postArr.length; i++)
        {
            Post p = postArr[i];
            System.out.println(p.getUser() + ": " + p.getPost() + " ["+p.getLikes()+"]");
        }
    }

    //NAME: selectionSort
    //PURPOSE: Simple selectionSort algorithm 
    //IMPORTS: Post[] array
    //EXPORTS: Sorted Post[] array
    public static Post[] selectionSort(Post[] A)
    {
        
        for(int i = 0; i < A.length; i++)
	    {   
            int min = i;
	        for(int j = i+1; j < A.length; j++)
	        {
                if(A[j].getLikes() > A[min].getLikes())
                {
                    min = j;
                }
                Post temp = A[min];
                A[min] = A[i];
                A[i] = temp;
	        }
	    }
	    return A;
    }
 
    


    //NAME: getPostCount
    //PURPOSE: counts the amount of posts in the posts list
    //IMPORTS: none
    //EXPORTS: Integer
    public int getPostCount()
    {
        int counter = 0; 
        Iterator postIter = posts.iterator();
        while(postIter.hasNext())
        {
            postIter.next();
            counter++;
        }
        return counter;
    }
 
    //NAME: getVertexCount
    //PURPOSE: counts amount of Vertexs in the vertice list
    //IMPORTS: none
    //EXPORTS: Integer
    public int getVertexCount()
    {
        int counter = 0; 
        Iterator iter = vertices.iterator();
        while(iter.hasNext())
        {
            iter.next();
            counter++;
        }
        return counter;
    }
    
    //NAME: displayList
    //PURPOSE: displays the list as an adjacency matrix
    //IMPORTS: none
    //EXPORTS: void
    public void displayList()
    {
        Object obj1, obj2;
        Iterator iter = vertices.iterator();
        Iterator iter2;
        while(iter.hasNext())
        {
            obj1 = iter.next();
            LnkList adj = ((Vertex)obj1).getAdjacent();
            
            //print the vertice to the terminal...
            System.out.print("\n"+((Vertex)obj1).toString() + " | "); 
            iter2 = adj.iterator();
            while(iter2.hasNext())
            {
                //...followed by the edges on the same line ( A | B C D )
                obj2 = iter2.next();
                System.out.print(((Vertex)obj2).toString() + " ");
            }
        }
        System.out.println();
    }
    
    //NAME: writeList
    //PURPOSE: writes the current list to a file
    //IMPORTS: String fileName, Integer counter
    //EXPORTS: void
    public void writeList(String fName, int counter)
    {
        FileOutputStream file = null;
        PrintWriter fileWrite;
        Object obj1, obj2;
        try 
        {
            file = new FileOutputStream(fName+counter+".txt");
            fileWrite = new PrintWriter(fName+counter+".txt");  
            Iterator iter = vertices.iterator();
            Iterator iter2;
            while(iter.hasNext())
            {
                //writes the file in the same format as displayList
                obj1 = iter.next();
                LnkList adj = ((Vertex)obj1).getAdjacent();
                 
                fileWrite.print(((Vertex)obj1).toString() + " | "); 
                iter2 = adj.iterator();
                while(iter2.hasNext())
                {
                    obj2 = iter2.next();
                    fileWrite.print(((Vertex)obj2).toString() + " ");
                }
                fileWrite.println();
            }
    
            //prints the most liked post at the end of the file
            if(!posts.isEmpty())
            {
                fileWrite.println("\nMOST LIKED POST: \n\n   " + getMostLiked());
            }
            fileWrite.close();
        }
        catch(IOException e)
        {
            System.out.println("IOException: " + e);
        }
    }
 

    //PRIVATE CLASS
    //
    //  Creates a Vertex Object to be stored
    //  in the network.
    //
    
    private class Vertex
    {

        private String label; 
        private Object value;
        private LnkList posts;
        private LnkList edgeList;
        private LnkList links;
        private boolean visited;
        
        public Vertex(String inLabel, Object inValue)
        {
            label = inLabel;
            value = inValue;
            edgeList = new LnkList();
            links = new LnkList();
            posts = new LnkList();
       }
    
        public Vertex(String inLabel)
        {
            label = inLabel;
            edgeList = new LnkList();
            links = new LnkList();
            posts = new LnkList();

        }

        public String getLabel()
        {
            return label;
        }

        public void insertPost(Post post)
        {
            posts.insertLast(post);
        }

        public void setValue(Object inValue)
        {
            value = inValue;
        }

        public Object getValue()
        {
            return value;
        }

        public LnkList getAdjacent()
        {
            return links;
        }

        public LnkList getEdgeList()
        {
            return edgeList;
        }

        public void addAdjacent(Vertex v)
        {
            if(links.isEmpty())
            {
                links.insertFirst(v);
            }
            else
            {
                links.insertLast(v);
            }
        }

        public void addEdge(Edge e)
        {
            if(edgeList.isEmpty())
            {
                edgeList.insertFirst(e);
            }
            else
            {
                edgeList.insertLast(e);
            }
        }

        public void setVisited()
        {
            visited = true;
        }
        
        public void clearVisited()
        {
            visited = false;
        }

        public boolean getVisited()
        {
            return visited;
        }

        public String toString()
        {
            return label;
        }
    }


    //PRIVATE CLASS 
    //
    //   class to represent the edges 
    //   in the network
    //
   
    private class Edge
    {
        String label;
        Object value;
        
        public Edge(String inLabel)
        {
            label = inLabel;
        }
    
        public Edge(String inLabel, Object inValue)
        {
            label = inLabel;
            value = inValue;
        }

        public String getLabel()
        {
            return label;
        }
        
        public Object getValue() 
        {
            return value;
        }
 
        public Vertex getFrom()
        {
            return null;
        }

        public Vertex getTo()
        {
            return null;
        }

        public boolean isDirected()
        {
            return false;
        }

        public String toString()
        {
            return label;
        }
    }        
}

