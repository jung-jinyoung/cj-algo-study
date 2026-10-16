/*
   문제 유형: BFS(너비 우선 탐색)로 여러 썩은 오렌지에서 동시에 탐색을 시작해 모든 오렌지가 썩는 최소 시간 계산
   문제6 : <LeetCode> - 994. Rotting Oranges
   난이도: Medium
*/

/*
   전략

   1. grid는 오렌지의 상태를 나타내는 2차원 배열이다.
   -> 0 = 빈 칸
   -> 1 = 신선한 오렌지
   -> 2 = 썩은 오렌지
   2. 전체 배열을 확인하면서 썩은 오렌지의 위치를 Queue에 저장한다.
   -> 처음부터 썩어 있는 오렌지들을 모두 탐색 시작점으로 사용
   3. 신선한 오렌지의 개수를 fresh 변수에 저장한다.
   -> grid 값이 1인 칸의 개수
   4. BFS(너비 우선 탐색)를 사용해 썩은 오렌지에서 상하좌우로 전파한다.
   -> 여러 시작점에서 동시에 탐색하는 다중 시작점 BFS
   5. 상하좌우 이동을 위한 방향 배열 dr과 dc를 만든다.
   -> 위쪽 = row - 1
   -> 아래쪽 = row + 1
   -> 왼쪽 = col - 1
   -> 오른쪽 = col + 1
   6. Queue에 현재 저장된 오렌지의 개수를 size에 저장한다.
   -> 현재 1분 동안 동시에 썩음을 전파할 오렌지의 개수
   7. Queue에서 오렌지의 위치를 하나씩 꺼내 상하좌우를 확인한다.
   8. 다음 위치가 배열 범위 안에 있고 신선한 오렌지인지 확인한다.
   -> 배열 범위를 벗어나면 건너뛰기
   -> 빈 칸이거나 이미 썩은 오렌지라면 건너뛰기
   9. 신선한 오렌지를 발견하면 해당 칸의 값을 2로 변경한다.
   -> grid[nextRow][nextCol] = 2
   10. 새롭게 썩은 오렌지의 위치를 Queue에 추가한다.
   -> 다음 1분 동안 썩음을 전파할 오렌지
   11. 신선한 오렌지가 하나 썩을 때마다 fresh를 1 감소시킨다.
   12. 현재 1분에 해당하는 오렌지들의 탐색이 끝나면 minutes를 1 증가시킨다.
   13. 모든 오렌지가 썩거나 더 이상 전파할 오렌지가 없을 때까지 반복한다.
   14. 신선한 오렌지가 모두 사라졌다면 경과 시간 minutes를 반환한다.
   -> fresh == 0이면 minutes 반환
   15. 신선한 오렌지가 남아 있다면 모든 오렌지를 썩게 만들 수 없으므로 -1을 반환한다.
*/

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        // 배열의 행과 열의 개수
        int rows = grid.length;
        int cols = grid[0].length;
        // BFS 탐색에 사용할 큐
        Queue<int[]> queue = new ArrayDeque<>();
        // 신선한 오렌지의 개수
        int fresh = 0;
        // 전체 배열을 확인하면서 오렌지 상태 검사
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 썩은 오렌지는 BFS 시작 위치로 저장
                if (grid[row][col] == 2) {
                    queue.offer(new int[]{row, col});
                }
                // 신선한 오렌지의 개수 증가
                else if (grid[row][col] == 1) {
                    fresh++;
                }
            }
        }
        // 상하좌우 이동을 위한 방향 배열
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        // 오렌지가 썩는 데 걸린 시간
        int minutes = 0;
        // 탐색할 오렌지가 있고 신선한 오렌지가 남아 있는 동안 반복
        while (!queue.isEmpty() && fresh > 0) {
            // 현재 1분 동안 탐색할 썩은 오렌지의 개수
            int size = queue.size();
            // 현재 시간에 해당하는 오렌지만 탐색
            for (int i = 0; i < size; i++) {
                // 큐에서 가장 앞에 있는 썩은 오렌지 꺼내기
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                // 현재 위치에서 상하좌우 네 방향 탐색
                for (int j = 0; j < 4; j++) {
                    int nextRow = row + dr[j];
                    int nextCol = col + dc[j];
                    // 배열 범위를 벗어나면 건너뛰기
                    if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                        continue;
                    }
                    // 신선한 오렌지가 아니면 건너뛰기
                    if (grid[nextRow][nextCol] != 1) {
                        continue;
                    }
                    // 신선한 오렌지를 썩은 오렌지로 변경
                    grid[nextRow][nextCol] = 2;
                    // 남아 있는 신선한 오렌지 개수 감소
                    fresh--;
                    // 새롭게 썩은 오렌지를 다음 탐색 위치로 추가
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
            // 현재 1분 동안의 전파가 끝났으므로 시간 증가
            minutes++;
        }
        // 신선한 오렌지가 모두 사라졌으면 경과 시간 반환
        if (fresh == 0) {
            return minutes;
        }
        // 썩지 못한 오렌지가 남아 있는 경우
        return -1;
    }
}