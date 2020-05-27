import java.util.ArrayList;

public class Operators {

    public static ArrayList<Tile_Puzzle> availableOperators(Tile_Puzzle node, boolean withWeights, String operation){
        int upLimit = 0;
        int leftLimit = 0;
        int downLimit = node.getTileMat().length - 1;
        int rightLimit = node.getTileMat()[0].length - 1;
        int[] blankPos = node.getBlankPosition();
        ArrayList<Tile_Puzzle> availableOps = new ArrayList<>();

        if(operation.equals("all") || operation.equals("L")) {
            if ((blankPos[1] != rightLimit) && node.getTileMat()[blankPos[0]][blankPos[1] + 1].getColor().isCanMove()) {
                Tile_Puzzle leftNode = operation(node, blankPos[0], blankPos[1] + 1, 3, "L", withWeights);
                if (leftNode != null)
                    availableOps.add(leftNode);
            }
        }

        if (operation.equals("all") || operation.equals("U")) {
            if ((blankPos[0] != downLimit) && node.getTileMat()[blankPos[0] + 1][blankPos[1]].getColor().isCanMove()) {
                Tile_Puzzle upNode = operation(node, blankPos[0] + 1, blankPos[1], 2, "U", withWeights);
                if (upNode != null)
                    availableOps.add(upNode);
            }
        }

        if (operation.equals("all") || operation.equals("R")) {
            if ((blankPos[1] != leftLimit) && node.getTileMat()[blankPos[0]][blankPos[1] - 1].getColor().isCanMove()) {
                Tile_Puzzle rightNode = operation(node, blankPos[0], blankPos[1] - 1, 1, "R", withWeights);
                if (rightNode != null)
                    availableOps.add(rightNode);
            }
        }

        if (operation.equals("all") || operation.equals("D")) {
            if ((blankPos[0] != upLimit) && node.getTileMat()[blankPos[0] - 1][blankPos[1]].getColor().isCanMove()) {
                Tile_Puzzle downNode = operation(node, blankPos[0] - 1, blankPos[1], 0, "D", withWeights);
                if (downNode != null)
                    availableOps.add(downNode);
            }
        }
        return availableOps;
    }


    private static Tile_Puzzle operation(Tile_Puzzle node, int positionX, int positionY, int direction, String movement, boolean withWeights) {
        Tile_Puzzle tmp = new Tile_Puzzle(node);
        tmp.swapTiles(positionX, positionY);
        tmp.setParentNode(node);
        if (!tmp.equals(node.getParentNode())) {
            String toAppend = "-" + node.getTileMat()[positionX][positionY].getIndex() + movement;
            tmp.addCost(node.getTileMat()[positionX][positionY].getColor().getValue());
            tmp.appendPath(toAppend);
            if (withWeights) {
                tmp.setfCost(tmp.getCostOfPath() + HeuristicFunc.manhattanDistanceInitialize(tmp));
                tmp.addNumOfIteration();
                tmp.setMoveDirection(direction);
            }
            return tmp;
        }
        return null;
    }

}
