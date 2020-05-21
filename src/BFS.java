import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS  {

    Tile_Puzzle startingNode;
    Tile_Puzzle endingNode;
    int numOfCreated;
    int costOfPath;

    BFS(Tile_Puzzle startingNode, Tile_Puzzle endingNode) {
        this.startingNode = startingNode;
        this.endingNode = endingNode;
        this.numOfCreated = 1;
        this.costOfPath = 0;
    }


    String bfsAlgrotihm() {
        Queue<Tile_Puzzle> myQueue = new LinkedList<>();
        HashSet<Tile_Puzzle> openList = new HashSet<>();
        HashSet<Tile_Puzzle> closedList = new HashSet<>();

        myQueue.add(startingNode);
        openList.add(startingNode);

        while (!myQueue.isEmpty()) {
            Tile_Puzzle currentNode = myQueue.poll();
            openList.remove(currentNode);
            closedList.add(currentNode);
            ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperatorsWithoutWeights(currentNode); //TODO: add parent to nodeOperations
            for (Tile_Puzzle operation : nodeOperations) {
                numOfCreated++;
                if (!myQueue.contains(operation) && !closedList.contains(operation)) {
                    if (operation.equals(endingNode))
                        return getPath(operation);
                    myQueue.add(operation);
                    openList.add(operation);
                }
            }
        }
        return "";
    }



    String getPath(Tile_Puzzle operation){
        String path = operation.getCurrentPath().substring(1) + "\n";
        path += "Num: " + numOfCreated + "\n";
        path += "Cost: " + operation.getCostOfPath();
        return  path;
    }



}
