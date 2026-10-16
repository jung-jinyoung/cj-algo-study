/*
   문제 유형: DFS와 백트래킹으로 원소를 선택하고 되돌리며 모든 부분집합 생성
   문제2 : <LeetCode> - 78. Subsets
   난이도: Medium
*/

/*
   전략

   1. nums 배열에 있는 숫자들로 만들 수 있는 모든 부분집합을 구한다.
   -> ex) [1, 2]의 부분집합은 [], [1], [2], [1, 2].
   -> 빈 배열 []도 부분집합에 포함.
   2. 모든 부분집합을 저장할 answer 리스트를 만든다.
   3. 현재 선택한 숫자들을 저장할 current 리스트를 만든다.
   4. DFS 함수를 사용해 숫자를 하나씩 선택하면서 부분집합을 만든다.
   -> start = 다음 탐색을 시작할 인덱스.
   -> current = 현재까지 선택한 숫자들의 모음.
   5. DFS 함수가 호출될 때마다 현재 부분집합을 answer에 저장한다.
   -> 빈 부분집합부터 시작해 모든 선택 결과를 저장.
   6. start 위치부터 마지막 숫자까지 반복하며 숫자를 하나 선택한다.
   -> current.add(nums[i])로 현재 숫자를 추가.
   7. 숫자를 선택한 상태에서 DFS 함수를 다시 호출한다.
   -> i + 1부터 탐색해 이미 선택한 숫자를 중복 선택하지 않음.
   8. DFS 호출이 끝나면 방금 추가했던 숫자를 제거한다.
   -> current.remove(current.size() - 1)
   -> 이전 상태로 되돌리는 과정 = 백트래킹
   9. 다음 숫자를 선택하면서 가능한 모든 부분집합을 탐색한다.
   10. 모든 탐색이 끝나면 answer 반환한다.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        // 모든 부분집합을 저장할 리스트
        List<List<Integer>> answer = new ArrayList<>();
        // 아무 숫자도 선택하지 않은 상태에서 DFS 시작
        dfs(nums, 0, new ArrayList<>(), answer);
        return answer;
    }
    // start: 탐색 시작 위치, current: 현재 선택한 숫자들
    private void dfs(int[] nums, int start, List<Integer> current, List<List<Integer>> answer) {
        // 현재 부분집합을 복사하여 정답에 저장
        answer.add(new ArrayList<>(current));
        // 현재 위치부터 마지막 숫자까지 탐색
        for (int i = start; i < nums.length; i++) {
            // 현재 숫자를 부분집합에 추가
            current.add(nums[i]);
            // 다음 숫자부터 새로운 부분집합 탐색
            dfs(nums, i + 1, current, answer);
            // 방금 추가한 숫자를 제거하여 이전 상태로 복구
            current.remove(current.size() - 1);
        }
    }
}