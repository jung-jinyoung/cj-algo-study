/*
 풀이 전략
 - dp[i]를 i번 위치에 도착하기까지 지불한 최소 비용으로 정의한다.
   i번 계단의 비용은 아직 지불하지 않은 상태이다.
 - 0번과 1번에서 시작할 수 있으므로 dp[0]과 dp[1]은 0이다.
 - i번 위치에는 i-1 또는 i-2에서 이동할 수 있다.
 - 이동 전 계단의 비용을 지불해야 하므로 다음 두 값을 비교한다.
   dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]
 - 두 값 중 작은 값을 dp[i]에 저장하며 바텀업으로 계산한다.
 - 정상은 마지막 계단 다음 위치이므로 dp[cost.length]를 반환한다.

 시간복잡도: O(N)
 - N은 cost의 길이이다.
 - 2번부터 N번 위치까지 각 상태를 한 번씩 계산한다.
 */
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = 0;

        for(int i = 2;i<=n;i++){
            dp[i] = Math.min(
                dp[i-1] + cost [i-1],
                dp[i-2] + cost[i-2]
            );
        }
        return dp[n];
    }
}