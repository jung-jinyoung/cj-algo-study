/*
   문제 유형: DFS(깊이 우선 탐색)로 각 숫자의 더하기·빼기 경우를 모두 탐색해 목표 합 찾기
   문제1 : <프로그래머스> - 타겟 넘버
   난이도: Level 2
*/

/*
   전략

   1. numbers 배열의 모든 숫자에 + 또는 -를 붙여 target을 만드는 경우의 수를 구한다.
   -> 각 숫자는 반드시 한 번 사용.
   -> 숫자마다 더하기·빼기 중 한개 선택.
   2. DFS(깊이 우선 탐색)를 사용해 가능한 모든 경우를 확인한다.
   3. DFS 함수에 현재 숫자의 위치 index와 지금까지 계산한 합 sum을 전달한다.
   -> index = 현재 몇 번째 숫자를 처리하는지
   -> sum = 지금까지 더하거나 뺀 결과
   4. 처음에는 아무 숫자도 사용하지 않았으므로 index와 sum을 0으로 시작한다.
   5. 현재 숫자를 더하는 경우를 재귀 함수로 탐색한다.
   -> sum + numbers[index]
   6. 현재 숫자를 빼는 경우도 재귀 함수로 탐색한다.
   -> sum - numbers[index]
   7. 두 경우 모두 다음 숫자로 이동하므로 index를 1 증가시킨다.
   8. index가 numbers.length와 같아지면 모든 숫자를 사용한 것이다.
   9. 모든 숫자를 사용했을 때 sum이 target과 같으면 경우의 수 1을 반환한다.
   -> target과 다르면 경우의 수 0을 반환.
   10. 더하기와 빼기에서 찾은 경우의 수를 합쳐 최종 결과를 반환한다.
*/

class Solution {
    public int solution(int[] numbers, int target) {
        // 첫 번째 숫자부터 합계 0으로 DFS 탐색 시작
        return dfs(numbers, target, 0, 0);
    }
    // index: 현재 숫자의 위치, sum: 지금까지 계산한 합
    private int dfs(int[] numbers, int target, int index, int sum) {
        // 모든 숫자를 사용한 경우
        if (index == numbers.length) {
            // 최종 합이 target과 같으면 성공한 경우 1개
            if (sum == target) {
                return 1;
            }
            // 최종 합이 target과 다르면 실패한 경우
            return 0;
        }
        // 현재 숫자를 더하고 다음 숫자로 이동
        int plus = dfs(numbers, target, index + 1, sum + numbers[index]);
        // 현재 숫자를 빼고 다음 숫자로 이동
        int minus = dfs(numbers, target, index + 1, sum - numbers[index]);
        // 더하기와 빼기에서 찾은 성공 경우의 수 합산
        return plus + minus;
    }
}