import java.util.*;

class Solution {
    String[] operators = {"+", "-", "*"};
    String expression;
    
    public long solution(String expression) {
        this.expression = expression;
        int length = operators.length;
        
        long answer = setPriority(0, new int[length], new boolean[length]);
        
        return answer;
    }
    
    // 우선순위 정하기 (순열)
    public long setPriority(int cnt, int[] priority, boolean[] visited) {
        long answer = 0;
        
        // 우선순위가 정해졌다면 |수식의 결괏값|을 구한다.
        if (cnt == priority.length) {
            return operate(priority);
        }
        
        for (int i = 0; i < priority.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            priority[cnt] = i;
            answer = Math.max(answer, setPriority(cnt + 1, priority, visited));
            visited[i] = false;
        }
        
        return answer;
    }
    
    public long operate(int[] priority) {
        long result = 0;
        
        String[] numSplit = expression.replace("+", " ").replace("-", " ").replace("*", " ").split(" ");
        List<Long> nums = new ArrayList<>();
        for (int i = 0; i < numSplit.length; i++) {
            nums.add(Long.parseLong(numSplit[i]));
        }
        List<String> ops = new ArrayList<>();
        for (char c : expression.toCharArray()) {
            if ("+-*".contains(c + "")) ops.add(c + "");
        }
        
        // 정해진 우선순위 대로 연산 실행
        for (int p : priority) {
            // 수행해야 하는 연산자
            String op = operators[p];
            // 수식에 포함되지 않은 연산자는 패스
            if (!expression.contains(op)) continue;
            
            Stack<Long> nStack = new Stack<>();
            Stack<String> oStack = new Stack<>();
            nStack.push(nums.get(0));
            
            for (int i = 0; i < ops.size(); i++) {
                oStack.push(ops.get(i));
                nStack.push(nums.get(i + 1));
                
                if (oStack.peek().equals(op)) {
                    long b = nStack.pop();
                    long a = nStack.pop();
                    nStack.push(calc(a, b, oStack.pop()));
                }
            }
            
            nums = nStack;
            ops = oStack;
        }
        
        return Math.abs(nums.get(0));
    }
    
    public long calc(long a, long b, String op) {
        if (op.equals("+")) return a + b;
        if (op.equals("-")) return a - b;
        return a * b;
    }
}