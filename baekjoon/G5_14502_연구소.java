import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @문제링크 https://www.acmicpc.net/problem/14502
 * @메모리 138640 kb
 * @시간 344 ms
 * @분류 그래프 이론, 브루트포스 알고리즘, 그래프 탐색, 너비 우선 탐색
 * @고려사항
 */
public class G5_14502_연구소 {
    static int N, M, maxSafeArea, answer;
    static int[][] map;
    static ArrayList<Point> viruses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]); // 세로 크기
        M = Integer.parseInt(input[1]); // 가로 크기

        // -3 : 추가적으로 세울 벽
        maxSafeArea = N * M - 3;
        answer = 0;
        map = new int[N][M];
        viruses = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            input = br.readLine().split(" ");
            // 0 : 빈 칸, 1 : 벽, 2 : 바이러스
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(input[m]);
                // 벽은 안전 영역에 포함될 수 없다.
                if (map[n][m] == 1) maxSafeArea--;
                if (map[n][m] == 2) viruses.add(new Point(n, m));
            }
        }
        build(3, new int[3], 0);
        System.out.println(answer);
    }

    // 벽을 세울 칸 선택
    public static void build(int toSelect, int[] selected, int startIdx) {
        if (toSelect == 0) {
            int[][] tempMap = getTempArr();

            for (int i = 0; i < 3; i++) {
                int r = selected[i] / M;
                int c = selected[i] % M;
                // 정해진 칸에 벽 세우기
                tempMap[r][c] = 1;
            }

            answer = Math.max(answer, getSafeArea(tempMap));
            return;
        }

        for (int i = startIdx; i < N * M; i++) {
            // 벽은 빈 칸에 세울 수 있다.
            if (map[i / M][i % M] != 0) continue;
            selected[selected.length - toSelect] = i;
            build(toSelect - 1, selected, i + 1);
        }
    }

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int getSafeArea(int[][] tempMap) {
        int safeArea = maxSafeArea;

        Queue<Point> q = new LinkedList<>();
        q.addAll(viruses);
        boolean[][] visited = new boolean[N][M];

        while(!q.isEmpty()) {
            Point p = q.poll();
            safeArea--;

            // 바이러스는 상하좌우 인접한 빈 칸으로 퍼져나갈 수 있다.
            for (int d = 0; d < deltas.length; d++) {
                int nr = p.x + deltas[d][0];
                int nc = p.y + deltas[d][1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (tempMap[nr][nc] != 0) continue;

                visited[nr][nc] = true;
                q.offer(new Point(nr, nc));
                tempMap[nr][nc] = 2;
            }
        }

        return safeArea;
    }

    public static int[][] getTempArr() {
        int[][] tempArr = new int[N][M];
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                tempArr[n][m] = map[n][m];
            }
        }
        return tempArr;
    }
}


/*

바이러스 확산을 막기 위해 연구소에 벽을 세우려고 한다
연구소의 크기는 N x M
빈 칸 : 0
벽 : 1
바이러스 : 2

바이러스가 퍼질 수 없는 곳 = 안전 영역
안전 영역 크기의 최댓값을 구하자


벽을 세울 3개의 칸을 조합으로 먼저 구한 후,
BFS로 바이러스를 퍼뜨려보고, 안전 영역의 크기를 구한다.
조합마다의 안전 영역을 구해서 최댓값을 답으로 출력.

 */