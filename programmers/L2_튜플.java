class Solution {
    public int[] solution(String s) {
        s = s.replace("{", " ").replace("}", " ").trim();
        String[] arr = s.split(" , ");
        
        // 문자열 길이 오름차순 -> 집합의 원소 개수 오름차순
        Arrays.sort(arr, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        
        HashSet<Integer> hs = new HashSet<>();
        int[] answer = new int[arr.length];
        int idx = 0;
        for (String str : arr) {
            for (String num : str.split(",")) {
                int number = Integer.parseInt(num);
                if (hs.add(number)) answer[idx++] = number;
            }
        }
        
        return answer;
    }
}