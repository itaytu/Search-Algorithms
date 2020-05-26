import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class File_Reader {
    private String path;
    private Map<Integer, Tile> allTiles = new HashMap<>();

    public File_Reader(String path) {
        this.path = path;
    }

    public String getAlgorithm() throws IOException{
        return Files.readAllLines(Paths.get(path)).get(0);
    }

    public boolean getWithTime() throws IOException {
        return Files.readAllLines(Paths.get(path)).get(1).equals("with time");
    }

    public boolean getWithOpen() throws IOException {
        return Files.readAllLines(Paths.get(path)).get(2).equals("with open");
    }

    public void getAllTiles() throws IOException {
        int[] tileSize = getTileSize(Files.readAllLines(Paths.get(path)).get(3));
        getTiles(Files.readAllLines(Paths.get(path)).get(4), color.BLACK);
        getTiles(Files.readAllLines(Paths.get(path)).get(5), color.RED);
        fillLeftTiles(tileSize);
        Tile blankTile = new Tile(color.WHITE, -1);
        allTiles.put(-1, blankTile);
    }

    public Tile_Puzzle getStartingTilePuzzle() throws IOException {
        getAllTiles();
        int[] tileSize = getTileSize(Files.readAllLines(Paths.get(path)).get(3));
        Tile[][] tileMat = new Tile[tileSize[0]][tileSize[1]];
        int[] blankPosition = new int[2];
       for (int i = 0; i < tileSize[0]; i++) {
           String[] row = Files.readAllLines(Paths.get(path)).get(i + 6).split(",");
           for (int j = 0; j < row.length; j++) {
               int index;
               if(row[j].equals("_")){
                   blankPosition[0] = i;
                   blankPosition[1] = j;
                   index = -1;
               }
               else
                   index = Integer.parseInt(row[j]);
               tileMat[i][j] = allTiles.get(index);
           }
       }
       Tile_Puzzle tile_puzzle = new Tile_Puzzle(tileSize[0], tileSize[1], blankPosition);
       tile_puzzle.setTileMat(tileMat);
       return tile_puzzle;
    }

    public Tile_Puzzle getEndingTilePuzzle() throws IOException {
        int[] tileSize = getTileSize(Files.readAllLines(Paths.get(path)).get(3));
        Tile[][] tileMat = new Tile[tileSize[0]][tileSize[1]];
        int index = 1;
        for (int i = 0; i < tileSize[0]; i++) {
            for (int j = 0; j < tileSize[1]; j++) {
                tileMat[i][j] = new Tile(color.NONE, index);
                index++;
            }
        }
        tileMat[tileSize[0] - 1][tileSize[1] - 1] = new Tile(color.NONE, -1);
        Tile_Puzzle tile_puzzle = new Tile_Puzzle(tileMat);
        int[] blackPosition = new int[2];
        blackPosition[0] = tileMat.length - 1;
        blackPosition[1] = tileMat[0].length - 1;
        tile_puzzle.setBlankPosition(blackPosition);
        return tile_puzzle;
    }


    private int[] getTileSize(String s) {
        int index = s.indexOf('x');
        int[] tileSize = new int[2];
        tileSize[0] = Integer.parseInt(s.substring(0, index));
        tileSize[1] = Integer.parseInt(s.substring(index + 1));
        return tileSize;
    }

    private void getTiles(String s, color color) {
        s = s.replaceAll(" ", "");
        String sub = s.substring(s.indexOf(':') + 1);

        if (sub.isEmpty())
            return;

        String[] tiles = sub.split(",");
        for (String tile : tiles){
            int index =  Integer.parseInt(tile);
            Tile tmp = new Tile(color, index);
            allTiles.put(index, tmp);
        }
    }

    private void fillLeftTiles(int[] tileSize) {
        int numOfIndexes = tileSize[0] * tileSize[1];
        for (int i = 1; i <  numOfIndexes; i++){
            if(!allTiles.containsKey(i)) {
                Tile tmp = new Tile(color.GREEN, i);
                allTiles.put(i, tmp);
            }
        }
    }

}
