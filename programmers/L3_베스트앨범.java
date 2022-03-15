import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 재생 횟수
        HashMap<String, Integer> genrePlayMap = new HashMap<>();

        // 장르별 노래
        HashMap<String, PriorityQueue<Music>> hm = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            if (hm.get(genres[i]) == null) {
                hm.put(genres[i], new PriorityQueue<>());
            }

            PriorityQueue<Music> pq = hm.get(genres[i]);
            pq.add(new Music(i, plays[i]));
            hm.put(genres[i], pq);

            // 기존 장르 재생 횟수(없다면 0)에 이 노래 재생 횟수 추가
            genrePlayMap.put(genres[i], genrePlayMap.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 장르의 재생 횟수 내림차순으로 정렬
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(genrePlayMap.entrySet());
        Collections.sort(entries, (e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()) * -1);

        ArrayList<Integer> answerList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : entries) {
            int cnt = 0;
            // 해당 장르의 노래들 (우선순위를 고려하여 정렬되어있음)
            PriorityQueue<Music> pq = hm.get(entry.getKey());
            while (!pq.isEmpty()) {
                // 장르당 최대 두 곡 수록
                if (++cnt > 2) break;
                // 해당 장르에서 우선순위가 높은 곡부터 수록
                answerList.add(pq.poll().idx);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    class Music implements Comparable {
        int idx;
        int plays;

        public Music(int idx, int plays) {
            this.idx = idx;
            this.plays = plays;
        }

        public int compareTo(Object o) {
            Music m = (Music) o;
            if (this.plays == m.plays) return Integer.compare(this.idx, m.idx);
            return Integer.compare(this.plays, m.plays) * -1;
        }
    }
}