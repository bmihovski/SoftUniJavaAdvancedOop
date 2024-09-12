# SoftUniJavaAdvancedOop

## Running Tests

To run the tests for this project, you can use the `run_tests.sh` script. This script will build the project using Maven, split the test input and output files, and then run the tests.

### Preparing Test Files

1. **Test Input File**: Create a file named `Test.in.txt` in the `tests` directory. This file should contain the input data for your tests, with each test case separated by a line containing `===`.

   Example:

   ```
   input for test 1
   ===
   input for test 2
   ===
   input for test 3
   ```

2. **Test Output File**: Create a file named `Test.out.txt` in the `tests` directory. This file should contain the expected output data for your tests, with each test case separated by a line containing `===`.

   Example:

   ```
   expected output for test 1
   ===
   expected output for test 2
   ===
   expected output for test 3
   ```

### Running the Script

1. Open a terminal and navigate to the project directory.
2. Run the script using the following command:
   ```sh
   ./run_tests.sh
   ```

### Interpreting the Results

- The script will build the project using Maven. If the build fails, it will print "Maven build failed!" and exit.
- The script will split the test input and output files into separate files for each test case.
- For each test case, the script will run the program and compare the actual output with the expected output.
- If the output matches, it will print "Test X passed!".
- If the output does not match, it will print "Test X failed!", show the input that was used, and display the differences between the actual and expected output.

### Cleaning Up

The script will automatically clean up the temporary files it creates during the test run.

