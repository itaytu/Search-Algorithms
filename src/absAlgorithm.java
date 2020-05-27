import java.io.IOException;

public abstract class absAlgorithm {

    File_Reader file_reader;

    Tile_Puzzle startingNode;
    Tile_Puzzle endingNode;

    int numOfCreated;
    int costOfPath;

    public absAlgorithm(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode){
        this.file_reader = file_reader;
        this.startingNode = startingNode;
        this.endingNode = endingNode;
        this.numOfCreated = 1;
        this.costOfPath = 0;
    }

    public abstract String Init();
    public String getPath(Tile_Puzzle answer, boolean isPath) {
        String path = "";
        if (isPath) {
            path = answer.getCurrentPath().substring(1) + "\n";
            path += "Num: " + numOfCreated + "\n";
            path += "Cost: " + answer.getCostOfPath();
            if (file_reader.getWithTime()){

            }
        }
        else{
            path = "no path" + "\n";
            path += "Num: " + numOfCreated;
        }
        return path;

    } //TODO: Generalize getPath for all


    public HeuristicFunc getHeuristicFunc(){
        return new HeuristicFunc();
    }

    boolean checkIfPossible(Tile[][] startNode, Tile[][] endNode) {
        for (int i = 0; i < startNode.length; i++) {
            for(int j = 0; j < startNode[0].length; j++) {
                if(!startNode[i][j].equals(endNode[i][j]) && startNode[i][j].getColor() == color.BLACK)
                    return false;
            }
        }
        return true;
    }


}
