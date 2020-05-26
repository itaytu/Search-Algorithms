import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class IDAStar extends absAlgorithm {

    public IDAStar(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode) throws IOException {
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        Stack<Tile_Puzzle> nodeStack = new Stack<>();
        Hashtable<Tile_Puzzle, Tile_Puzzle> openList = new Hashtable<>();
        int t = HeuristicFunc.manhattanDistanceInitialize(startingNode);

        while (t != Integer.MAX_VALUE){
            int minF = Integer.MAX_VALUE;
            nodeStack.add(startingNode);
            openList.put(startingNode, startingNode);
            while (!nodeStack.isEmpty()) {
                Tile_Puzzle currentNode = nodeStack.pop();
                if (currentNode.getOut())
                    openList.remove(currentNode);
                else {
                    currentNode.setOut(true);
                    ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, true);
                    numOfCreated+= nodeOperations.size();
                    for (Tile_Puzzle operation : nodeOperations) {
                        if(operation.getfCost() > t){
                            minF = Math.min(minF, operation.getfCost());
                            continue;
                        }
                        if(openList.contains(operation) && openList.get(operation).getOut())
                            continue;
                        if(openList.contains(operation) && (!openList.get(operation).getOut())) {
                            if(operation.getfCost() < openList.get(operation).getfCost()){
                                nodeStack.remove(operation);
                                openList.remove(operation);
                            }
                            else
                                continue;
                        }
                        if (operation.equals(endingNode))
                            return getPath(operation, true);
                        nodeStack.add(operation);
                        openList.put(operation, operation);
                    }
                }
            }
            t = minF;
        }
        return "";
    }

    @Override
    public String getPath(Tile_Puzzle operation, boolean isPath) {
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
