import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        // 1. 빈 문자열인 경우, 빈 문자열 반환
        if (p.equals("")) return "";
        
        return convert(p);
    }
    
    public String convert(String w) {
        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
        //   단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며,
        //   v는 빈 문자열이 될 수 있다.
        String u = "";
        String v = "";
        int lCnt = 0;
        int rCnt = 0;
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') lCnt++;
            else rCnt++;
            u += w.charAt(i);
            if (lCnt == rCnt) {
                v = w.substring(i + 1);
                break;
            }
        }
        
        if (chkBracket(u)) {
            // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행
            //   수행한 결과 문자열을 u에 이어 붙인 후 반환
            if (!v.equals("")) u += convert(v);
            return u;
            
        } else {
            // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행
            // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙인다.
            String answer = "(";
            
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙인다.
            if (!v.equals("")) answer += convert(v);
            
            // 4-3. ')'를 다시 붙인다.
            answer += ")";
            
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고,
            //   나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙인다.
            for (int i = 1; i < u.length() - 1; i++) {
                if (u.charAt(i) == '(') answer += ")";
                else answer += "(";
            }
            
            // 4-5. 생성된 문자열을 반환
            return answer;
        }
    }
    
    public boolean chkBracket(String u) {
        if (u.charAt(0) == ')') return false;
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') stack.push(u.charAt(i));
            else stack.pop();
        }
        return stack.isEmpty();
    }
}