# 전략: DFS(스택). 스택에 (노드, 그 노드의 깊이)를 함께 넣는다. 루트는 깊이 1.
#       꺼낼 때마다 깊이를 answer와 비교해 더 크면 바꾸고, 자식은 깊이+1로 넣는다.
#       모든 노드를 다 꺼내면 answer가 가장 깊은 깊이.
# 시간복잡도: O(N)  — 노드마다 한 번씩 넣고 꺼냄

from typing import Optional

class TreeNode:                          
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        answer = 0
        stack = []
        if root:                                     # 빈 트리면 깊이 0
            stack.append((root, 1))                  # (노드, 깊이) — 루트는 1층

        while stack:
            node, depth = stack.pop()
            if depth > answer:                       # 지금까지 본 것보다 깊으면 갱신
                answer = depth
            if node.left:
                stack.append((node.left, depth + 1))     # 자식은 한 층 아래
            if node.right:
                stack.append((node.right, depth + 1))
        return answer