/*
   문제 유형: BFS(너비 우선 탐색)로 상하좌우 이동 가능한 경로를 탐색해 도착점까지의 최단거리 찾기
   문제5 : <프로그래머스> - 게임 맵 최단거리
   난이도: Level 2
*/

/*
   전략

   1. maps는 0과 1로 이루어진 2차원 배열이다.
   -> 0 = 벽이 있는 칸으로 이동 불가능
   -> 1 = 벽이 없는 칸으로 이동 가능
   2. 시작 위치는 왼쪽 위 (0, 0), 도착 위치는 오른쪽 아래로 설정한다.
   -> 시작 위치 = maps[0][0]
   -> 도착 위치 = maps[rows - 1][cols - 1]
   3. BFS(너비 우선 탐색)를 사용해 시작 위치부터 도착 위치까지 탐색한다.
   -> BFS는 가까운 위치부터 순서대로 탐색하므로 최단거리 계산에 적합
   4. 각 칸까지의 최단거리를 저장할 distance 배열을 만든다.
   -> 0 = 아직 방문하지 않은 칸
   -> 1 이상 = 시작 위치부터 해당 칸까지 지나간 칸의 개수
   5. 현재 탐색할 위치를 저장할 Queue를 만든다.
   -> Queue는 먼저 들어온 위치를 먼저 탐색하는 FIFO 구조
   6. 시작 위치 (0, 0)을 Queue에 넣고 distance[0][0]을 1로 설정한다.
   -> 시작 칸도 지나간 칸의 개수에 포함
   7. Queue에서 가장 앞에 있는 위치를 꺼낸다.
   8. 현재 위치가 도착 위치인지 확인한다.
   -> 도착했다면 현재 위치까지의 최단거리 반환
   9. 현재 위치를 기준으로 상하좌우 네 방향을 확인한다.
   -> 위쪽 = row - 1
   -> 아래쪽 = row + 1
   -> 왼쪽 = col - 1
   -> 오른쪽 = col + 1
   10. 다음 위치가 이동 가능한 칸인지 확인한다.
   -> 배열 범위 안에 있는 위치
   -> maps 값이 1인 위치
   -> 아직 방문하지 않은 위치
   11. 이동 가능한 칸이면 현재 거리에서 1을 더해 distance에 저장한다.
   -> distance[nextRow][nextCol] = distance[row][col] + 1
   12. 이동한 위치를 Queue에 추가하고 BFS 탐색을 반복한다.
   13. Queue가 빌 때까지 도착 위치를 찾지 못하면 -1을 반환한다.
*/

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {
        // 게임 맵의 행과 열의 개수
        int rows = maps.length;
        int cols = maps[0].length;
        // 시작 위치부터 각 칸까지의 최단거리 저장
        int[][] distance = new int[rows][cols];
        // 상하좌우 이동을 위한 방향 배열
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        // BFS에서 탐색할 위치를 저장할 큐
        Queue<int[]> queue = new ArrayDeque<>();
        // 시작 위치를 큐에 추가
        queue.offer(new int[]{0, 0});
        // 시작 칸을 포함하므로 거리 1로 설정
        distance[0][0] = 1;
        // 큐에 탐색할 위치가 남아 있는 동안 반복
        while (!queue.isEmpty()) {
            // 큐에서 가장 앞에 있는 위치 꺼내기
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            // 도착 위치에 도달하면 최단거리 반환
            if (row == rows - 1 && col == cols - 1) {
                return distance[row][col];
            }
            // 현재 위치에서 상하좌우 네 방향 탐색
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];
                // 다음 위치가 배열 범위를 벗어나면 건너뛰기
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols) {
                    continue;
                }
                // 벽이 있는 칸이면 건너뛰기
                if (maps[nextRow][nextCol] == 0) {
                    continue;
                }
                // 이미 방문한 칸이면 건너뛰기
                if (distance[nextRow][nextCol] != 0) {
                    continue;
                }
                // 다음 칸까지의 최단거리 저장
                distance[nextRow][nextCol] = distance[row][col] + 1;
                // 다음 탐색 위치를 큐에 추가
                queue.offer(new int[]{nextRow, nextCol});
            }
        }
        // 도착 위치까지 이동할 수 없는 경우
        return -1;
    }
}