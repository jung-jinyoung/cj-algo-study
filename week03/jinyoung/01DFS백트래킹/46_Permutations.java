/**
 * 
 * 1. 접근 전략
 * - 순열 기반 백트래킹(Permutation Backtracking) 알고리즘 적용
 * - 순서 구분을 위해 매 탐색마다 0번 인덱스부터 전체 순회
 * - `boolean[] visited` 배열 활용을 통한 원소 사용 여부 추적 및 중복 방지
 * - 깊은 복사(`new ArrayList<>(currentPath)`)를 통한 자바 객체 참조 오염 방지
 * - 상태 변경(`visited[i] = true`, `currentPath.add()`) 및 상태 복구(`visited[i] = false`, `currentPath.remove()`, UNDO) 패턴 구현
 * 
 * 2. 복잡도 분석 
 * - 시간 복잡도: O(N! * N)
 *   - 길이가 N인 배열에서 생성 가능한 모든 순열의 총개수 N!개
 *   - 각 순열을 결과 리스트에 담을 때 소요되는 복사 연산 비용 O(N) 반영
 * - 공간 복잡도: O(N)
 *   - 재귀 호출 스택(Call Stack), 방문 체크 배열(`visited`), 경로 리스트의 최대 크기 N 기준
 */


import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> result;
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();

        // 순서가 중요한 배열이기 때문에 방문 배열 초기화 
        boolean[] visited = new boolean[nums.length];
        permutation(0, nums, new ArrayList<>(), visited);
        return result;
    }
    private void permutation(int depth, int[]nums, List<Integer> currentPath, boolean[] visited) {
        // 종료 조건 : 뽑은 원소의 개수가 전체 배열의 길이와 같아 졌을 때 (순열 완성)
        if (depth == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        
        // 순열은 순서가 중요하므로 매번 0부터 시작 
        for (int i = 0 ; i < nums.length ; i++) { 
            // 이미 방문했던 숫자면 continue;
            if (visited[i]) continue;
            // 아니라면 방문 처리 후 추가
            visited[i] = true;
            currentPath.add(nums[i]);

            permutation(depth+1, nums, currentPath, visited);

            // 상태 복구
            visited[i] = false;
            currentPath.remove(currentPath.size() - 1);
        }
    }
}