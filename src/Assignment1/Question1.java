package Assignment1;

public class Question1 {
    public static void main(String[] args) {
        long testValues[] = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000};

        // looping over all values
        for (int i = 0; i < 8; i++) {

            // printing the value being calculated
            System.out.println(testValues[i]);

            // algone test
            long startTime = System.nanoTime();
            System.out.print("\talgone   : " + algone(testValues[i]));
            long endTime = System.nanoTime();
            System.out.println(" " + (endTime - startTime)/1000000 + "ms");

            // algtwo test
            startTime = System.nanoTime();
            System.out.print("\talgtwo   : " + algtwo(testValues[i]));
            endTime = System.nanoTime();
            System.out.println(" " + (endTime - startTime)/1000000 + "ms");

            // algthree test
            startTime = System.nanoTime();
            System.out.print("\talgthree : " + algthree(testValues[i]));
            endTime = System.nanoTime();
            System.out.println(" " + (endTime - startTime)/1000000 + "ms");
        }
    }

    // algorithm 1
    public static long algone(long n) {
        long sum = 0;
        for (long m = 1; m <= n; m++) {
            sum += m;
        }
        return sum;
    }

    // algorithm 2
    public static long algtwo(long n) {
        long sum = 0;
        for (long m = 1; m <= n; m++) {
            for (long k = 1; k <= m; k++) {
                sum++;
            }
        }
        return sum;
    }

    // algorithm 3
    public static long algthree(long n) {
        return (n*(n+1)/2);
    }
}
