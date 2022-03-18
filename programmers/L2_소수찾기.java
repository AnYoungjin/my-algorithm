import java.util.*;

class Solution {
    char[] numbers;
    HashSet<Integer> hs = new HashSet<>();

    public int solution(String numbers) {
        int answer = 0;
        this.numbers = numbers.toCharArray();

        for (int i = 1; i <= numbers.length(); i++) {
            answer += makeNumber(i, "", new boolean[numbers.length()]);
        }

        return answer;
    }

    public int makeNumber(int length, String number, boolean[] visited) {
        int answer = 0;

        if (number.length() == length) {
            if (!isPrime(Integer.parseInt(number))) return 0;
            if (hs.add(Integer.parseInt(number))) return 1;
            return 0;
        }

        for (int i = 0; i < numbers.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            answer += makeNumber(length, number + numbers[i], visited);
            visited[i] = false;
        }

        return answer;
    }

    public boolean isPrime(int num) {
        if (num < 2) return false;

        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}