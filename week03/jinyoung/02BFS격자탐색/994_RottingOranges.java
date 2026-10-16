/**
 * 
 * 1. 접근 전략 
 * - 다중 시작점 너비 우선 탐색(Multi-source BFS) 알고리즘 적용
 * - 초기 격자 전체 순회를 통한 모든 썩은 오렌지(`2`)의 좌표 일괄 큐 삽입 및 신선한 오렌지(`1`) 총개수 카운트
 * - `queue.size()` 기반 레벨(시간) 단위 반복문 구현을 통한 동시 다발적 확산 시뮬레이션
 * - 방향 벡터(`dr`, `dc`) 활용 상하좌우 인접 칸 탐색
 * - 썩음 발생 여부(`isRotten`)에 따른 경과 시간(분) 누적 관리
 * - 탐색 종료 후 신선한 오렌지 잔여 여부(`freshCount == 0`) 판별을 통한 최종 소요 시간 또는 `-1` 반환
 * 
 * 2. 복잡도 분석
 * - 시간 복잡도: O(N * M)
 *   - 세로 크기 N, 가로 크기 M인 2차원 격자의 모든 셀을 최악의 경우 한 번씩 방문 및 처리
 * - 공간 복잡도: O(N * M)
 *   - 큐(Queue)가 최악의 경우 전체 격자의 크기만큼 공간 소모
 */


import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        int freshCount = 0 ; 

        // 1. 모든 썩은 오렌지 좌표 큐에 넣기 + 신선한 오렌지 개수 카운트
        for (int r = 0 ; r < rows ; r++) { 
            for (int c = 0 ; c < cols ; c++) {
                if (grid[r][c] == 2) {
                    // 썩은 오렌지 삽입
                    queue.add(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    // 신선한 오렌지 카운트
                    freshCount ++;
                }
            }
        }

        // 처음부터 신선한 오렌지가 없으면 리턴
        if (freshCount == 0 ) return 0;

        int time = 0 ;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 2. BFS 탐색
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isRotten = false;

            for (int i = 0 ; i < size ; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                // 4방향 계산 
                for (int d = 0 ; d < 4 ; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(0 <= nr && nr < rows && 0 <= nc && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        freshCount --;
                        queue.add(new int[]{nr, nc});
                        isRotten = true;
                    }
                }
            }

            if (isRotten) {
                time++;
            }
        }

        if (freshCount == 0 ){ 
            return time;
        } else{
            return -1;
        }
    }
}