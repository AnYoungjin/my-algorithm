class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long start = 0;
        long end = (long) (Math.pow(10, 9) * 2 * Math.pow(10, 5) * 2);
        long answer = end;
        
        while (start <= end) {
            // mid의 시간이 주어졌을 때 광물을 운반할 수 있는지 
            long mid = (start + end) / 2;
            
            long gold = 0;
            long silver = 0;
            long total = 0;
            
            for (int i = 0; i < g.length; i++) {
                // 가능한 이동 횟수
                long moveCnt = mid / (t[i] * 2);            // 왕복
                if (mid % (t[i] * 2) >= t[i]) moveCnt++;    // 편도
                
                gold += Math.min(g[i], w[i] * moveCnt);
                silver += Math.min(s[i], w[i] * moveCnt);
                total += Math.min(g[i] + s[i], w[i] * moveCnt);
            }
            
            if (gold >= a && silver >= b && total >= a + b) {
                answer = Math.min(answer, mid);
                end = mid - 1;
                
            } else {
                start = mid + 1;
                
            }
        }
        
        return answer;
    }
}