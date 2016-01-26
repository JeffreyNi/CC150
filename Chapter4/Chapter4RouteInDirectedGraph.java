/********************************************************
 * This class implements problem 4.2 in CC150.
 *
 * Given a directed graph, design an algorithm to find out
 * whether there is a rout between two nodes.
 *
 * Solution:
 * Just traverse the graph from the starting node, using 
 * any kind of traversal, e.g., BFS, DFS, and see if the
 * end Node can be reached or not.
 * Here we assume that Node has a state, and can be set as
 * Unvisited, Visited, and Visiting. Node has method getAdjacent()
 * to get all nodes that are ajacent to a specific node. And 
 * we assume there is already a class Graph, which has method 
 * getNodes().
 *
 * @author Jiafeng Ni
 * 
 ********************************************************/

import java.util.*;

public class Chapter4RouteInDirectedGraph{

    public enum State {
	Unvisited, Visited, Visiting;
    }


    /**
     * Returns true if there is a path from start to end in the graph.
     * @param g the given graph where to search the path
     * @param start the given start node
     * @param end the given destination node
     * @return true if there is a path from start to end in the graph.
     */
    public static boolean hasPath(Graph g, Node start, Node end) {
	Queue<Node> q = new LinkedList<Node>();
	for (Node n : g.getNdoes()) {
	    n.state = Unvisited;
	}

	start.state = Visiting;
	q.add(start);
	Node node;

	while (!q.isEmpty()) {
	    node = q.poll();
	    if (node != null) {
		for (node adj : node.getAjacent()) {

		    if (adj.state == State.Unvisited) {
			if (adj == end) { return true; }
			else {
			    adj.state = State.Visiting;
			    q.add(adj);
			}           
		    }
		}
		node.state = State.Visited;
	    }
	}
	
	return false;
    }

}
