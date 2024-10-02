import java.util.Scanner;
import java.io.*;

/* This class reads numbers from a file, calculates the
   mean and standard deviation, and writes the results
   to a file.
*/
public class StatsDemo {
    // TASK #3 Add the throws clause
    public static void main(String[] args) throws IOException {
        double value = 0;       // To hold a value from the file
        double sum = 0;         // The sum of the numbers
        int count = 0;          // The number of numbers added
        double mean = 0;        // The average of the numbers
        double stdDev = 0;      // The standard deviation
        double difference;      // The value and mean difference
        double sumOfSquares = 0;  // Sum of squares of differences

        // Create an object of type Scanner
        Scanner keyboard = new Scanner(System.in);
        String filename;        // The user input file name

        // Prompt the user and read in the file name
        System.out.println("This program calculates " +
                "statistics on a file " +
                "containing a series of numbers.");
        System.out.println("Enter the file name (e.g., Numbers.txt or provide an absolute path): ");
        filename = keyboard.nextLine();

        // Check if the file exists before proceeding
        File inputFile = new File(filename);
        if (!inputFile.exists()) {
            System.out.println("Error: The file '" + filename + "' does not exist.");
            return; // Exit the program
        }

        // ADD LINES FOR TASK #4 HERE
        // Create a Scanner object passing it a File object with the name of the file
        System.out.println("Reading from file: " + inputFile.getAbsolutePath());

        // Use try-with-resources to ensure that the Scanner is closed automatically
        try (Scanner inputFileScanner = new Scanner(inputFile)) {
            // Loop until you are at the end of the file
            while (inputFileScanner.hasNextDouble()) {
                // Read a double value from the file
                value = inputFileScanner.nextDouble();

                // Add the value to sum
                sum += value;

                // Increment the counter
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
            return;
        }

        // Store the calculated mean in the mean variable
        if (count == 0) {
            System.out.println("Error: The file is empty or contains no valid numbers.");
            return;
        }

        mean = sum / count;

        // Debugging: Output the mean calculation
        System.out.println("Mean calculated: " + mean);

        /// ADD LINES FOR TASK #5 HERE
        // Reopen the file to calculate the standard deviation
        try (Scanner inputFileScanner2 = new Scanner(inputFile)) {
            // Reinitialize sum of squares and count
            sumOfSquares = 0;
            count = 0;

            // Loop until you are at the end of the file
            while (inputFileScanner2.hasNextDouble()) {
                // Read a double value from the file
                value = inputFileScanner2.nextDouble();

                // Subtract the mean from the value
                difference = value - mean;

                // Add the square of the difference to the sum of squares
                sumOfSquares += Math.pow(difference, 2);

                // Increment the counter
                count++;
            }
        }

        // Store the calculated variance and standard deviation
        double variance = sumOfSquares / count;
        stdDev = Math.sqrt(variance);

        // Debugging: Output the standard deviation calculation
        System.out.println("Standard deviation calculated: " + stdDev);

        // ADD LINES FOR TASK #3 HERE
        // Create a PrintWriter object using "Results.txt"
        File outputFile = new File("Results.txt");
        System.out.println("Writing results to: " + outputFile.getAbsolutePath());

        try (PrintWriter output = new PrintWriter(outputFile)) {
            // Print the results to the output file
            output.printf("mean = %.3f%n", mean);
            output.printf("standard deviation = %.3f%n", stdDev);
        } catch (IOException e) {
            System.out.println("Error: Unable to write to 'Results.txt'.");
            e.printStackTrace();
        }

        System.out.println("Results have been written to Results.txt.");
    }
}

