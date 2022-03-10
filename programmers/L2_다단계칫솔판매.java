import java.util.*;

class Solution {
    ArrayList<Node> list = new ArrayList<>();
    HashMap<String, Integer> hm = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < enroll.length; i++) {
            // enroll[i]의 부모 = referral[i]
            list.add(new Node(i, enroll[i], referral[i]));
            hm.put(enroll[i], i);
        }
        
        int[] answer = new int[enroll.length];
        for (int i = 0; i < seller.length; i++) {
            int profit = amount[i] * 100;
            Node me = list.get(hm.get(seller[i]));
            while (true) {
                int p = (int) Math.floor(profit * 0.1);
                // 10%를 계산한 금액이 1원 미만인 경우 자신이 모두 가진다.
                if (p < 1) {
                    answer[me.idx] += profit;
                    break;
                }
                
                // 발생하는 이익의 10%는 추천인에게 배분하고 나머지를 가진다.
                answer[me.idx] += profit - p;
                profit = p;
                if (me.parent.equals("-")) break;
                me = list.get(hm.get(me.parent));
            }
        }
        
        return answer;
    }
    
    class Node {
        private int idx;
        private String name;
        private String parent;
        
        public Node(int idx, String name, String parent) {
            this.idx = idx;
            this.name = name;
            this.parent = parent;
        }
    }
}