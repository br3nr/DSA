# DSA
This repo is for an assignment completed in first year for the unit Datastructrues and Algorithms.
The assignment was a lot of fun and was a great way to exercise my problem solving skills. 

Source files can be found in the src folder, compilation steps are not included as this repo has been
uploaded for doccumentation :) 

For this assignment I received a mark of 89%, with an overall mark of : INSERT MARK


## Social Sim
SocialSim consists of the following java files which I will describe and justify: 
1. SocialInterface.java
1. SocialSim.java,
1. Graph.java,
1. Post.java 
1. LinkedList.java

### Social Interface
Social Interface acts as a type of user interface for the program. It displays the menu to the user in
interactive mode and passes the simulator around inside a while loop. It contains quality of life esque
functions to make the UI a bit nicer to look at, such as clearing the terminal and waiting for a user input to
continue. I felt that this class was necessary as it separates the interface from the algorithms, rather than
having them in one big class.

### SocialSim
SocialSim contains the methods that will manipulate the network externally and perform the functions
listed inside the menu. It reads in the network files and event files from the terminal and uses them to load
up the Graph network. To process the simulation mode Event file and Network files, I created TWO read
file methods so that the while loop could point to the correct line processing methods. This makes the
code a lot more predictable. The eventFiles line processor controls the file writing and timestepping in
simulation mode.

### Graph
The Graph file contains the all so important timestep function, all of the edge and vertice operations (i.e
addEdge, addVertice). This file contains a lot of code. I would have preferred to have had more java files
to split the classes up and move some of the algorithms around, however, this implementation was the
easiest and came with less errors and complicated workarounds. Within the graph, I have the Vertices and
Edges stored in Linked Lists, just as shown in the pseudo code shown in the practicals. The Edge and
Vertex classes are private inner classes of the Graph class. I could have made them their own classes, but
since I wouldn't have to work on them and that they are completely dependent on a Graph class I decided
against it.

### Post
The Post class stores a users posts. To identify which user posted it, the created post is given the label of
the vertex that is associated with it. The post is then given a String to represent what the post says. When
a post is created, it's added to a LinkedList of posts in the Graph file for easy access when searching for
who has the most likes. The post also contains a LinkedList within its class that stores the names of
whoever would happen to call the newLike() function. This is so that, when we call checkLiked(), we can
prevent multiple likes from the same user. I decided to create this class as I felt it best represented what a
post could be.

### Timestep
The algorithm for the timestep is quite large, however performs exactly what I wanted it to. Let's say we
were to run 2 timesteps after a post. On the initial timestep, the User who posted exposes his followers to
his post. If they like it, then they expose it to their followers and they have a chance to like and follow
them. Thats where timestep one ends. The outward flow occurs on each subsequent timestep, where more
and more users in the database can see the post.

### LinkedLists
One of the main data structures I have used throughout my code are LinkedLists. I have coded the linked
list based of the algorithms given in the lectures, and the algorithms given in UCP. Linked lists are used to
store the Vertex’s, Adjacent Vertices, Edges, Posts and postLikers. I feel my decision was the right
decision as I can't imagine any other data structure that could have done it better. One problem I have
with the use of linked lists is typecasting. 