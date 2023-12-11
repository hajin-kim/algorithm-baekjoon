import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        List<Integer> result = solve(n);

        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining("\n")));
    }

    private static List<Integer> solve(int n) {
        LinkedList<Integer> queue = new LinkedList<>(ONE_DIGIT_PRIME_NUMBERS);

        for (int digits = 0; digits < n - 1; digits++) {
            int size = queue.size();
            for (int poll = 0; poll < size; poll++) {
                int now = queue.poll();
                for (int digit = 1; digit <= 9; digit++) {
                    int newNumber = now * 10 + digit;

                    if (isPrime(newNumber)) {
                        queue.add(newNumber);
                    }
                }
            }
        }

        return queue;
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static final List<Integer> ONE_DIGIT_PRIME_NUMBERS = List.of(2, 3, 5, 7);
}