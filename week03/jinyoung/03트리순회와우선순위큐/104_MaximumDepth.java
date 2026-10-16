/**
 * 
 * 1. 접근 전략 
 * - 상향식(Bottom-Up) 재귀 방식(후위 순회 성격) 적용
 * - 기저 조건(Base Case, `root == null`) 설정을 통해 리프 노드 하위의 깊이 0 반환
 * - 좌측 자식과 우측 자식의 최대 깊이를 각각 재귀적으로 산출
 * - 양쪽 깊이 중 큰 값(`Math.max`)에 현재 노드 자신을 포함하는 가중치 `1`을 더해 상위 노드로 반환
 * 
 * 2. 복잡도 분석
 * - 시간 복잡도: O(N)
 *   - 트리의 모든 노드(N개)를 정확히 한 번씩 방문
 * - 공간 복잡도: O(H) (단, H는 트리의 높이. 최악의 경우 편향 트리에서 O(N))
 *   - 재귀 호출 스택(Call Stack)이 트리의 높이만큼 메모리 소모
 */


class Solution {
    public int maxDepth(TreeNode root) {
        // 전위순회 : root -> left -> right
        if (root == null) {
            return 0;
        }
        // 왼쪽 자식 방향으로 최대 깊이 재귀 호출
        int leftDepth = maxDepth(root.left);
        // 오른쪽 자식 방향 최대 깊이 재귀 호출
        int rightDepth = maxDepth(root.right);

        // 좌측과 우측 깊이 중 큰 값에 현재 노드 자신을 포함하는 가중치 1을 더해 반환
        return Math.max(leftDepth, rightDepth) + 1;
        
    }
}