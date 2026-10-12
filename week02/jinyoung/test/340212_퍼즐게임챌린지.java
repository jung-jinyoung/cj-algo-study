import java.util.Arrays;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        // 최소 시간 범위 확인용 
        // level 범위 : 난이도 1 부터 시작 ~ 최악의 난이도 경우 
        int left = 1 ;
        int right = 100000;
        // l - i ~ l - n - 1 까지의 시간이 limit 안에 있어야 함 
        while (left < right) {
            long cost = 0 ;
            int mid = left + (right - left) / 2 ;
            for (int i = 0 ; i < diffs.length ; i++) {
                if (diffs[i] <= mid) {
                    cost += times[i];
                } else { 
                    // 난이도가 더 클 경우 더 추가 비용
                    // 현재 시간만큼 문제를 푸는데 사용
                    // 이전 퍼즐을 다시 풀고 와야 함 
                    int fail = (diffs[i] - mid);  // 시도 횟수
                    
                    int before ;
                    if (i == 0) {
                        before = 0 ;
                    } else {
                        before = times[i-1];
                    }
                   cost += (before + times[i]) * fail + times[i] ;
                }
                // 해당 난이도로 제한 시간 안에 풀 수 없을 경우 
                if (cost > limit) {
                    break; 
                }
            }
            
            // 제한 시간 이내에 해결 가능한 경우
            if (cost <= limit) {
                right = mid;

            } else {
                // 숙련도를 높여야 하는 경우
                left = mid + 1;
            }
        }
        
        
        return left;
    }
}