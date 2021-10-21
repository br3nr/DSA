public class testGraph
{

    public static void main(String[] args)
    {
        Graph gh = new Graph();
        System.out.println("~~~ Graph Test ~~~");
        System.out.println("\n\n   Inserting A, B and C");
        gh.addVertex("A");
        gh.addVertex("B");
        gh.addVertex("C");
        System.out.println("   ... done");
        System.out.println("   Displaying current state");
        gh.displayList();
        gh.getVertexCount();
        

        System.out.println("\n\n   Adding edges C and B to A");
        gh.addEdge("A", "C", "A:C");
        gh.addEdge("A", "B", "A:B");
        System.out.println("  Inserting null edges Z, L and F and catching them");
        System.out.println("    ...");
        gh.addEdge("Z", "L", "Z:L");
        System.out.println("    ...");
        gh.addEdge("A", "F", "A:F");
        System.out.println("   Done");
       

        System.out.println("~~~ Displaying the list ~~~");
        gh.displayList();

        System.out.println("\n\n    Adding D, E and F with edges C, B and D");
        gh.addVertex("D");
        gh.addVertex("E");
        gh.addVertex("F");

        gh.addEdge("D", "C", "D:C");
        gh.addEdge("E", "B", "E:B");
        gh.addEdge("F", "D", "F:D");
        System.out.println("   Displaying the final list");
        gh.displayList();

        System.out.println("");
    }


}
