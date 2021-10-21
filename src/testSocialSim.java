public class testSocialSim
{
    public static void main(String[] args)
    {
      
        System.out.println("Creating a SocialSim object with probabilities == 1.0");
        SocialSim s = new SocialSim(1.0, 1.0);
        System.out.println("Loading testNetwork.txt");
        s.loadNetwork("testNetwork.txt");
        System.out.println("Displaying the network");
        
        System.out.println("\n\n");
        s.displayNetwork(); 
        
        System.out.println("\n\n");
        System.out.println("Loading an example event: 'P:Crota: My sword big but brain small'");
        s.processEvent("P:Crota:My sword big but brain small");
        System.out.println("Now running a timeStep");
        s.timeStep();
        
        System.out.println("\n\n");
        System.out.println("Displaying the network");
        s.displayNetwork();
    }
}
