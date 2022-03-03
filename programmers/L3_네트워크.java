class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] network = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            // 연결 확인된 컴퓨터는 패스
            if (network[i]) continue;
            
            // 컴퓨터 i와 연결된 컴퓨터 확인
            checkNetwork(i, computers, network);
            // 네트워크 수++
            answer++;
        }
        
        return answer;
    }
    
    public void checkNetwork(int cur, int[][] computers, boolean[] network) {
        network[cur] = true;
        
        for (int i = 0; i < network.length; i++) {
            // 연결 확인된 컴퓨터는 다시 확인할 필요가 없다.
            if (network[i]) continue;
            // 연결되어 있지 않은 컴퓨터는 정보를 교환할 수 없다.
            if (computers[cur][i] == 0) continue;
            
            network[i] = true;
            // 해당 컴퓨터와 연결되어 있는 컴퓨터를 찾으러 간다.
            checkNetwork(i, computers, network);
        }
    }
}