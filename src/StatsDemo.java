import java.util.Scanner;
// TASK #3 Add the file I/O import statement here
import java.io.*;

/*This class reads numbers from a file, calculates the
mean and standard deviation, and writes the results
to a file.
*/
public class StatsDemo {
// TASK #3 Add the throws clause
public static void main(String[] args) throws IOException{
double value = 0; // To hold a value from the file
double sum = 0; // The sum of the numbers
int count = 0;  // The number of numbers added
double mean = 0; // The average of the numbers
double stdDev = 0; // The standard deviation
double difference; // The value and mean difference
double totalSum=0;
// Create an object of type Scanner
Scanner keyboard = new Scanner (System.in);
String filename; // The user input file name

// Prompt the user and read in the file name
System.out.println("This program calculates " +
"statistics on a file " +
"containing a series of numbers");
System.out.println("Enter the file name: ");
filename = keyboard.nextLine();

// ADD LINES FOR TASK #4 HERE
// Create a Scanner object passing it a File object
// with the name of the file.
File inputFile= new File(filename);
Scanner inputFileScanner=  new Scanner (inputFile);

// Loop until you are at the end of the file
while(inputFileScanner.hasNextDouble()){
// Read a double value from the file
value=inputFileScanner.nextDouble();

// Add the value to sum
sum+=value;

// Increment the counter
count++;

}

// After the loop finishes, close the input file
inputFileScanner.close();

// Store the calculated mean in the mean variable
mean=sum/count;

/// ADD LINES FOR TASK #5 HERE
// Create a Scanner object passing it a File object
// with the name of the file.
Scanner inputFileScanner2=new Scanner(new File(filename));

// Reinitialize toatlSum to 0
totalSum=0;
// Reinitialize count to 0
count=0;
// Loop until you are at the end of the file
while(inputFileScanner2.hasNextDouble()){
// Read a double value from the file
value=inputFileScanner2.nextDouble();
// subtract the mean from the value
difference = value-mean;
// Add the square of the difference to the sum
totalSum += Math.pow(difference,2);
// Increment the counter
count++;
}

// After the loop finishes, close the input file
inputFileScanner2.close();

// Store the calculated standard deviation in stdDev
double variance=totalSum/count;

stdDev=Math.sqrt(variance);

// ADD LINES FOR TASK #3 HERE
// Create a PrintWriter object using "Results.txt"
PrintWriter outputFile = new PrintWriter("Results.txt");

// Print the results to the output file
outputFile.printf("mean = %.3f%n", mean);
outputFile.printf("standard deviation = %.3f%n", stdDev);

// Close the output file
outputFile.close();

System.out.println("Results have been written to Results.txt");
}

}