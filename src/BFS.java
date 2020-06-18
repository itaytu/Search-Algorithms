import java.util.*;

public class BFS extends absAlgorithm {

    public BFS(File_Reader file_reader,Tile_Puzzle startingNode, Tile_Puzzle endingNode) {
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        if (checkIfNotPossible(startingNode.getTileMat(), endingNode.getTileMat()))
            return getPath(startingNode, false);
        Queue<Tile_Puzzle> myQueue = new LinkedList<>();
        HashSet<Tile_Puzzle> openList = new HashSet<>();
        HashSet<Tile_Puzzle> closedList = new HashSet<>();
        String[] operations = {"L", "U", "R", "D"};

        myQueue.add(startingNode);
        openList.add(startingNode);

        while (!myQueue.isEmpty()) {
            Tile_Puzzle currentNode = myQueue.poll();
            if (file_reader.getWithOpen()) {
                for (Tile_Puzzle node : openList){
                    System.out.println(node.toString());
                }
                System.out.println("-----------------------------------------");
            }
            openList.remove(currentNode);
            closedList.add(currentNode);
            for (String movement : operations) {
                ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, false, movement);
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
        }
        return getPath(startingNode,false);
    }
}
