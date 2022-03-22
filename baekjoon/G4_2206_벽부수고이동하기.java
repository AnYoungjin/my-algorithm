import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 문제링크 https://www.acmicpc.net/problem/2206
 * 메모리 162060 kb
 * 시간 692 ms
 * 분류 그래프 이론, 그래프 탐색, 너비 우선 탐색
 * 고려사항
 */
public class G4_2206_벽부수고이동하기 {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        for (int n = 0; n < N; n++) {
            int m = 0;
            for (char c : br.readLine().toCharArray()) {
                map[n][m++] = c - '0';
            }
        }

        System.out.println(solution());
    }

    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static int solution() {
        // visited[n][m][0] : (n,m) 까지 벽 부수기 찬스를 사용 후 도착했는지
        // visited[n][m][1] : (n,m) 까지 벽을 부수지 않고 도착했는지
        boolean[][][] visited = new boolean[N][M][2];

        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(0, 0, 1, 1));
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Info info = q.poll();
            // (N,M) 위치에 도착했다면
            if (info.r == N - 1 && info.c == M - 1) {
                return info.dist;
            }

            for (int[] d : deltas) {
                int nr = info.r + d[0];
                int nc = info.c + d[1];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc][info.chance]) continue;

                if (map[nr][nc] == 0) {
                    // 이동하려는 칸이 빈 칸이라면 바로 이동
                    q.offer(new Info(nr, nc, info.chance, info.dist + 1));
                    visited[nr][nc][info.chance] = true;

                } else if (info.chance == 1) {
                    // 이동하려는 칸이 벽인데 부수기 찬스가 남아있다면 부수고 이동
                    q.offer(new Info(nr, nc, 0, info.dist + 1));
                    visited[nr][nc][0] = true;
                }
            }
        }

        return -1;
    }

    static class Info {
        int r, c;
        int chance, dist;

        public Info(int r, int c, int chance, int dist) {
            this.r = r;
            this.c = c;
            this.chance = chance;
            this.dist = dist;
        }
    }
}
