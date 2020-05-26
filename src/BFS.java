import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends absAlgorithm {

    public BFS(File_Reader file_reader,Tile_Puzzle startingNode, Tile_Puzzle endingNode) throws IOException {
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        Queue<Tile_Puzzle> myQueue = new LinkedList<>();
        HashSet<Tile_Puzzle> openList = new HashSet<>();
        HashSet<Tile_Puzzle> closedList = new HashSet<>();

        myQueue.add(startingNode);
        openList.add(startingNode);

        while (!myQueue.isEmpty()) {
            Tile_Puzzle currentNode = myQueue.poll();
            openList.remove(currentNode);
            closedList.add(currentNode);
            ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, false);
            numOfCreated += nodeOperations.size();
            for (Tile_Puzzle operation : nodeOperations) {
                if (!openList.contains(operation) && !closedList.contains(operation)) {
                    if (operation.equals(endingNode))
                        return getPath(operation, true);
                    myQueue.add(operation);
                    openList.add(operation);
                }
            }
        }
        return getPath( startingNode,false);
    }

    @Override
    public String getPath(Tile_Puzzle operation, boolean isPath){
        String path = "";
        if (isPath) {
            path = operation.getCurrentPath().substring(1) + "\n";
            path += "Num: " + numOfCreated + "\n";
            path += "Cost: " + operation.getCostOfPath();
        }
        else{

        }
        return path;
    }



}
