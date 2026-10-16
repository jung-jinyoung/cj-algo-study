/*
   문제 유형: DFS(깊이 우선 탐색)로 이진 트리의 루트 → 왼쪽 → 오른쪽 순서대로 노드 방문
   문제7 : <LeetCode> - 144. Binary Tree Preorder Traversal
   난이도: Easy
*/

/*
   전략

   1. 이진 트리의 모든 노드를 전위 순회(Preorder Traversal) 방식으로 탐색한다.
   -> 전위 순회 = 루트 → 왼쪽 자식 → 오른쪽 자식
   2. 방문한 노드의 값을 저장할 answer 리스트를 만든다.
   3. DFS(깊이 우선 탐색)를 사용해 루트 노드부터 재귀적으로 탐색한다.
   -> root = 현재 탐색을 시작할 노드
   4. 현재 노드가 null인지 확인한다.
   -> null = 해당 위치에 노드가 없는 상태
   -> null이면 더 이상 탐색할 노드가 없으므로 재귀 함수 종료
   5. 현재 노드가 존재하면 해당 노드의 값을 answer에 추가한다.
   -> root.val = 현재 노드에 저장된 값
   6. 현재 노드의 왼쪽 자식 노드를 DFS로 탐색한다.
   -> root.left = 왼쪽 자식 노드
   7. 왼쪽 자식과 연결된 모든 노드의 탐색이 끝나면 오른쪽 자식 노드를 DFS로 탐색한다.
   -> root.right = 오른쪽 자식 노드
   8. 왼쪽과 오른쪽 자식 노드에서도 동일한 순서로 DFS 탐색을 반복한다.
   9. 모든 노드를 방문하면 재귀 함수의 실행을 종료한다.
   10. 방문한 노드의 값이 순서대로 저장된 answer를 반환한다.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        // 전위 순회로 방문한 노드의 값을 저장할 리스트
        List<Integer> answer = new ArrayList<>();
        // 루트 노드부터 DFS 탐색 시작
        dfs(root, answer);
        return answer;
    }
    // 현재 노드부터 전위 순회 진행
    private void dfs(TreeNode root, List<Integer> answer) {
        // 현재 노드가 없으면 탐색 종료
        if (root == null) {
            return;
        }
        // 첫 번째: 현재 노드의 값을 저장
        answer.add(root.val);
        // 두 번째: 왼쪽 자식 노드 탐색
        dfs(root.left, answer);
        // 세 번째: 오른쪽 자식 노드 탐색
        dfs(root.right, answer);
    }
}