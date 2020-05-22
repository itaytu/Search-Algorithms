import java.util.ArrayList;

public class Operators {

    //private static int upLimit = 0;
    //private static int downLimit;
    //private static int leftLimit = 0;
    //private static int rightLimit;

    public static ArrayList<Tile_Puzzle> availableOperatorsWithoutWeights(Tile_Puzzle node){
        int upLimit = 0;
        int leftLimit = 0;
        int downLimit = node.getTileMat().length - 1;
        int rightLimit = node.getTileMat()[0].length - 1;
        int[] blankPos = node.getBlankPosition();
        ArrayList<Tile_Puzzle> availableOps = new ArrayList<>();

        if((blankPos[1] != rightLimit) && node.getTileMat()[blankPos[0]][blankPos[1] + 1].getColor().isCanMove()){
            Tile_Puzzle tmp = new Tile_Puzzle(node);
            tmp.swapTiles(blankPos[0], blankPos[1] + 1);
            tmp.setParentNode(node);
            if(!tmp.equals(node.getParentNode())) {
                String toAppend = "-" + node.getTileMat()[blankPos[0]][blankPos[1] + 1].getIndex() + "L";
                tmp.addCost(node.getTileMat()[blankPos[0]][blankPos[1] + 1].getColor().getValue());
                tmp.appendPath(toAppend);
                availableOps.add(tmp);
            }
        }

        if((blankPos[0] != downLimit) && node.getTileMat()[blankPos[0] + 1][blankPos[1]].getColor().isCanMove()){
            Tile_Puzzle tmp = new Tile_Puzzle(node);
            tmp.swapTiles(blankPos[0] + 1, blankPos[1]);
            tmp.setParentNode(node);
            if(!tmp.equals(node.getParentNode())) {
                String toAppend = "-" + node.getTileMat()[blankPos[0] + 1][blankPos[1]].getIndex() + "U";
                tmp.addCost(node.getTileMat()[blankPos[0] + 1][blankPos[1]].getColor().getValue());
                tmp.appendPath(toAppend);
                availableOps.add(tmp);
            }
        }

        if((blankPos[1] != leftLimit) && node.getTileMat()[blankPos[0]][blankPos[1] - 1].getColor().isCanMove()){
            Tile_Puzzle tmp = new Tile_Puzzle(node);
            tmp.swapTiles(blankPos[0], blankPos[1] - 1);
            tmp.setParentNode(node);
            if(!tmp.equals(node.getParentNode())) {
                String toAppend = "-" + node.getTileMat()[blankPos[0]][blankPos[1] - 1].getIndex() + "R";
                tmp.addCost(node.getTileMat()[blankPos[0]][blankPos[1] - 1].getColor().getValue());
                tmp.appendPath(toAppend);
                availableOps.add(tmp);
            }
        }

        if((blankPos[0] != upLimit) && node.getTileMat()[blankPos[0] - 1][blankPos[1]].getColor().isCanMove()){
            Tile_Puzzle tmp = new Tile_Puzzle(node);
            tmp.swapTiles(blankPos[0] - 1, blankPos[1]);
            tmp.setParentNode(node);
            if(!tmp.equals(node.getParentNode())) {
                String toAppend = "-" + node.getTileMat()[blankPos[0] - 1][blankPos[1]].getIndex() + "D";
                tmp.addCost(node.getTileMat()[blankPos[0] - 1][blankPos[1]].getColor().getValue());
                tmp.appendPath(toAppend);
                availableOps.add(tmp);
            }
        }
        return availableOps;
    }
}
