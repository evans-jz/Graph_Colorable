/**
 * Created by evansliang on 9/8/16.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    private ArrayList<Integer>[] adjacencyArray;
    private int total_edges, num_vertices, invalidSource, invalidVertex;
    boolean colorable;
    private Node[] visited;

    public Graph(int max_Vertices) {
        //construct a new graph with the size of numVertices.
        total_edges = 0;
        num_vertices = max_Vertices;
        adjacencyArray = (ArrayList<Integer>[])new ArrayList[max_Vertices+1];
        colorable = true;
        // use as a variable to store the invalid source and vertex that makes it not 2colorable.
        invalidSource = -1;
        invalidVertex = -1;
        //the adjacency list is an array of arraylist, initialize each element of the array with an empty arraylist.
        //ignore adjacencyArray[0], we want to line up the index with the vertex number.
        for(int i = 1;i<adjacencyArray.length;i++)
            adjacencyArray[i] = new ArrayList<>();
    }
    //add the edges to the adjacency array
    public void addEdge(int vertex1, int vertex2){
        //adds the edge for both vertex since its undirected so it goes both ways.
        adjacencyArray[vertex1].add(vertex2);
        adjacencyArray[vertex2].add(vertex1);
        //keeps track how many edges are in the graph.
        total_edges++;
    }
    //computs the whether or not the graph is 2colorable
    public boolean isColorable(){
        visited = new Node[num_vertices+1];
        //create a node object for every element in visited
        for(int i=0;i<visited.length;i++){
            //0=unvisited, 1=red, 2=blue
            visited[i] = new Node(0,-1);
        }
        //perform a bfs on every unvisited node
        for(int i=1;i<visited.length;i++) {
            if(visited[i].getColor() == 0)
                DFS_visit(i);
        }

        //print the invalid cycle that makes it not 2colorable
        if(colorable==false){
            //print the invalid cycle if the size is less than 100
            int size=1;
            String invalidCycle = "";
            int current = invalidVertex;
            while(current != invalidSource){
                invalidCycle+=current+" ";
                current = visited[current].getParent();
                size++;
            }
            invalidCycle+=invalidSource;
            size++;
            if(size<=100)
                System.out.println(invalidCycle);
            else
                System.out.println("The size of the invalid cycle is larger than 100");
            return false;
        }
        else{
            //prints the graph if the size is less than 100.
            if(num_vertices<=100) {
                for (int i = 1; i < visited.length; i++) {
                    System.out.print("\nVertex " + i + " : ");
                    if (visited[i].getColor() == 1)
                        System.out.print("Red");
                    else
                        System.out.print("Blue");
                }
                System.out.println();
            }
            else
                System.out.println("The size of the graph is larger than 100");
            return true;
        }
    }
    //dfs algorithm
    public void DFS_visit(int from){
        //initialize the stack with the root node
        Stack stack = new Stack();
        stack.push(from);
        visited[from].setColor(1);

        while(!stack.isEmpty()) {
            //remove the first item on the stack
            int temp = (int)stack.pop();
            //loop through all of its neighbors
            for (int i : adjacencyArray[temp]) {
                //if a neighbor is not visited, mark the color depending on its parent
                if (visited[i].getColor() == 0 ) {
                    //set parent
                    visited[i].setParent(temp);
                    stack.push(i);
                    if (visited[temp].getColor() == 1)
                        visited[i].setColor(2);
                    else
                        visited[i].setColor(1);
                }
                else{
                    //if any of the neighbor has the same color as the current node, flag it
                    if(visited[i].getColor() == visited[temp].getColor()){
                        colorable=false;
                        //keeps track of where the invalidVertex is.
                        if(invalidVertex == -1){
                            invalidVertex = i;
                            invalidSource = from;
                        }
                    }
                }
            }
        }
    }
    //bfs algorithm, only works partly, the part that doesnt work is to printout the invalid cycle.
//    public void BFS_visit(int from){
//        Queue queue = new LinkedList<>();
//        //add root node to queue and set its color to 1
//        visited[from].setColor(1);
//        queue.add(from);
//        //starting from this root node, start the search
//        while(!queue.isEmpty()){
//            int temp = (int) queue.remove();
//            for (int i: adjacencyArray[temp]){
//                visited[i].setParent(temp);
//                //if a child is unvisited, mark as visited and assign color depending on the parent.
//                if(visited[i].getColor() == 0 ){
//                    queue.add(i);
//                    visited[i].setParent(temp);
//                    if(visited[temp].getColor()==1)
//                        visited[i].setColor(2);
//                    else if (visited[temp].getColor()==2)
//                        visited[i].setColor(1);
//                }
//                //if the parent has the same color as a child, flag it and terminate. record the invalid vertex and source.
//                if (visited[temp].getColor() == visited[i].getColor()) {
//                    colorable = false;
//                    invalidVertex = i;
//                    invalidSource = from;
//                }
//            }
//            if (colorable == false)
//                break;
//        }
//
//    }
}
