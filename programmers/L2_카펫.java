class Solution {
    public int[] solution(int brown, int yellow) {
        // r + c = brown / 2 + 2
        // r * c = brown + yellow

        int[] answer = new int[2];

        for (int i = 1; i < brown / 2; i++) {
            int side1 = i;
            int side2 = brown / 2 + 2 - side1;

            if (side1 * side2 == brown + yellow) {
                answer[0] = Math.max(side1, side2);
                answer[1] = Math.min(side1, side2);
                break;
            }
        }

        return answer;
    }
}