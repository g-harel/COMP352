package Assignment1;

public class Question2 {
    public static void main(String[] args) {
        // creating an array of 1000 pseudo-random numbers between 1 and 21
        int[] nums = new int[100000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(Math.random()*20 + 1);
        }
        // calculating execution time
        long startTime = System.nanoTime();
        System.out.print("multiply : " + multiply(nums, 999));
        long endTime = System.nanoTime();
        System.out.println(" " + (endTime - startTime)/1000000 + "ms");
    }

    public static double multiply(int[] A, int n) {
        if (n < 4) {
            return 1;
        } else {
            double temp = A[n-1] * multiply(A, n-3);
            System.out.println(temp);
            return temp;
        }
    }
}
