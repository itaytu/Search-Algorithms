import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Ex1 {
    //throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
    public static void main(String[] args) {
        String path = "input.txt";
        try {
            File_Reader file_reader = new File_Reader(path);
            String algName = file_reader.getAlgorithm();
            if (algName.equals("A*"))
                algName = "AStar";
            Class<absAlgorithm> _tempClass = (Class<absAlgorithm>) Class.forName(algName);
            Constructor<absAlgorithm> ctor = _tempClass.getDeclaredConstructor(File_Reader.class, Tile_Puzzle.class, Tile_Puzzle.class);
            absAlgorithm algorithm = ctor.newInstance(file_reader, file_reader.getStartingTilePuzzle(), file_reader.getEndingTilePuzzle());
            System.out.println(algorithm.Init());
        }
        catch (Exception e) {
            System.out.println("An error occurred during the program: " + e.getMessage());
        }
    }

}
