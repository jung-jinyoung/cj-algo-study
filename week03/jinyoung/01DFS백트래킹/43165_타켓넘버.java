
/**
 * 1. 접근 전략 
 * - 이중 선택형(Binary Choice) DFS 패턴 활용:
 *   각 숫자를 '더하는 경우(+)'와 '빼는 경우(-)' 두 가지 갈림길로 나누어 전체 경우의 수를 탐색
 * - 파라미터 전달 방식:
 *   전역 변수를 사용하지 않고, 재귀 호출 시 매개변수(depth, currentSum)로 상태를 관리
 *   프로그래머스와 같은 플랫폼에서 발생할 수 있는 테스트 케이스 간 데이터 오염(Side Effect)을 원천 차단
 * - 반환값 누적 방식:
 *   탐색이 끝나는 리프 노드에서 조건을 만족하면 1, 아니면 0을 리턴하여 상위 호출로 누적 합산
 * 
 * 2. 복잡도 분석
 * - 시간 복잡도: O(2^N)
 *   - 배열의 각 원소마다 2가지 선택(+, -)을 하므로, 전체 트리의 노드 수는 2^N개
 *   - 문제에서 numbers의 길이는 최대 20이므로, 최대 2^20 (약 100만 번) 연산으로 충분히 시간 내에 통과
 * - 공간 복잡도: O(N)
 *   - 재귀 호출(Call Stack)의 최대 깊이는 배열의 길이 N과 같습니다. 따라서 스택 메모리는 O(N)을 소모
 */

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0 ;
        answer = dfs(numbers, target, 0, 0);
        return answer;
    }
    
    // dfs 이중선택형 메서드
    private int dfs(int[] numbers, int target, int depth, int currentSum) {
        // 종료 조건 : 모든 숫자 다 사용
        if (depth == numbers.length) {
            // 타켓인지 확인 후 리턴
            if (currentSum == target) {
                return 1;
            }
            
            return 0;
        }
        // 재귀 호출 : 해당 값을 더하거나 혹은 빼거나
        int count = 0 ;
        count += dfs(numbers, target, depth + 1, currentSum + numbers[depth]);
        count += dfs(numbers, target, depth + 1, currentSum - numbers[depth]);
        
        return count;
        
    }
}