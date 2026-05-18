package files;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandler {

    public void writeData(String fileName, String data) {

        try {
            FileWriter file = new FileWriter(fileName);
            file.write(data);
            file.close();

        } catch(Exception e) {
            System.out.println("Error writing file: " + fileName);
        }
    }

    public void readData(String fileName) {

        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            while(input.hasNextLine()) {
                System.out.println(input.nextLine());
            }

            input.close();

        } catch(Exception e) {
            System.out.println("Error reading file: " + fileName);
        }
    }
}