import java.util.*;

class Solution {
    int length;
    ArrayList<String> selected;
    String[][] relation;
    
    public int solution(String[][] relation) {
        length = relation[0].length;
        selected = new ArrayList<>();
        this.relation = relation;
        
        int answer = 0;
        for (int i = 1; i <= length; i++) {
            // i개의 속성으로 이루어진 후보키의 개수
            answer += getCandidateKey(i, "", 0);
        }
        
        return answer;
    }
    
    public int getCandidateKey(int toSelect, String key, int startIdx) {
        // 후보 키에 이미 사용된 속성이 포함되어 있다면 최소성을 만족하지 않는다.
        for (String k : selected) {
            int cnt = 0;
            for (char c : k.toCharArray()) {
                if (key.contains(c + "")) cnt++;
            }
            if (cnt == k.length()) return 0;
        }
        
        int answer = 0;
        
        if (key.length() == toSelect) {
            // 해당 조합이 유일성을 만족하지 못한다면
            if (!satisfy(key)) return 0;
            
            // 최소성을 만족하기 위해 이미 후보키에 쓰인 속성 저장
            selected.add(key);
            return 1;
        }
        
        for (int i = startIdx; i < length; i++) {
            answer += getCandidateKey(toSelect, key + i, i + 1);
        }
        
        return answer;
    }
    
    public boolean satisfy(String key) {
        HashSet<String> hs = new HashSet<>();
        
        for (String[] tuple : relation) {
            String candidateKey = "";
            for (int i = 0; i < key.length(); i++) {
                candidateKey += tuple[key.charAt(i) - '0'];
            }
            
            // 유일성을 만족하지 못한다면 후보 키가 될 수 없다.
            if (!hs.add(candidateKey)) return false;
        }
        
        return true;
    }
}