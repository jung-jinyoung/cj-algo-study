# 전략: DFS(스택) + 상하좌우 방향 배열. 시작 칸의 원래 색(orig)을 기억하고, 시작 칸부터
#       상하좌우로 이어진 orig 색 칸을 스택으로 따라가며 새 색으로 칠한다.
#       칠한 칸은 더 이상 orig가 아니므로 '칠하기'가 곧 방문 표시 역할을 한다.
#       (orig == color면 칠해도 표시가 안 돼 무한 반복이 되므로 바로 반환)
# 시간복잡도: O(R × C)  — 모든 칸을 최대 한 번씩 칠하고 꺼냄
# Deque 써보기 array Deque

class Solution:
    def floodFill(self, image: list[list[int]], sr: int, sc: int, color: int) -> list[list[int]]:
        orig = image[sr][sc]
        if orig == color:                    # 이미 같은 색이면 바꿀 것 없음 (안 하면 무한 반복)
            return image

        R, C = len(image), len(image[0])
        dr = [-1, 1, 0, 0]                   # 상, 하, 좌, 우
        dc = [0, 0, -1, 1]

        image[sr][sc] = color                # 시작 칸 칠하고
        stack = [(sr, sc)]                   # 스택에 넣기
        while stack:
            r, c = stack.pop()
            for d in range(4):
                nr, nc = r + dr[d], c + dc[d]
                if 0 <= nr < R and 0 <= nc < C and image[nr][nc] == orig:   # 범위 안 + 원래 색
                    image[nr][nc] = color    # 넣을 때 바로 칠함 = 방문 표시
                    stack.append((nr, nc))
        return image