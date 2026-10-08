import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        int l = numbers.length;
        // 계산 편리를 위한 문자열 배열 선언 
        String[] arr = new String[l];
        for (int i = 0 ; i < l ; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        
        // 배열 정렬 
        // a+b 와 b+a 를 비교하여 내림차순 정렬 
        // a = 1 b = 2 
        // 12 와 21 비교 
        
        Arrays.sort(arr, (a,b) -> {
            int ab = Integer.valueOf(a + b) ;
            int ba = Integer.valueOf(b + a) ;
            
            return ba - ab;
        });
        
        // 모든 숫자가 0이면 
        // 0000 방지 
        if (arr[0].equals("0")) {
            return arr[0];
        }
        
        // 문자열 배열 -> 문자열 변환 후 리턴
        return String.join("", arr);
        
    }
}