import javax.imageio.IIOException;
import java.io.IOException;

public class Algorithms {

    private File_Reader file_reader;
    Algorithms(File_Reader file_reader) {
        this.file_reader = file_reader;
    }


    void Init() throws IOException {
        this.file_reader.getAllTiles();
        Tile_Puzzle startingNode = file_reader.getStartingTilePuzzle();
        Tile_Puzzle endingNode = file_reader.getEndingTilePuzzle();
        boolean isPossible = checkIfPossible(startingNode.getTileMat(), endingNode.getTileMat());

        if(isPossible){
            if (file_reader.getAlgorithm().equals("BFS")) {
                BFS bfs = new BFS(startingNode, endingNode);
                System.out.println(bfs.bfsAlgorithm());
            }
        }

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
