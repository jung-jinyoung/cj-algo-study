class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2 ; i <= n ; i++) {
            // i-1번째에서 올라올 때의 코스트와 
            // i-2번째에서 올라올 때의 코스트 중 최솟값이 최적값
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }

        return dp[n];
    }
}