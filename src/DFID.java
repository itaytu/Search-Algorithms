import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class DFID extends absAlgorithm {

    public DFID(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode) {
        super(file_reader, startingNode, endingNode);
    }

    @Override
    public String Init() {
        String result = "";
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            HashSet<Tile_Puzzle> openList = new HashSet<>();
            result = limited_DFS(startingNode, endingNode, i, openList);
            if (!result.equals("cutoff")) {
                if (result.equals("fail"))
                    return getPath(startingNode, false);
                return result;
            }
        }
        return result;
    }


    private String limited_DFS(Tile_Puzzle currentNode, Tile_Puzzle endingNode, int limit, HashSet<Tile_Puzzle> openList) {
        String result;
        String[] operations = {"L", "U", "R", "D"};

        if (currentNode.equals(endingNode))
            return getPath(currentNode, true);
        else if(limit == 0)
            return "cutoff";
        else {
            openList.add(currentNode);
            boolean isCutoff = false;
            for (String movement : operations) {
                ArrayList<Tile_Puzzle> nodeOperations = Operators.availableOperators(currentNode, false, movement);
                numOfCreated += nodeOperations.size();
                for (Tile_Puzzle tile_puzzle : nodeOperations) {
                    if (!openList.contains(tile_puzzle)) {
                        result = limited_DFS(tile_puzzle, endingNode, limit - 1, openList);
                        if (result.equals("cutoff")) {
                            isCutoff = true;
                        } else if (!result.equals("fail"))
                            return result;
                    }
                }
            }
            if (file_reader.getWithOpen()){
                for (Tile_Puzzle node : openList){
                    System.out.println(node.toString());
                }
                System.out.println("-----------------------------------------");
            }
            openList.remove(currentNode);
            if(isCutoff)
                return "cutoff";
            else
                return "fail";
        }
    }
}
