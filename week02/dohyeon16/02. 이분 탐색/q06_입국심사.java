/*
   문제 유형: 이분 탐색으로 모든 사람이 심사를 받을 수 있는 최소 시간 찾기
   문제6 : <프로그래머스> - 입국심사
   난이도: Level 3
*/

/*
   전략

   1. 정답이 될 수 있는 "시간"을 기준으로 이분 탐색한다.
   2. 최소 시간 left는 1분으로 설정한다.
   3. 최대 시간 right는 가장 오래 걸리는 심사관이 n명을 모두 심사하는 시간으로 설정한다.
   -> 가장 느린 심사 시간 × 사람 수 n
   4. left와 right의 가운데 시간 mid를 구한다.
   5. mid분 동안 각 심사관이 몇 명을 심사할 수 있는지 계산한다.
   -> mid / 심사관의 심사 시간
   6. 모든 심사관이 처리할 수 있는 사람 수를 합한다.
   7. 처리 가능한 사람이 n명 이상이면 mid분 안에 모든 사람을 심사할 수 있다는 뜻이다.
   -> 더 짧은 시간도 가능한지 확인하기 위해 right를 mid - 1로 줄인다.
   -> 현재 mid를 정답 후보로 저장한다.
   8. 처리 가능한 사람이 n명보다 적으면 시간이 부족한 것이다.
   -> 더 긴 시간이 필요하므로 left를 mid + 1로 늘린다.
   9. left가 right보다 커질 때까지 탐색 범위를 계속 절반씩 줄인다.
   10. 조건을 만족한 가장 작은 시간을 반환한다.
*/

class Solution {
    public long solution(int n, int[] times) {
        // 정답이 될 수 있는 최소 시간
        long left = 1;
        // 가장 오래 걸리는 심사 시간 찾기
        long maxTime = 0;
        for (int time : times) {
            if (time > maxTime) {
                maxTime = time;
            }
        }
        // 가장 느린 심사관이 모든 사람을 혼자 심사하는 경우
        long right = maxTime * n;
        // 최소 시간을 저장할 변수
        long answer = right;
        while (left <= right) {
            // 현재 확인할 가운데 시간
            long mid = left + (right - left) / 2;
            // mid분 동안 심사할 수 있는 전체 사람 수
            long people = 0;
            for (int time : times) {
                people += mid / time;
                // 이미 n명 이상이면 더 계산할 필요 없음
                if (people >= n) {
                    break;
                }
            }
            // mid분 안에 모든 사람을 심사할 수 있는 경우
            if (people >= n) {
                answer = mid;
                // 더 짧은 시간이 가능한지 왼쪽 범위 탐색
                right = mid - 1;
            } else {
                // 시간이 부족하므로 오른쪽 범위 탐색
                left = mid + 1;
            }
        }
        return answer;
    }
}