/*
   문제 유형: DFS와 백트래킹으로 숫자의 사용 여부를 관리하며 모든 순열 생성
   문제3 : <LeetCode> - 46. Permutations
   난이도: Medium
*/

/*
   전략

   1. nums 배열의 모든 숫자를 사용해 만들 수 있는 순열을 구한다.
   -> 순열은 숫자의 선택 순서가 다르면 서로 다른 경우로 취급.
   -> ex) [1, 2]와 [2, 1]은 서로 다른 순열.
   2. 모든 순열을 저장할 answer 리스트를 만든다.
   3. 현재 선택한 숫자를 저장할 current 리스트를 만든다.
   4. 각 숫자의 사용 여부를 저장할 boolean 배열 used를 만든다.
   -> used[i]가 true면 현재 순열에서 이미 사용한 숫자.
   -> used[i]가 false면 아직 사용하지 않은 숫자.
   5. DFS 함수를 사용해 숫자를 하나씩 선택하면서 순열을 만든다.
   6. nums를 처음부터 끝까지 확인하면서 아직 사용하지 않은 숫자를 찾는다.
   -> 이미 사용한 숫자라면 건너뜀.
   7. 사용하지 않은 숫자를 찾으면 used[i]를 true로 변경하고 current에 추가한다.
   8. 숫자를 선택한 상태에서 DFS 함수를 다시 호출한다.
   -> 다음 위치에 들어갈 숫자를 선택하기 위한 과정.
   9. current에 저장된 숫자의 개수가 nums.length와 같아지면 순열이 완성된다.
   -> 완성된 순열을 복사하여 answer에 저장.
   10. DFS 호출이 끝나면 방금 추가한 숫자를 제거하고 used[i]를 false로 변경한다.
   11. 다른 숫자를 선택하면서 가능한 모든 순열을 탐색한다.
   12. 모든 탐색이 끝나면 answer 반환한다.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // 모든 순열을 저장할 리스트
        List<List<Integer>> answer = new ArrayList<>();
        // 숫자의 사용 여부를 저장할 배열 (기본값 false)
        boolean[] used = new boolean[nums.length];
        // 아무 숫자도 선택하지 않은 상태에서 DFS 시작
        dfs(nums, used, new ArrayList<>(), answer);
        return answer;
    }
    // current: 현재 선택한 숫자들, used: 숫자의 사용 여부
    private void dfs(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> answer) {
        // 모든 숫자를 선택했다면 완성된 순열 저장
        if (current.size() == nums.length) {
            answer.add(new ArrayList<>(current));
            return;
        }
        // nums의 모든 숫자를 처음부터 확인
        for (int i = 0; i < nums.length; i++) {
            // 이미 사용한 숫자는 건너뛰기
            if (used[i]) {
                continue;
            }
            // 현재 숫자를 사용한 것으로 표시
            used[i] = true;
            // 현재 숫자를 순열에 추가
            current.add(nums[i]);
            // 다음 위치에 들어갈 숫자 탐색
            dfs(nums, used, current, answer);
            // 방금 추가한 숫자를 제거하여 이전 상태로 복구
            current.remove(current.size() - 1);
            // 다른 순열에서 사용할 수 있도록 사용 여부 초기화
            used[i] = false;
        }
    }
}