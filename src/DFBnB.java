import java.io.IOException;
import java.util.*;

public class DFBnB extends absAlgorithm {

    public DFBnB(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode){
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        if (!checkIfPossible(startingNode.getTileMat(), endingNode.getTileMat()))
            return getPath(startingNode, false);

        Stack<Tile_Puzzle> nodeStack = new Stack<>();
        Hashtable<Tile_Puzzle, Tile_Puzzle> openList = new Hashtable<>();
        Tile_Puzzle result = startingNode;
        int t = Integer.MAX_VALUE;

        nodeStack.add(startingNode);
        openList.put(startingNode, startingNode);
        while (!nodeStack.isEmpty()) {
            Tile_Puzzle currentNode = nodeStack.pop();
            if (currentNode.getOut())
                openList.remove(currentNode);
            else {
                    currentNode.setOut(true);
                    nodeStack.add(currentNode);
                    ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, true, "all");
                    nodeOperations.sort(Tile_Puzzle::compareTo);
                    numOfCreated+= nodeOperations.size();
                    for (int i = 0; i < nodeOperations.size(); i++) {
                        if(nodeOperations.get(i).getfCost() >= t) {
                            nodeOperations.subList(i, nodeOperations.size()).clear();
                            break;
                        }
                        else if (openList.containsKey(nodeOperations.get(i)) && openList.get(nodeOperations.get(i)).getOut()){
                            nodeOperations.remove(nodeOperations.get(i));
                            i--;
                        }
                        else if  (openList.containsKey(nodeOperations.get(i)) && !openList.get(nodeOperations.get(i)).getOut()) {
                            if (openList.get(nodeOperations.get(i)).getfCost() <= nodeOperations.get(i).getfCost()) {
                                nodeOperations.remove(nodeOperations.get(i));
                                i--;
                            }
                            else {
                                openList.remove(nodeOperations.get(i));
                                nodeStack.remove(nodeOperations.get(i));
                            }
                        }
                        else if (nodeOperations.get(i).equals(endingNode)){
                            t = nodeOperations.get(i).getfCost();
                            result = new Tile_Puzzle(nodeOperations.get(i));
                            nodeOperations.subList(i, nodeOperations.size()).clear();
                            break;
                        }
                    }
                Collections.reverse(nodeOperations);
                for (Tile_Puzzle node : nodeOperations) {
                    nodeStack.push(node);
                    openList.put(node, node);
                }
            }
        }
        return getPath(result, true);
    }
}
