import java.util.*;

class Solution {
    int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    char[][] arr;
    int N = 5;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[N];
        int idx = 0;
        
        for (String[] place : places) {
            arr = new char[N][N];
            for (int i = 0; i < N; i++) {
                arr[i] = place[i].toCharArray();
            }
            answer[idx++] = s();
        }
        
        return answer;
    }
    
    public int s() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (arr[r][c] == 'X') continue;

                int pCnt = 0;
                for (int d = 0; d < deltas.length; d++) {
                    int nr = r + deltas[d][0];
                    int nc = c + deltas[d][1];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if (arr[nr][nc] == 'P') pCnt++;
                }
                
                if (arr[r][c] == 'P' && pCnt > 0) return 0;
                if (arr[r][c] == 'O' && pCnt > 1) return 0;
            }
        }
        return 1;
    }
}