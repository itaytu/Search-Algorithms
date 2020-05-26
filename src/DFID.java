import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class DFID extends absAlgorithm {

    public DFID(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode) throws IOException {
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        String result = "";
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            HashSet<Tile_Puzzle> pathSet = new HashSet<>();
            result = limited_DFS(startingNode, endingNode, i, pathSet);
            if (!result.equals("cutoff")) return result;
        }
        return result;
    }


    private String limited_DFS(Tile_Puzzle currentNode, Tile_Puzzle endingNode, int limit, HashSet<Tile_Puzzle> pathSet) {
        String result;
        if (currentNode.equals(endingNode))
            return getPath(currentNode, true);
        else if(limit == 0)
            return "cutoff";
        else {
            pathSet.add(currentNode);
            boolean isCutoff = false;
            ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, false);
            numOfCreated+= nodeOperations.size();
            for (Tile_Puzzle tile_puzzle : nodeOperations) {
                if (!pathSet.contains(tile_puzzle)){
                    result = limited_DFS(tile_puzzle, endingNode, limit-1, pathSet);
                    if (result.equals("cutoff")){
                        isCutoff = true;
                    }
                    else if (!result.equals("fail"))
                        return result;
                }
            }
            pathSet.remove(currentNode);
            if(isCutoff)
                return "cutoff";
            else
                return "fail";
        }
    }



    @Override
    public String getPath(Tile_Puzzle operation, boolean isPath) { //TODO: Generalize getPath
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
