/*
   문제 유형: 동적 계획법(DP)으로 인접한 집을 동시에 선택하지 않으면서 훔칠 수 있는 최대 금액 계산
   문제9 : <LeetCode> - 198. House Robber
   난이도: Medium
*/

/*
   전략

   1. nums 배열에 저장된 각 집의 금액을 이용해 훔칠 수 있는 최대 금액을 구한다.
   -> nums[i] = i번 인덱스 집에 있는 돈
   -> 서로 인접한 두 집은 동시에 선택 불가
   2. 동적 계획법(DP)을 사용해 이전에 계산한 최대 금액을 재사용한다.
   -> DP = 작은 문제의 결과를 이용해 큰 문제의 결과 계산
   3. 각 집에서는 현재 집을 털거나 털지 않는 두 가지 선택이 가능하다.
   -> 현재 집을 털지 않는 경우
   -> 현재 집을 터는 경우
   4. 현재 집을 털지 않으면 이전 집까지의 최대 금액을 그대로 사용한다.
   -> skip = dp[i-1]
   5. 현재 집을 털면 바로 이전 집은 선택할 수 없다.
   -> 현재 집의 금액 + 두 집 전까지의 최대 금액
   -> take = dp[i-2] + nums[i]
   6. 두 선택 중 더 큰 금액을 현재 집까지의 최댓값으로 저장한다.
   -> dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i])
   7. 전체 DP 배열 대신 이전 두 결과만 저장한다.
   -> twoBefore = 두 집 전까지의 최대 금액
   -> oneBefore = 바로 이전 집까지의 최대 금액
   8. 처음에는 아직 어떤 집도 확인하지 않았으므로 두 변수를 0으로 초기화한다.
   9. 첫 번째 집부터 마지막 집까지 순서대로 확인한다.
   10. 현재 집을 털지 않는 경우의 최대 금액을 계산한다.
   -> skip = oneBefore
   11. 현재 집을 터는 경우의 최대 금액을 계산한다.
   -> take = twoBefore + nums[i]
   12. Math.max()로 두 선택 중 더 큰 금액을 선택한다.
   -> current = Math.max(skip, take)
   13. 다음 집을 확인하기 위해 이전 두 결과를 갱신한다.
   -> twoBefore = oneBefore
   -> oneBefore = current
   14. 마지막 집까지 확인한 뒤 훔칠 수 있는 최대 금액을 반환한다.
*/

class Solution {
    public int rob(int[] nums) {
        // 두 집 전까지 훔칠 수 있는 최대 금액
        int twoBefore = 0;
        // 바로 이전 집까지 훔칠 수 있는 최대 금액
        int oneBefore = 0;
        // 첫 번째 집부터 마지막 집까지 확인
        for (int i = 0; i < nums.length; i++) {
            // 현재 집을 털지 않는 경우의 최대 금액
            int skip = oneBefore;
            // 현재 집을 터는 경우의 최대 금액
            int take = twoBefore + nums[i];
            // 두 경우 중 더 큰 금액 선택
            int current = Math.max(skip, take);
            // 다음 집 계산을 위해 이전 두 결과 갱신
            twoBefore = oneBefore;
            oneBefore = current;
        }
        // 훔칠 수 있는 전체 최대 금액 반환
        return oneBefore;
    }
}