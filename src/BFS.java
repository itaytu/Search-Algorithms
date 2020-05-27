import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends absAlgorithm {

    public BFS(File_Reader file_reader,Tile_Puzzle startingNode, Tile_Puzzle endingNode) {
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        if (!checkIfPossible(startingNode.getTileMat(), endingNode.getTileMat()))
            return getPath(startingNode, false);
        Queue<Tile_Puzzle> myQueue = new LinkedList<>();
        HashSet<Tile_Puzzle> openList = new HashSet<>();
        HashSet<Tile_Puzzle> closedList = new HashSet<>();

        myQueue.add(startingNode);
        openList.add(startingNode);

        while (!myQueue.isEmpty()) {
            Tile_Puzzle currentNode = myQueue.poll();
            openList.remove(currentNode);
            closedList.add(currentNode);
            ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, false, "all");
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
        return getPath(startingNode,false);
    }
}
