class Solution {
    public long solution(int w, int h) {
        long total = (long) w * (long) h;
        
        int gcd = getGCD(Math.max(w, h), Math.min(w, h));
        
        return total - (w + h - gcd);
    }
    
    public int getGCD(int a, int b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}