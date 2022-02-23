import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] arr = new int[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                arr[r][c] = r * columns + c + 1;
            }
        }
        
        int idx = 0;
        for (int[] query : queries) {
            int x1 = query[0] - 1;
            int y1 = query[1] - 1;
            int x2 = query[2] - 1;
            int y2 = query[3] - 1;
            
            // 테두리를 시계방향으로 회전
            // 이동한 숫자 중 최솟값 구하기
            int temp = arr[x1][y1];
            int minNum = temp;
            
            // y1열 위로 옮기기
            for (int x = x1; x < x2; x++) {
                arr[x][y1] = arr[x + 1][y1];
                minNum = Math.min(minNum, arr[x][y1]);
            }
            
            // x2행 왼쪽으로 옮기기
            for (int y = y1; y < y2; y++) {
                arr[x2][y] = arr[x2][y + 1];
                minNum = Math.min(minNum, arr[x2][y]);
            }
            
            // y2열 아래로 옮기기
            for (int x = x2; x > x1; x--) {
                arr[x][y2] = arr[x - 1][y2];
                minNum = Math.min(minNum, arr[x][y2]);
            }
            
            // x1행 오른쪽으로 옮기기
            for (int y = y2; y > y1; y--) {
                arr[x1][y] = arr[x1][y - 1];
                minNum = Math.min(minNum, arr[x1][y]);
            }
            arr[x1][y1 + 1] = temp;
            answer[idx++] = minNum;
        }
        
        return answer;
    }
}