import java.io.IOException;
import java.util.*;

public class AStar extends absAlgorithm{

    public AStar(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode) throws IOException {
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        String path = "";
        PriorityQueue<Tile_Puzzle> myQueue = new PriorityQueue<>();
        Hashtable<Tile_Puzzle, Integer> openList = new Hashtable<>();
        Hashtable<Tile_Puzzle, Integer> closedList = new Hashtable<>();

        myQueue.add(startingNode);
        openList.put(startingNode, 0);

        while (!myQueue.isEmpty()){
            Tile_Puzzle currentNode = myQueue.poll();
            openList.remove(currentNode);

            ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, true);
            for (Tile_Puzzle node : nodeOperations) {
               // System.out.println(node.toString());
                numOfCreated++;
                if (node.equals(endingNode))
                    return getPath(node, true);
                if (openList.contains(node) && openList.get(node) <= node.getfCost())
                    continue;
                if (closedList.contains(node) && closedList.get(node) <= node.getfCost())
                    continue;
                else {
                    openList.put(node, node.getfCost());
                    myQueue.add(node);
                }
            }
            closedList.put(currentNode, currentNode.getfCost());
        }
        return path;
    }

    @Override
    public String getPath(Tile_Puzzle operation, boolean isPath) {
        String path = "";
        if (isPath){
            path = operation.getCurrentPath().substring(1) + "\n";
            path += "Num: " + numOfCreated + "\n";
            path += "Cost: " + operation.getCostOfPath();
        }
        else {

        }
        return path;
    }
}
