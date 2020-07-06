import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Constructor;

public class Ex1 {
    public static void main(String[] args) {
        String path = "input.txt";
        try {
            File_Reader file_reader = new File_Reader(path);
            String algName = file_reader.getAlgorithm();
            if (algName.equals("A*"))
                algName = "AStar";
            if (algName.equals("IDA*"))
                algName = "IDAStar";
            Class<absAlgorithm> _tempClass = (Class<absAlgorithm>) Class.forName(algName);
            Constructor<absAlgorithm> ctor = _tempClass.getDeclaredConstructor(File_Reader.class, Tile_Puzzle.class, Tile_Puzzle.class);
            absAlgorithm algorithm = ctor.newInstance(file_reader, file_reader.getStartingTilePuzzle(), file_reader.getEndingTilePuzzle());
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(algorithm.Init());
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
