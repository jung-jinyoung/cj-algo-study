/*
   문제 유형: 동적 계획법(DP)으로 이전 두 계단의 최소 비용을 비교해 정상까지의 최소 누적 비용 계산
   문제8 : <LeetCode> - 746. Min Cost Climbing Stairs
   난이도: Easy
*/

/*
   전략

   1. cost 배열에 저장된 계단별 비용을 이용해 정상까지 올라가는 최소 비용을 구한다.
   -> cost[i] = i번 인덱스 계단에서 이동할 때 지불하는 비용
   -> 한 번에 이동 가능한 계단 수 = 1칸 또는 2칸
   -> 정상 위치 = cost.length
   2. 0번 또는 1번 인덱스에서 출발할 수 있다.
   -> 출발 위치를 선택하는 것 자체에는 비용 없음
   -> 선택한 계단에서 위로 이동할 때 해당 계단의 비용 지불
   3. 동적 계획법(DP)을 사용해 이전에 계산한 최소 비용을 재사용한다.
   -> DP = 작은 문제의 결과를 이용해 큰 문제의 결과 계산
   4. i번째 위치에 도달하는 마지막 이동은 1칸 또는 2칸이다.
   -> 1칸 이동 = i-1번째 계단에서 도착
   -> 2칸 이동 = i-2번째 계단에서 도착
   5. 두 이동 방법 중 누적 비용이 더 작은 값을 선택한다.
   -> dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
   6. 처음에는 0번 또는 1번 위치에서 출발할 수 있으므로 누적 비용을 0으로 설정한다.
   -> dp[0] = 0
   -> dp[1] = 0
   7. 전체 DP 배열 대신 이전 두 위치의 최소 비용만 저장한다.
   -> twoBefore = 두 위치 전까지의 최소 비용
   -> oneBefore = 한 위치 전까지의 최소 비용
   8. 2번째 위치부터 정상 위치까지 반복하며 최소 비용을 계산한다.
   9. 한 칸 이동했을 때의 누적 비용을 계산한다.
   -> oneStep = oneBefore + cost[i-1]
   10. 두 칸 이동했을 때의 누적 비용을 계산한다.
   -> twoSteps = twoBefore + cost[i-2]
   11. Math.min()으로 두 이동 방법 중 최소 비용을 선택한다.
   -> current = Math.min(oneStep, twoSteps)
   12. 다음 위치를 계산하기 위해 이전 두 위치의 최소 비용을 갱신한다.
   -> twoBefore = oneBefore
   -> oneBefore = current
   13. 정상 위치까지 계산한 최소 누적 비용을 반환한다.
*/

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // 0번 위치에서 출발할 때의 초기 비용
        int twoBefore = 0;
        // 1번 위치에서 출발할 때의 초기 비용
        int oneBefore = 0;
        // 2번째 위치부터 정상까지 최소 비용 계산
        for (int i = 2; i <= cost.length; i++) {
            // 한 칸 전 계단에서 이동하는 경우의 누적 비용
            int oneStep = oneBefore + cost[i - 1];
            // 두 칸 전 계단에서 이동하는 경우의 누적 비용
            int twoSteps = twoBefore + cost[i - 2];
            // 두 이동 방법 중 더 작은 비용 선택
            int current = Math.min(oneStep, twoSteps);
            // 다음 계산을 위해 이전 두 위치의 값 갱신
            twoBefore = oneBefore;
            oneBefore = current;
        }
        // 정상까지 올라가는 최소 누적 비용 반환
        return oneBefore;
    }
}