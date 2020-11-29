import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Map {
	
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
    
	
    private static final String[] listofnodes = {"A", "B", "C", "D", "E"}; //list of nodes in the given graph 
    private final List<Path>[] neighbournodes;   // Neighbor nodes which are adjacent to the given node 
    private List<String> completepath; // The total path possible 
    private int to;  // destination node 
    private int maximumdist;   //maximum distance possible 
    private int ends;           // number of terminations once we go for a traversal 
    private int routesnum;   // number of routes possible 
    private int tripsnum;     // number of trips possible 

    
    private Map(int n) {
        if (n < 0) throw new IllegalArgumentException("Number of nodes must be greater than zero"); // Check if the number of nodes in the given graph is greater than zero or NOT. 
        neighbournodes = (LinkedList<Path>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            neighbournodes[i] = new LinkedList<>();
    }

    public Map(String inputGraph) {
        this(listofnodes.length);
        PathValue(inputGraph);
    }

    private void PathValue(String inputGraph){
        String[] inputArr = inputGraph.split(",");
        for (String s : inputArr) {
            s = s.trim();
            int from = getpos(s.substring(0, 1));
            int to = getpos(s.substring(1, 2));
            int weight = Integer.parseInt(s.substring(2));
            Path e = new Path(from, to, weight);
            newnode(e);
        }
    }

    private void newnode(Path e) {
        int v = e.from();
        neighbournodes[v].add(e);
    }

    private List<Path> neighbournodes(int v) {
        if (v < 0 || v >= listofnodes.length) throw new IndexOutOfBoundsException("node " + v + " do not exist"); //Check if the node would exist or not 
        return neighbournodes[v];
    }

    public String showPath(String route){  //DisplayDistance
        int distance = producepathweight(route);
        return (distance != -1) ? String.valueOf(distance) : "NO SUCH ROUTE"; // Check if a particular route would exist or not if not produce NO SUCH ROUTE EXIST
    }

    
    // To compute the weight of a given path 
    private int producepathweight(String route){  
        if(route == null) throw new IllegalArgumentException("Path is Incorrect"); // Check if the path is Correct or not 
        int distance = 0;
        String[] vertex = route.trim().split("");
        int from, to;

        for (int i = 0; i < vertex.length-1;) {
            boolean hasPath = false;
            from = getpos(vertex[i++]);
            to = getpos(vertex[i]);
            List<Path> edgeList = neighbournodes(from);
            for (Path edge : edgeList)
                if (edge.to() == to) {
                    distance += edge.weight();
                    hasPath = true;
                    break;
                }
            if(!hasPath) return -1;
        }

        return distance;
    }

    // Function to compute the number of trips 

    public int calculatetripsnum(String from, String to, Predicate<Integer> p, int ends){
        this.to = getpos(to);
        this.ends = ends;
        this.tripsnum = 0;
        int startIndex = getpos(from);
        calculatetripsnum(startIndex, String.valueOf(startIndex), p);

        return tripsnum;
    }

    private void calculatetripsnum(int from, String path, Predicate<Integer> p) {
        List<Path> edges = neighbournodes(from);
        for (Path e: edges) {

            String next = path + e.to();
            int stopCount = next.length()-1;

            if (this.to == e.to() && p.test(stopCount))
                tripsnum++;

            if(stopCount <= ends)
                calculatetripsnum(e.to(), next, p);
        }
    }


    
    // Function to calculate the shortest Path
    public int calculateShortestPath(String from, String to){
        completepath = new ArrayList<>();
        this.to = getpos(to);
        int startIndex = getpos(from);
        calculateShortestPath(startIndex, String.valueOf(startIndex));

        int shortestDistance = Integer.MAX_VALUE, currentDistance;
        for(String s: completepath){
            currentDistance = producepathweight(s);
            if(shortestDistance > currentDistance)
                shortestDistance = currentDistance;
        }

        if(shortestDistance == Integer.MAX_VALUE) return 0;

        return shortestDistance;
    }

    private void calculateShortestPath(int from, String path) {
        List<Path> edges = neighbournodes(from);
        for (Path e: edges) {

            if (path.length()>1 && path.substring(1).contains(String.valueOf(e.to()))) //checked visited or not
                continue;

            String next = path + e.to();

            if (this.to == e.to())
                completepath.add(getPathName(next));

            calculateShortestPath(e.to(), next);
        }
    }

    
    // Function to calculate the number of routes

    public int calculateroutesnum(String from, String to, int maximumdist){
        this.to = getpos(to);
        this.maximumdist = maximumdist;
        this.routesnum = 0;
        int startIndex = getpos(from);
        calculateroutesnum(startIndex, String.valueOf(startIndex));

        return routesnum;
    }

    private void calculateroutesnum(int from, String path) {
        List<Path> edges = neighbournodes(from);
        for (Path e: edges) {

            String next = path + e.to();
            int distance = producepathweight(getPathName(next));

            if (this.to == e.to() && (distance < maximumdist))
                routesnum++;

            if(distance < maximumdist)
                calculateroutesnum(e.to(), next);
        }
    }


    private static int getpos(String vertex) {
        int index = Arrays.binarySearch(listofnodes, vertex);
        if (index < 0)
            throw new IllegalArgumentException("input is Incorrect!");

        return index;
    }

    private String nodename(int index) {
        if (index < 0 || index >= listofnodes.length)
            throw new IllegalArgumentException("Index is Incorrect");

        return listofnodes[index];
    }

    private String getPathName(String path){
        String arr[] = path.trim().split("");
        String name = "";
        for(String v: arr)
            name += nodename(Integer.parseInt(v));

        return name;
    }
}