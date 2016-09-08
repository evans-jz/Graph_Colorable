import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    //reads input
    public static void main(String[] args) throws FileNotFoundException {
        System.currentTimeMillis();
        String file = args[0];
        Scanner scan = new Scanner(new FileReader(file));

        //first line is the number of vertices, use that to create graph
        Graph g = new Graph(Integer.parseInt(scan.next()));
        scan.nextLine();
        //add the edges to the adjacent list in Graph.
        while(scan.hasNextLine()){
            String[] line = scan.nextLine().split(" ");
            g.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        scan.close();
        System.out.print("Two-colorable: "+g.isColorable());
        System.currentTimeMillis();
    }
}
