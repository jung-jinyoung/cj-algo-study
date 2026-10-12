/*
   문제 유형: 투 포인터로 연속 구간의 합을 조절해 k가 되는 가장 짧은 구간 찾기
   문제8 : <프로그래머스> - 연속된 부분 수열의 합
   난이도: Level 2
*/

/*
   전략

   1. 연속된 부분 수열의 시작 위치를 나타내는 left를 0으로 시작한다.
   2. right를 배열의 처음부터 끝까지 한 칸씩 이동한다.
   3. right가 가리키는 값을 sum에 더해 현재 구간의 합을 구한다.
   4. 현재 구간의 합 sum이 k보다 크면 구간의 왼쪽 값을 빼면서 left를 오른쪽으로 이동한다.
   -> 구간의 합을 다시 작게 만드는 과정이다.
   5. sum이 k와 같아지면 현재 left부터 right까지가 조건을 만족하는 부분 수열이다.
   6. 현재 부분 수열의 길이를 구한다.
   7. 이전에 찾은 부분 수열보다 길이가 짧다면 시작 인덱스와 마지막 인덱스를 저장한다.
   8. 길이가 같은 경우에는 먼저 발견한 구간을 유지한다.
   -> right를 왼쪽부터 확인하므로 먼저 발견한 구간의 시작 인덱스가 더 작다.
   9. right가 배열의 끝까지 이동할 때까지 반복한다.
   10. 저장한 시작 인덱스와 마지막 인덱스를 배열로 반환한다.
*/

class Solution {
    public int[] solution(int[] sequence, int k) {
        // 현재 구간의 시작 위치
        int left = 0;
        // 현재 구간의 합
        int sum = 0;
        // 찾은 구간의 시작과 마지막 위치
        int bestStart = 0;
        int bestEnd = sequence.length - 1;
        // 현재까지 찾은 가장 짧은 길이
        int bestLength = sequence.length + 1;
        // right를 오른쪽으로 한 칸씩 이동
        for (int right = 0; right < sequence.length; right++) {
            // 현재 값을 구간 합에 추가
            sum += sequence[right];
            // 합이 k보다 크면 왼쪽 값을 빼면서 구간 축소
            while (sum > k && left <= right) {
                sum -= sequence[left];
                left++;
            }
            // 현재 구간의 합이 k인 경우
            if (sum == k) {
                int currentLength = right - left + 1;
                // 기존 구간보다 더 짧은 경우 정답 갱신
                if (currentLength < bestLength) {
                    bestLength = currentLength;
                    bestStart = left;
                    bestEnd = right;
                }
            }
        }
        return new int[]{bestStart, bestEnd};
    }
}