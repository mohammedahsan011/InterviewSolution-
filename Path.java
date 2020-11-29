public class Path {

    private final int from;
    private final int to;
    private final int weight;

    
    /**
     * Exceptions if : 
     * 1. If node value is less than zero (negative)
     * 2. If the weight value is less than zero (negative)
     *  
     */
    
    public Path(int from, int to, int weight) {
    	// If node value is less than zero 
        if (from < 0)   throw new IllegalArgumentException("Node values must be greater than zero");
        // If the destination node values is less than zero 
        if (to < 0)     throw new IllegalArgumentException("Node values must be greater than zero");
        // If the edge weight or distance value is less than zero or negative 
        if (weight < 0) throw new IllegalArgumentException("Weight values must be greater than zero");
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public int weight() {
        return weight;
    }
}