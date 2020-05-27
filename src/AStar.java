import java.io.IOException;
import java.util.*;

public class AStar extends absAlgorithm{

    public AStar(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode) {
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        if (!checkIfPossible(startingNode.getTileMat(), endingNode.getTileMat()))
            return getPath(startingNode, false);
        PriorityQueue<Tile_Puzzle> myQueue = new PriorityQueue<>();
        Hashtable<Tile_Puzzle, Integer> openList = new Hashtable<>();
        Hashtable<Tile_Puzzle, Integer> closedList = new Hashtable<>();
        String[] operations = {"L", "U", "R", "D"};

        myQueue.add(startingNode);
        openList.put(startingNode, 0);

        while (!myQueue.isEmpty()){
            Tile_Puzzle currentNode = myQueue.poll();
            openList.remove(currentNode);

            if (currentNode.equals(endingNode))
                return getPath(currentNode, true);

            closedList.put(currentNode, currentNode.getfCost());
            for (String movement : operations) {
                ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, true, movement);
                numOfCreated += nodeOperations.size();
                for (Tile_Puzzle node : nodeOperations) {
                    if (!openList.containsKey(node) && !closedList.containsKey(node)) {
                        myQueue.add(node);
                        openList.put(node, node.getfCost());
                    } else if (openList.containsKey(node) && openList.get(node) > node.getfCost()) {
                        openList.remove(node);
                        myQueue.remove(node);
                        openList.put(node, node.getfCost());
                        myQueue.add(node);
                    }
                }
            }
        }
        return getPath(startingNode, false);
    }
}
