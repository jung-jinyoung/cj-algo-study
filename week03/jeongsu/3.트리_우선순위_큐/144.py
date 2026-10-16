# 전략: DFS(스택). 전위 순회 = 나 → 왼쪽 → 오른쪽 순서로 방문.
#       스택에서 노드를 꺼내 값을 기록하고, 자식은 '오른쪽 먼저, 왼쪽 나중'에 넣는다.
#       스택은 나중에 넣은 게 먼저 나오므로 왼쪽이 먼저 꺼내져 나 → 왼쪽 → 오른쪽 순서가 된다.
# 시간복잡도: O(N)  — 노드마다 한 번씩 넣고 꺼냄

from typing import Optional, List  

class TreeNode:                          
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def preorderTraversal(self, root:Optional[TreeNode]) -> List[int]:
        answer = []
        stack = []
        if root:                            
            stack.append(root)

        while stack:
            node = stack.pop()
            answer.append(node.val)          # 나
            if node.right:                   # 오른쪽을 먼저 넣어야
                stack.append(node.right)
            if node.left:                    # 왼쪽이 먼저 꺼내짐
                stack.append(node.left)
        return answer