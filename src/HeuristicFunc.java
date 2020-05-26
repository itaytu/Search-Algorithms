public class HeuristicFunc {


    public static int manhattanDistance(Tile_Puzzle currentNode, int[] tilePosition){
        Tile currentTile = currentNode.getTileMat()[tilePosition[0]][tilePosition[1]];

        int rowLength = currentNode.getTileMat()[0].length;
        int value = currentTile.getIndex();
        int rowExpected = (value - 1)/rowLength;
        int colExpected = (value - 1)%rowLength;

        int heuristicValue = Math.abs(rowExpected - tilePosition[0]) + Math.abs(colExpected - tilePosition[1]);
        return currentTile.getColor().getValue() * heuristicValue;
    }


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
