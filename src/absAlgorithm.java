import java.io.IOException;

public abstract class absAlgorithm {

    File_Reader file_reader;

    Tile_Puzzle startingNode;
    Tile_Puzzle endingNode;

    int numOfCreated;
    int costOfPath;

    public absAlgorithm(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode) throws IOException {
        this.file_reader = file_reader;
        this.startingNode = startingNode;
        this.endingNode = endingNode;
        this.numOfCreated = 1;
        this.costOfPath = 0;
    }

    public abstract String Init();
    public abstract String getPath(Tile_Puzzle path, boolean isPath); //TODO: Generalize getPath for all


    public HeuristicFunc getHeuristicFunc(){
        return new HeuristicFunc();
    }

}
