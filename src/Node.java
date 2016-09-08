/**
 * Created by evansliang on 9/8/16.
 */

//simple node class that contains the color, 0=unvisited, 1=red, 2=blue and the parent of the node.
public class Node {
    private int color;
    private int parent;

    public Node(int new_color, int new_parent){
        color = color;
        parent = new_parent;
    }
    //setter and getter method for the node.
    public int getParent(){return parent;}
    public int getColor(){return color;}
    public void setParent(int new_parent){
        parent = new_parent;
    }
    public void setColor(int new_color){
        color= new_color;
    }
}
