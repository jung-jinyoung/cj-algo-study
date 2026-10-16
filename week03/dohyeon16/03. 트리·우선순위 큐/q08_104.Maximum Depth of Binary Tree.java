/*
   문제 유형: DFS(깊이 우선 탐색)로 왼쪽·오른쪽 서브트리의 깊이를 비교해 이진 트리의 최대 깊이 계산
   문제8 : <LeetCode> - 104. Maximum Depth of Binary Tree
   난이도: Easy
*/

/*
   전략

   1. 이진 트리에서 루트부터 가장 깊은 자식 노드까지의 최대 깊이를 구한다.
   -> 깊이 = 루트부터 가장 깊은 노드까지 포함된 노드의 개수
   -> ex) 루트 → 자식 → 손자 노드까지 연결되어 있다면 깊이 3
   2. DFS(깊이 우선 탐색)를 사용해 왼쪽과 오른쪽 자식 노드를 재귀적으로 탐색한다.
   3. 현재 노드 root가 null인지 확인한다.
   -> null = 해당 위치에 노드가 없는 상태
   -> 노드가 없으면 깊이 0 반환
   4. 현재 노드의 왼쪽 자식 노드를 재귀 함수로 탐색한다.
   -> root.left = 왼쪽 자식 노드
   -> leftDepth = 왼쪽 서브트리의 최대 깊이
   5. 현재 노드의 오른쪽 자식 노드도 재귀 함수로 탐색한다.
   -> root.right = 오른쪽 자식 노드
   -> rightDepth = 오른쪽 서브트리의 최대 깊이
   6. 왼쪽과 오른쪽 서브트리의 깊이를 비교한다.
   -> Math.max(leftDepth, rightDepth) = 더 깊은 쪽의 깊이
   7. 더 깊은 쪽의 깊이에 현재 노드 1개를 더한다.
   -> 현재 노드까지 포함한 최대 깊이
   8. 재귀 함수가 종료되면서 각 노드의 최대 깊이를 부모 노드로 반환한다.
   9. 최종적으로 루트 노드까지 계산된 최대 깊이를 반환한다.
*/

class Solution {
    public int maxDepth(TreeNode root) {
        // 현재 노드가 없으면 깊이 0 반환
        if (root == null) {
            return 0;
        }
        // 왼쪽 서브트리의 최대 깊이 계산
        int leftDepth = maxDepth(root.left);
        // 오른쪽 서브트리의 최대 깊이 계산
        int rightDepth = maxDepth(root.right);
        // 더 깊은 쪽을 선택하고 현재 노드 1개 추가
        return Math.max(leftDepth, rightDepth) + 1;
    }
}