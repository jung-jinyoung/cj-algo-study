# 전략: BFS(큐), 시작점 여러 개. 처음에 썩은 오렌지(2)를 '전부' 큐에 넣고(시간 0), 신선한 오렌지(1) 개수를 센다.
#       큐에서 꺼낸 칸의 상하좌우 신선한 오렌지를 썩게 만들고(2로 바꿈 = 방문 표시) 시간+1로 큐에 넣는다.
#       큐는 시간 순서대로 꺼내지므로 마지막에 꺼낸 시간이 다 썩는 데 걸린 시간.
#       끝나고도 신선한 오렌지가 남아 있으면 -1.
# 시간복잡도: O(R × C)  — 모든 칸을 최대 한 번씩 넣고 꺼냄
# 어떻게 꺼낼 건지 

from collections import deque

class Solution:
    def orangesRotting(self, grid: list[list[int]]) -> int:
        answer = 0
        R, C = len(grid), len(grid[0])
        dr = [-1, 1, 0, 0]                   # 상, 하, 좌, 우
        dc = [0, 0, -1, 1]

        q = deque()
        fresh = 0
        for r in range(R):
            for c in range(C):
                if grid[r][c] == 2:          # 썩은 오렌지는 전부 시작점 (시간 0)
                    q.append((r, c, 0))
                elif grid[r][c] == 1:        # 신선한 오렌지 개수 세기
                    fresh += 1

        while q:
            r, c, t = q.popleft()
            answer = t                       # 시간 순서대로 꺼내므로 마지막에 꺼낸 t가 가장 큼
            for d in range(4):
                nr, nc = r + dr[d], c + dc[d]
                if 0 <= nr < R and 0 <= nc < C and grid[nr][nc] == 1:
                    grid[nr][nc] = 2         # 썩게 만들기 = 방문 표시
                    fresh -= 1
                    q.append((nr, nc, t + 1))

        if fresh > 0:                        # 끝까지 안 썩은 오렌지가 있으면
            answer = -1
        return answer