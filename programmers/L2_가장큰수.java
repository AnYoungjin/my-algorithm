import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = numbers[i] + "";
        }
        
        Arrays.sort(nums, (String o1, String o2) -> {
            return (o2 + o1).compareTo(o1 + o2);
        });
        
        String answer = "";
        
        for (String num : nums) {
            answer += num;
        }
        
        if (answer.charAt(0) == '0') answer = "0";
        return answer;
    }
}