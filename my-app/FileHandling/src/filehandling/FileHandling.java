package filehandling;

import java.io.*;

public class FileHandling {
    public static void main(String[] args) {
        BufferedReader reader = null;

        try {
            System.out.println("Enter the file name: ");
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = inputReader.readLine(); // Get the file name from the user

            reader = new BufferedReader(new FileReader(fileName)); // Use the fileName variable!
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException ev) {
            System.out.println("The file is not found: " + ev.getMessage());

        } catch (IOException ev) {
            System.out.println("An error occurred while reading file: " + ev.getMessage());

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    System.out.println("File reader is closed");
                }
            } catch (IOException e) {
                System.out.println("Error closing the reader: " + e.getMessage());
            }
        }
    }
}