/*
 풀이 전략
 - dp[i]를 앞의 i개 집을 고려했을 때 얻을 수 있는 최대 금액으로 정의한다.
   마지막으로 고려한 집을 반드시 선택한다는 의미는 아니다.
 - 현재 집을 건너뛰면 앞의 i-1개 집에서 구한 최댓값을 유지한다.
 - 현재 집을 선택하면 바로 앞집은 선택할 수 없으므로,
   앞의 i-2개 집의 최댓값에 현재 집의 금액 nums[i-1]을 더한다.
 - 따라서 dp[i] = max(dp[i-1], dp[i-2] + nums[i-1])로 계산한다.
 - 초기값은 dp[0] = 0, dp[1] = nums[0]으로 설정한다.
 - 모든 집을 고려한 결과인 dp[nums.length]를 반환한다.

 * 시간복잡도: O(N)
 * - N은 집의 수이다.
 * - 각 집마다 선택하는 경우와 건너뛰는 경우를 O(1)에 비교한다.
 */
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int [] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = nums[0];

        for(int i = 2;i<=n;i++){
            dp[i] = Math.max(
                dp[i-1],
                dp[i-2] + nums[i-1]
            );
        }
        return dp[n];
    }
}