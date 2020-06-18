import java.util.*;

public class DFBnB extends absAlgorithm {

    public DFBnB(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode){
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        if (checkIfNotPossible(startingNode.getTileMat(), endingNode.getTileMat()))
            return getPath(startingNode, false);

        Stack<Tile_Puzzle> nodeStack = new Stack<>();
        Hashtable<Tile_Puzzle, Tile_Puzzle> openList = new Hashtable<>();
        Tile_Puzzle result = startingNode;
        int n = file_reader.getNumOfBlacks();
        int t = Math.min(factorial(n), Integer.MAX_VALUE);
        nodeStack.add(startingNode);
        openList.put(startingNode, startingNode);
        while (!nodeStack.isEmpty()) {
            Tile_Puzzle currentNode = nodeStack.pop();
            if (file_reader.getWithOpen()){
                openList.forEach((k, v) -> System.out.println(k.toString()));
                System.out.println("-----------------------------------------");
            }
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


    private int factorial(int num) {
        int result = 1;
        for (int i = 2; i <= num ; i++) {
            result *= i;
        }
        return result;
    }

}
