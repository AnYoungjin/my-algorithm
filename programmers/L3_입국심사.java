class Solution {
    public long solution(int n, int[] times) {
        long start = 0;
        long end = (long) 1000000000 * (long) n;
        long answer = end;
        
        while (start <= end) {
            long mid = (start + end) / 2;
            
            long a = 0;
            for (int i = 0; i < times.length; i++) {
                a += mid / times[i];
            }
            
            if (a >= n) {
                answer = Math.min(answer, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return answer;
    }
}