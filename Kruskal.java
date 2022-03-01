import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

	// Refering to the class WGraph to return value
	// Example : class object = new class();
    WGraph createGraph = new WGraph();
    // List  of edges sorted  by weight
     ArrayList<Edge> sortWeight = g.listOfEdgesSorted();  
 
    // Going through do edges and adding new edges to the graph
    int e = 0;
   while ( e < sortWeight.size()) {
	   createGraph.addEdge(sortWeight.get(e));  
       e++;
   }
    return createGraph;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){
	// Defining the nodes
    int rootP = p.find(e.nodes[0]);
    int rootE  = p.find(e. nodes[1]);
       //If the nodes have the same parent then we don't have to merge them anymore
       if (rootP == rootE) {
           return false;
       } else { 
           // If they don't have the same parent we can merge them so it's true
       p.union(rootP, rootE);
       return true;
       }

    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
