public class Tile_Puzzle {

    private Tile[][] tilePuzzle;
    private Tile parentNode = null;

    private int[] blankPosition = new int[2];

    public Tile_Puzzle(int row, int col, int[] blankPosition){
        tilePuzzle = new Tile[row][col];

        setBlankPosition(blankPosition);
    }

    public Tile_Puzzle(int row, int col){
        tilePuzzle = new Tile[row][col];
    }

    public Tile_Puzzle(Tile[][] tilePuzzle) {
        this.tilePuzzle = tilePuzzle;
    }


    public Tile[][] getTilePuzzle() { return tilePuzzle; }

    public void setTilePuzzle(Tile[][] tilePuzzle) { this.tilePuzzle = tilePuzzle; }


    public Tile getParentNode() { return parentNode; }

    public void setParentNode(Tile parentNode) { this.parentNode = parentNode; }


    public int[] getBlankPosition() { return blankPosition; }

    public void setBlankPosition(int[] blankPosition) {
        this.blankPosition[0] = blankPosition[0];
        this.blankPosition[1] = blankPosition[1];
    }


    public boolean equals(Tile_Puzzle other) {
        for (int i = 0; i < tilePuzzle.length; i++){
            for (int j = 0; j < tilePuzzle[0].length; j++) {
                if(tilePuzzle[i][j].getIndex() != other.tilePuzzle[i][j].getIndex())
                    return false;
            }
        }
        return true;
    }

}




