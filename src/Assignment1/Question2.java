package Assignment1;

import java.io.*;

public class Question2 {
    public static void main(String[] args) {
        try {
            // creating an array of 1000 pseudo-random numbers between 0 and 20
            int[] nums = new int[100000];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (int) (Math.random() * 20);
            }
            // calculating execution time
            long startTime = System.nanoTime();
            Double result = multiply(nums, 99);
            long time = (System.nanoTime() - startTime)/1000;
            // outputting the results to the screen
            String output = "result = " + result + " in " + time + " μs\n";
            System.out.print(output);
            // outputting the results to the file
            PrintWriter out = new PrintWriter(new FileWriter("out.txt", true), true);
            out.write(output);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // multiply method
    public static double multiply(int[] A, int n) {
        if (n < 4) {
            return 0;
        } else {
            double temp = A[n - 1] * multiply(A, n - 3);
            return temp;
        }
    }

    // tail-recursive multiply method
    public static double multiply2(int[] A, int n, int result) {
        if (n < 4) {
            return 0;
        } else {
            return multiply2(A, n - 3, A[n - 1] * result);
        }
    }
}