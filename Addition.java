public class Addition {
    public static int add(int a, int b) {
        // Correctly perform addition instead of division
        return a + b;
    }

    public static void main(String[] args) {
        int a = 4;
        int b = 16; // Adjusted to match the expected result of 20

        System.out.println(add(a, b)); // 20
    }
}