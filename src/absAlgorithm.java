public abstract class absAlgorithm {

    /**
        This class represents the interface for an algorithm.
        It has the Constructor to initialize all the variables needed for the calculations.
     **/
    File_Reader file_reader;

    Tile_Puzzle startingNode;
    Tile_Puzzle endingNode;

    int numOfCreated;
    int costOfPath;
    long algorithmStartTime = 0;

    public absAlgorithm(File_Reader file_reader, Tile_Puzzle startingNode, Tile_Puzzle endingNode){
        this.file_reader = file_reader;
        this.startingNode = startingNode;
        this.endingNode = endingNode;
        this.numOfCreated = 1;
        this.costOfPath = 0;
        this.algorithmStartTime = System.currentTimeMillis();
    }

    /**
     * This method runs the algorithms.
      * @return string results.
     */
    public abstract String Init();

    /**
     * This method prints the wanted results from the algorithm calculations
     * @param answer - the returned answer from the algorithm
     * @param isPath - indicates if there is a path from the starting point to the goal
     * @return string
     */
    public String getPath(Tile_Puzzle answer, boolean isPath) {
        String path;
        if (isPath) {
            path = answer.getCurrentPath().substring(1) + "\n";
            path += "Num: " + numOfCreated + "\n";
            path += "Cost: " + answer.getCostOfPath();
            if (file_reader.getWithTime()){
                path += "\n" + (double) (System.currentTimeMillis() - algorithmStartTime)/1000 + " seconds";
            }
        }
        else{
            path = "no path" + "\n";
            path += "Num: " + numOfCreated;
        }
        return path;
    }


    /**
     * This method checks if it's possible to even use the algorithm by checking the position of the black tiles.
     * @param startNode - starting state
     * @param endNode - wanted state
     * @return boolean
     */
    boolean checkIfNotPossible(Tile[][] startNode, Tile[][] endNode) {
        for (int i = 0; i < startNode.length; i++) {
            for(int j = 0; j < startNode[0].length; j++) {
                if(!startNode[i][j].equals(endNode[i][j]) && startNode[i][j].getColor() == color.BLACK)
                    return true;
            }
        }
        return false;
    }


}
