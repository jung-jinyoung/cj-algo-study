/*
   문제 유형: 동적 계획법(DP)으로 주어진 동전을 조합해 목표 금액을 만드는 최소 동전 개수 계산
   문제3 : <LeetCode> - 322. Coin Change
   난이도: Medium
*/

/*
   전략

   1. coins 배열에 저장된 동전 종류를 이용해 amount를 만드는 최소 동전 개수를 구한다.
   -> coins[i] = 사용할 수 있는 동전의 금액
   -> amount = 만들어야 하는 목표 금액
   -> 각 동전은 무제한 사용 가능
   2. 가장 큰 동전부터 선택하는 그리디 방식은 항상 최적의 결과를 보장하지 않는다.
   -> 동전 종류에 따라 큰 동전을 먼저 선택하는 것이 비효율적인 경우 존재
   3. 동적 계획법(DP)을 사용해 작은 금액의 최소 동전 개수부터 계산한다.
   -> DP = 작은 문제의 결과를 재사용해 큰 문제의 결과 계산
   4. 금액별 최소 동전 개수를 저장할 dp 배열을 생성한다.
   -> dp[i] = 금액 i를 만드는 최소 동전 개수
   5. dp 배열의 크기를 amount+1로 설정한다.
   -> 0원부터 목표 금액까지 저장
   6. 모든 금액의 초기값을 amount+1로 설정한다.
   -> amount+1 = 아직 만들 수 없는 금액을 나타내는 값
   -> 동전 금액은 모두 1 이상이므로 최소 동전 개수가 amount를 초과할 필요 없음
   7. 금액 0원을 만드는 데 필요한 동전 개수는 0개로 설정한다.
   -> dp[0] = 0
   8. coins 배열에서 동전을 하나씩 선택한다.
   9. 선택한 동전이 목표 금액보다 크면 계산을 생략한다.
   -> coin > amount
   10. 선택한 동전 금액부터 목표 금액까지 오름차순으로 확인한다.
   -> 같은 동전을 여러 번 사용할 수 있도록 오름차순 탐색
   11. 현재 금액에서 선택한 동전의 금액을 뺀 상태를 확인한다.
   -> dp[value-coin] = 남은 금액을 만드는 최소 동전 개수
   12. 해당 동전을 한 개 추가한 경우의 동전 개수를 계산한다.
   -> dp[value-coin] + 1
   13. 기존 최소 동전 개수와 새로운 경우를 비교해 더 작은 값을 저장한다.
   -> dp[value] = Math.min(dp[value], dp[value-coin] + 1)
   14. 모든 동전에 대한 계산이 끝나면 dp[amount]를 확인한다.
   15. dp[amount]가 초기값 amount+1이면 목표 금액을 만들 수 없는 상태이다.
   -> -1 반환
   16. 목표 금액을 만들 수 있다면 dp[amount]를 반환한다.
*/

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        // 금액별 최소 동전 개수 저장
        int[] dp = new int[amount + 1];
        // 만들 수 없는 금액을 나타내는 초기값
        Arrays.fill(dp, amount + 1);
        // 0원을 만드는 데 필요한 동전 개수
        dp[0] = 0;
        // 사용할 수 있는 동전을 하나씩 확인
        for (int coin : coins) {
            // 목표 금액보다 큰 동전은 계산 생략
            if (coin > amount) {
                continue;
            }
            // 같은 동전을 여러 번 사용할 수 있도록 오름차순 계산
            for (int value = coin; value <= amount; value++) {
                // 기존 최소 개수와 현재 동전을 추가한 경우 비교
                dp[value] = Math.min(dp[value], dp[value - coin] + 1);
            }
        }
        // 목표 금액을 만들 수 없는 경우
        if (dp[amount] == amount + 1) {
            return -1;
        }
        // 목표 금액을 만드는 최소 동전 개수 반환
        return dp[amount];
    }
}