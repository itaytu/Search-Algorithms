import java.util.Arrays;

public class Tile_Puzzle implements Comparable<Tile_Puzzle>{

    private Tile[][] tileMat;
    private Tile_Puzzle parentNode = null;

    private int[] blankPosition = new int[2];
    private String currentPath;

    private int costOfPath;
    private int fCost;
    private int numOfIteration;
    private int moveDirection;
    private boolean isOut;

    public Tile_Puzzle(int row, int col, int[] blankPosition){
        tileMat = new Tile[row][col];
        setBlankPosition(blankPosition);
        currentPath = "";
        costOfPath = 0;
        fCost = 0;
        numOfIteration = 0;
        moveDirection = -1;
        isOut = false;
    }

    public Tile_Puzzle(Tile[][] tileMat) {
        this.tileMat = tileMat;
        currentPath = "";
        costOfPath = 0;
        fCost = 0;
        numOfIteration = 0;
        moveDirection = -1;
        isOut = false;
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
        numOfIteration = other.numOfIteration;
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


    public int getfCost() { return this.fCost; }

    public void setfCost(int cost) { this.fCost = cost; }


    public int getNumOfIteration() { return this.numOfIteration; }

    public void addNumOfIteration() { this.numOfIteration++; }


    public void setMoveDirection(int direction) { this.moveDirection = direction; }

    public int getMoveDirection() { return this.moveDirection; }


    public void setOut(boolean isOut) { this.isOut = isOut; }

    public boolean getOut() { return this.isOut; }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Tile[] tiles : tileMat) {
            s.append(Arrays.toString(tiles)).append(" ");
        }
        return s.toString();
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
        int hashing = 0;
        int index = 1;
        for (int i = 0; i < tileMat.length; i++) {
            for (int j = 0; j < tileMat[0].length; j++) {
                hashing += (tileMat[i][j].getIndex() * index) ^ 2;
                index++;
            }
        }
        return hashing;
    }

    @Override
    public int compareTo(Tile_Puzzle other) {
       if(this.getfCost() - other.getfCost() == 0){
           if(this.getNumOfIteration() == other.getNumOfIteration()){
               return other.getMoveDirection() - this.getMoveDirection();
           }
           else if (this.getNumOfIteration() > other.getNumOfIteration())
               return 1;
           else
               return -1;
       }
       else if(this.getfCost() - other.getfCost() > 0)
           return 1;
       else
           return -1;
    }
}




