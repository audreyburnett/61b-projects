/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** Returns the nextNumber in a Collatz sequence. */
    public static int nextNumber(int n) {
        /** returns the next number in the Collatz sequence. if n is even, returns n/2. if n is odd, the next number is 3n + 1.*/
        if (n % 2 == 0){
            return n/2;
        } else{
            return (3*n) + 1;

        }
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");

        // Some starter code to test
        while (n != 1) {          
            n = nextNumber(n);          
            System.out.print(n + " ");
        }
        System.out.println();

    }
}

