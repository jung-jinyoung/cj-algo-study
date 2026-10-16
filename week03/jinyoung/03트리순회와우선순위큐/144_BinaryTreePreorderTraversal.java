/** 
 * 1. 접근 전략 
 * - 재귀 함수(Recursion) 및 별도 헬퍼 메서드 패턴 적용
 * - 전위 순회(Preorder) 탐색 순서 준수 (`Root ➔ Left ➔ Right`)
 * - 기저 조건(Base Case, `node == null`) 설정을 통한 안전한 재귀 탈출
 * - 결과 누적용 `List<Integer>` 객체 파라미터 전달을 통한 데이터 수집
 * 
 * 2. 복잡도 분석 
 * - 시간 복잡도: O(N)
 *   - 트리에 존재하는 총 노드의 개수 N개만큼 모든 노드를 정확히 한 번씩 방문
 * - 공간 복잡도: O(N)
 *   - 최악의 경우(편향 트리) 재귀 호출 스택(Call Stack)이 트리의 높이인 N만큼 공간 소모
 */


import java.util.ArrayList;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode node, List<Integer> result) {
        if (node == null) return;

        result.add(node.val);
        preorder(node.left, result);
        preorder(node.right, result);
    }
}