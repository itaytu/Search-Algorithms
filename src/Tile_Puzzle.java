import java.util.Arrays;

public class Tile_Puzzle {

    private Tile[][] tileMat;
    private Tile_Puzzle parentNode = null;

    private int[] blankPosition = new int[2];
    private String currentPath;

    private int costOfPath;

    public Tile_Puzzle(int row, int col, int[] blankPosition){
        tileMat = new Tile[row][col];
        setBlankPosition(blankPosition);
        currentPath = "";
        costOfPath = 0;
    }

    public Tile_Puzzle(Tile[][] tileMat) {
        this.tileMat = tileMat;
        currentPath = "";
        costOfPath = 0;
    }

    public Tile_Puzzle(Tile_Puzzle other) {
        this.tileMat = new Tile[other.tileMat.length][other.tileMat[0].length];
        for (int i = 0; i < tileMat.length; i++) {
            for (int j = 0; j < tileMat[0].length; j++) {
                this.tileMat[i][j] = new Tile(other.tileMat[i][j]);
            }
        }

        parentNode = null;
        setBlankPosition(other.blankPosition);
        currentPath = other.currentPath;
        costOfPath = other.costOfPath;
    }

    public Tile[][] getTileMat() { return tileMat; }

    public void setTileMat(Tile[][] tileMat) { this.tileMat = tileMat; }


    public Tile_Puzzle getParentNode() { return parentNode; }

    public void setParentNode(Tile_Puzzle parentNode) { this.parentNode = parentNode; }


    public int[] getBlankPosition() { return blankPosition; }

    public void setBlankPosition(int[] blankPosition) {
        this.blankPosition[0] = blankPosition[0];
        this.blankPosition[1] = blankPosition[1];
    }

    public void swapTiles(int row, int col) {
        Tile tmp = new Tile(tileMat[row][col]);
        tileMat[row][col] = new Tile(color.WHITE, -1);
        tileMat[blankPosition[0]][blankPosition[1]] = tmp;
        setBlankPosition(new int[] {row, col});
    }

    public void appendPath(String path) {
        this.currentPath += path;
    }

    public String getCurrentPath() { return this.currentPath; }

    public void addCost(int costOfPath) {
        this.costOfPath += costOfPath;
    }

    public int getCostOfPath() { return this.costOfPath; }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tileMat.length; i++){
            s += Arrays.toString(tileMat[i]) + "\n";
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile_Puzzle that = (Tile_Puzzle) o;
        for (int i = 0; i < tileMat.length; i++) {
            if(!Arrays.equals(tileMat[i], that.tileMat[i]))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tileMat);
    }
}




