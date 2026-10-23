# 전략: BFS(큐) + 상하좌우 방향 배열. 시작 칸부터 큐로 퍼져 나가며 dist[칸] = 앞 칸까지 거리 + 1을 기록한다.
#       큐는 먼저 넣은 칸부터 꺼내므로 1칸 거리 → 2칸 거리 → … 순서로 퍼지고,
#       그래서 어떤 칸에 처음 도착한 거리가 곧 최단 거리다. dist가 0이면 아직 안 간 칸(방문 표시 겸용).
# 시간복잡도: O(N × M)  — 모든 칸을 최대 한 번씩 넣고 꺼냄
# 전위 중위 후위 

from collections import deque

def solution(maps):
    n, m = len(maps), len(maps[0])
    dr = [-1, 1, 0, 0]                       # 상, 하, 좌, 우
    dc = [0, 0, -1, 1]

    dist = [[0] * m for _ in range(n)]       # 0 = 아직 안 간 칸
    dist[0][0] = 1                           # 시작 칸도 1칸으로 셈
    q = deque([(0, 0)])

    while q:
        r, c = q.popleft()                   # 먼저 넣은 칸부터 (스택과 반대)
        for d in range(4):
            nr, nc = r + dr[d], c + dc[d]
            if 0 <= nr < n and 0 <= nc < m and maps[nr][nc] == 1 and dist[nr][nc] == 0:
                dist[nr][nc] = dist[r][c] + 1    # 넣을 때 바로 거리 기록 = 방문 표시
                q.append((nr, nc))

    if dist[n-1][m-1] == 0:                  # 끝 칸에 한 번도 못 갔으면
        return -1
    return dist[n-1][m-1]