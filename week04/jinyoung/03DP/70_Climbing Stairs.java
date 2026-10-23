class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        // i번 째에서는 2칸 전에서 오를 수 있는(i-2) 경우의 수와
        // 1칸 번에서 오를 수 있는[i-1] 경우의 수의 합
        for (int i = 2 ; i <= n ; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        return dp[n];
    }
}