import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 문제링크 https://www.acmicpc.net/problem/13549
 * 메모리 17452 kb
 * 시간 112 ms
 * 분류
 * 고려사항
 */
public class G5_13549_숨바꼭질3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        System.out.println(solution(N, K));
    }

    public static int solution(int N, int K) {
        Queue<Subin> q = new LinkedList<>();
        q.offer(new Subin(N, 0));

        int[] minTime = new int[100001];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[N] = 0;

        while (!q.isEmpty()) {
            Subin cur = q.poll();

            if (cur.idx == K) break;

            int nextIdx = cur.idx << 1;
            while (nextIdx < 100001) {
                if (minTime[nextIdx] != Integer.MAX_VALUE) break;

                minTime[nextIdx] = cur.time;
                q.offer(new Subin(nextIdx, cur.time));
                nextIdx <<= 1;
            }

            nextIdx = cur.idx - 1;
            if (nextIdx >= 0 && minTime[nextIdx] == Integer.MAX_VALUE) {
                minTime[nextIdx] = cur.time + 1;
                q.offer(new Subin(nextIdx, cur.time + 1));
            }

            nextIdx = cur.idx + 1;
            if (nextIdx < 100001 && minTime[nextIdx] == Integer.MAX_VALUE) {
                minTime[nextIdx] = cur.time + 1;
                q.offer(new Subin(nextIdx, cur.time + 1));
            }
        }

        return minTime[K];
    }

    static class Subin {
        int idx;
        int time;

        public Subin(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
