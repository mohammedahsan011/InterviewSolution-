public class Train {

	
	/*
     * 
     * The given problem can be considered as Dijkstra’s shortest path finding problem. 
	 * BFS can be done to find if there would exist a path from one node to another or not. 
	 * And also to find the distance from one node to another(shortest) Djikstra’s algorithm can be used. 
	 * However in the program different functions are employed to compute the values of different operations.

	 *	Djikstra’s algorithm would find a shortest-path from a fixed start node. 
	 *	So the distances or edge weights can be represented as an neighbournodesacency matrix 
	 *	With a complexity of O(|V|^3).

     * 
     * Output Explained : 
     * 
     * 
     *  Output 1 : 9 -> for the input distance of the route A-B-C (A to B it’s 5 and B to C it’s 4 so a total of 9.)
	 *  Output 2: 5 -> for the input distance of the route A-D (A to D is 5 given )
	 *	Output 3: 13 -> for the input distance of route A-D-C (A to D it’s 5 and D to C it’s 8 so a total of 8+5 equal to 13. )
	 *	Output 4: 22 -> for the input distance of route A-E-B-C-D (A to E it’s 7, and E to B it’s 3 , B to C it’s 4 and C to D it’s 8, so a total of 7+3+4+8 = 10+12 = 22. )
	 *	Output 5: NO SUCH ROUTE -> for the input distance of route A-E-D (Although there is a route from A to E, there is no route from E to D.)
	 *	Output 6: 2 -> the number of trips starting at C and ending at C with a maximum of 3 stops. In the sample data below, there are two such trips: C-D-C (2 stops).[C to D one stop and D to C another stop] and C-E-B-C [C to E one stop, E to B stop, and B to C another stop](3 stops).
	 *	Output 7: 3 -> the number of trips starting at A and ending at C with exactly 4 stops. In the sample data below, there are three such trips: A to C (via B, C, D which would be A->B->C->D->A , A to B one stop, B to C second stop, C to D third stop, and D to A fourth stop ); A to C (via D, C, D, A to D one stop, D to C second stop, C to D third stop, D to A fourth stop); and A to C (via D, E, B ;  A to D first stop, D to E second stop, E to B third stop, and B to C fourth stop ).
	 *	Output 8: 9 -> the length of the shortest route (in terms of distance to travel) from A to C.(5+4 which is equal to 9, A to B and B to C so A to C)
	 *	Output 9: 9 -> the length of the shortest route (in terms of distance to travel) from B to B.
	 *	Output 10: 7 -> the number of different routes from C to C with a distance of less than 30. In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
     * 
     * */
	
    public static void main(String[] args) {
        parser(args);
        System.out.println("args: "+ args[0]);
        Map nodes = new Map(args[0]);

        System.out.println("Output #1: " + nodes.showPath("ABC"));
        System.out.println("Output #2: " + nodes.showPath("AD"));
        System.out.println("Output #3: " + nodes.showPath("ADC"));
        System.out.println("Output #4: " + nodes.showPath("AEBCD"));
        System.out.println("Output #5: " + nodes.showPath("AED"));
        System.out.println("Output #6: " + nodes.calculatetripsnum("C", "C", t -> t <= 3, 3));
        System.out.println("Output #7: " + nodes.calculatetripsnum("A", "C", t -> t == 4, 4));
        System.out.println("Output #8: " + nodes.calculateShortestPath("A", "C"));
        System.out.println("Output #9: " + nodes.calculateShortestPath("B", "B"));
        System.out.println("Output #10: " + nodes.calculateroutesnum("C", "C", 30));
    }

    // Correct Input format. 
    private static void parser(String... args){
        String input = " Type in the input same as : \"AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7\"";

    // Exception if wron input length occurs     
        if(args.length != 1)
            throw new IllegalArgumentException("Wrong input length!" + input);

        String arr[] = args[0].split(",");
        for(String s: arr){
            s = s.trim();

            // If the length varies from what is expected input 
            if(s.length() < 3)
                throw new IllegalArgumentException("Incorrect input type!" + input);

            // 	f there occurs incorrect weight of the edge connecting nodes 
            if(!s.substring(2).matches("-?\\d+"))
                throw new IllegalArgumentException("Incorrect edge weight !" + input);
        }
    }
}
