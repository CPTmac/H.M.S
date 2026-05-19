package files;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    /*
     * =========================
     * Write Data To File
     * =========================
     */

    public void writeData(
            String fileName,
            ArrayList<String> data
    ) {

        try (

                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(fileName)
                        )

        ) {

            // كتابة كل سطر
            for (String line : data) {

                writer.write(line);

                writer.newLine();
            }

            System.out.println(
                    "Data saved successfully."
            );

        }

        catch (IOException e) {

            System.out.println(
                    "Error writing file: "
                            + fileName
            );
        }
    }

    /*
     * =========================
     * Read Data From File
     * =========================
     */

    public ArrayList<String> readData(
            String fileName
    ) {

        ArrayList<String> lines =
                new ArrayList<>();

        try (

                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(fileName)
                        )

        ) {

            String line;

            // قراءة كل سطر
            while ((line = reader.readLine())
                    != null) {

                lines.add(line);
            }

        }

        catch (IOException e) {

            System.out.println(
                    "Error reading file: "
                            + fileName
            );
        }

        return lines;
    }
}