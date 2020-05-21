import java.io.IOException;

public class Ex1 {

    public static void main(String[] args) throws IOException {
        String path = "input.txt";

        File_Reader file_reader = new File_Reader(path);
        Algorithms algorithm = new Algorithms(file_reader);
        algorithm.Init();
    }


}
