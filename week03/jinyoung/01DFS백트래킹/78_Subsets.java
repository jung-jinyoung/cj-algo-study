/**
 * 1. 접근 전략
 * - 조합 기반 백트래킹(Combination Backtracking) 알고리즘 적용
 * - `start` 인덱스 매개변수 활용을 통한 중복 탐지 방지 및 조합 생성
 * - 매 탐색 노드마다 현재 경로(`currentPath`)를 결과 리스트에 즉시 누적
 * - 깊은 복사(`new ArrayList<>(currentPath)`)를 통한 자바 객체 참조 오염 방지
 * - `currentPath.add()`(상태 변경)와 `currentPath.remove()`(상태 복구, UNDO) 패턴 구현
 * 
 * 2. 복잡도 분석
 * - 시간 복잡도: O(2^N * N)
 *   - 크기가 N인 배열에서 생성 가능한 모든 부분집합(Power Set)의 개수 2^N개
 *   - 각 부분집합을 결과 리스트에 담을 때 소요되는 복사 연산 비용 O(N) 반영
 * - 공간 복잡도: O(N)
 *   - 재귀 호출 스택(Call Stack)의 최대 깊이 및 경로 배열의 최대 크기 N 기준
 */


import java.util.ArrayList;
import java.util.List;

class Solution {
    // 전역 리스트 
    List<List<Integer>> result ;
    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        
        // 탐색 시작 
        backtrack(0, nums, new ArrayList<>());
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> currentPath) {
        // 탐색 결과 추가
        result.add(new ArrayList<>(currentPath));

        // start 인덱스부터 조합 탐색
        for (int i = start ; i < nums.length ; i++) {
            currentPath.add(nums[i]);
            backtrack(i+1, nums, currentPath);
            currentPath.remove(currentPath.size() - 1);
        }
    }
}

