import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Document> q = new LinkedList<>();
        
        int idx = -1;
        for (int priority : priorities) {
            q.offer(new Document(++idx, priority));
        }
        
        int[] arr = new int[priorities.length];
        int turn = 0;
        while(!q.isEmpty()) {
            Document curDoc = q.poll();
            boolean flag = false;
            for (Document doc : q) {
                if (doc.priority > curDoc.priority) {
                    q.offer(curDoc);
                    flag = true;
                    break;
                }
            }
            if (!flag) arr[curDoc.idx] = ++turn;
        }
        
        return arr[location];
    }
    
    class Document {
        int idx;
        int priority;
        
        public Document(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }
}