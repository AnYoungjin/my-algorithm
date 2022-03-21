import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            String name = clothes[i][0];
            String type = clothes[i][1];
            hm.put(type, hm.getOrDefault(type, 0) + 1);
        }

        // 의상의 종류가 하나라면 조합할 수 없다.
        int answer = 1;
        for (String type : hm.keySet()) {
            // 각각의 종류의 의상 수를 곱하면 조합 가능한 수가 나옴
            // 얼굴 * 상의 * 하의 * 겉옷
            // + 1 : 안 입은 경우
            answer *= hm.get(type) + 1;
        }
        // 하나도 안 입은 경우 제외
        return answer - 1;
    }
}