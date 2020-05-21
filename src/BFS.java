import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS  {

    Tile_Puzzle startingPos;
    Tile_Puzzle endingPos;

    BFS(Tile_Puzzle startingPos, Tile_Puzzle endingPos) {
        this.startingPos = startingPos;
        this.endingPos = endingPos;
    }


    void Init() {
        Queue<Tile_Puzzle> myQueue = new LinkedList<>();
        HashSet<Tile_Puzzle> closedList = new HashSet<>();
    }


}
