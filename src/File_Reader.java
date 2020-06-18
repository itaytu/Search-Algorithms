import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class File_Reader {
    /**
     *  This class is in charge of parsing all the information from the input txt file and extracting
     *  all the relevant information needed for the different calculations.
     */
    private String path;
    private Map<Integer, Tile> allTiles = new HashMap<>();

    public File_Reader(String path) {
        this.path = path;
    }

    //Extract the wanted algorithm
    public String getAlgorithm(){
        try {
            return Files.readAllLines(Paths.get(path)).get(0);
        }
        catch (IOException e){
            return null;
        }
    }

    //Extract if with time or not
    public boolean getWithTime() {
        try {
            return Files.readAllLines(Paths.get(path)).get(1).equals("with time");
        }
        catch (IOException e) {
            return false;
        }
    }

    //Extract if with open or not
    public boolean getWithOpen(){
        try {
            return Files.readAllLines(Paths.get(path)).get(2).equals("with open");
        }
        catch (IOException e){ return false; }
    }

    //Get the number of black tiles
    public int getNumOfBlacks() {
        String s = "";
        try {
            s = Files.readAllLines(Paths.get(path)).get(0);
            s = s.replaceAll(" ", "");
            String sub = s.substring(s.indexOf(':') + 1);
            String[] tiles = sub.split(",");
            return tiles.length;
        }
        catch (IOException e){
            return 0;
        }
    }

    //Get the starting state of the tile puzzle
    public Tile_Puzzle getStartingTilePuzzle(){
        try {
            getAllTiles();
            int[] tileSize = getTileSize(Files.readAllLines(Paths.get(path)).get(3));
            Tile[][] tileMat = new Tile[tileSize[0]][tileSize[1]];
            int[] blankPosition = new int[2];
            for (int i = 0; i < tileSize[0]; i++) {
                String[] row = Files.readAllLines(Paths.get(path)).get(i + 6).split(",");
                for (int j = 0; j < row.length; j++) {
                    int index;
                    if (row[j].equals("_")) {
                        blankPosition[0] = i;
                        blankPosition[1] = j;
                        index = -1;
                    } else
                        index = Integer.parseInt(row[j]);
                    tileMat[i][j] = allTiles.get(index);
                }
            }
            Tile_Puzzle tile_puzzle = new Tile_Puzzle(tileMat);
            tile_puzzle.setBlankPosition(blankPosition);
            return tile_puzzle;
        }
        catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    //Get the ending state of the tile puzzle
    public Tile_Puzzle getEndingTilePuzzle(){
        try {
            int[] tileSize = getTileSize(Files.readAllLines(Paths.get(path)).get(3));
            Tile[][] tileMat = new Tile[tileSize[0]][tileSize[1]];
            int index = 1;
            int numOfValues = tileSize[0] * tileSize[1] - 1;
            for (int i = 0; i < tileSize[0]; i++) {
                for (int j = 0; j < tileSize[1]; j++) {
                    if (index <= numOfValues) {
                        tileMat[i][j] = new Tile(allTiles.get(index));
                        index++;
                    }
                }
            }
            tileMat[tileSize[0] - 1][tileSize[1] - 1] = new Tile(allTiles.get(-1));
            Tile_Puzzle tile_puzzle = new Tile_Puzzle(tileMat);
            int[] blackPosition = new int[2];
            blackPosition[0] = tileMat.length - 1;
            blackPosition[1] = tileMat[0].length - 1;
            tile_puzzle.setBlankPosition(blackPosition);
            return tile_puzzle;
        }
        catch (IOException e){
            System.out.println(e.toString());
            return null;
        }
    }

    //Extract the tile puzzle size
    private int[] getTileSize(String s) {
        int index = s.indexOf('x');
        int[] tileSize = new int[2];
        tileSize[0] = Integer.parseInt(s.substring(0, index));
        tileSize[1] = Integer.parseInt(s.substring(index + 1));
        return tileSize;
    }

    //Extract all the tiles
    private void getAllTiles() {
        try {
            int[] tileSize = getTileSize(Files.readAllLines(Paths.get(path)).get(3));
            getTiles(Files.readAllLines(Paths.get(path)).get(4), color.BLACK);
            getTiles(Files.readAllLines(Paths.get(path)).get(5), color.RED);
            fillLeftTiles(tileSize);
            Tile blankTile = new Tile(color.WHITE, -1);
            allTiles.put(-1, blankTile);
        }
        catch (IOException e){ System.out.println(e.toString()); }
    }

    //Extract the tiles from each type
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

    //Create all the tiles that weren't generated yet
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
