public class HeuristicFunc {

    /**
     * This function calculates the heuristic function of a given tile puzzle state
     * @param startingNode
     * @return int
     */
    public static int manhattanDistanceInitialize(Tile_Puzzle startingNode){
        int value = 0;
        int rowLength = startingNode.getTileMat()[0].length;

        for (int i = 0; i < startingNode.getTileMat().length; i++) {
            for (int j = 0; j < startingNode.getTileMat()[0].length; j++) {
                Tile currentTile = startingNode.getTileMat()[i][j];
                int rowExpected = (currentTile.getIndex() - 1)/rowLength;
                int colExpected = (currentTile.getIndex() - 1)%rowLength;

                int heuristicValue = Math.abs(rowExpected - i) + Math.abs(colExpected - j);
                value += currentTile.getColor().getValue() * heuristicValue;
            }
        }
        return value;
    }

}
