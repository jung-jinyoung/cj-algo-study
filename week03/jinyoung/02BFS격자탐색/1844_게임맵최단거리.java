
/**
 * 1. 접근 전략 
 * - 너비 우선 탐색(BFS) 기반 최단 거리 산출 알고리즘 적용
 * - `ArrayDeque` 기반 큐(Queue) 활용을 통한 탐색 성능 최적화
 * - 방향 벡터(`directions`) 활용 상하좌우 격자 탐색 구현
 * - `distances` 2차원 배열 초기화(-1)를 통한 방문 여부 확인 및 최단 이동 칸 수 동시 누적
 * - 목표 지점 도달 시 최소 거리 반환, 도달 불가 시 초기값(-1) 유지 특성 활용
 * 
 * 2. 복잡도 분석
 * - 시간 복잡도: O(N * M)
 *   - 세로 크기 N, 가로 크기 M인 2차원 격자의 모든 칸을 최악의 경우 한 번씩 방문
 * - 공간 복잡도: O(N * M)
 *   - 거리 저장용 2차원 배열(`distances`) 및 큐(Queue)가 최악의 경우 전체 격자 크기만큼 공간 소모
 */


import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int answer = 0;
        
        // 거리 저장 이중 행렬 초기화
        int[][] distances = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            Arrays.fill(distances[i], -1);
        }
        
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        distances[0][0] = 1;
            
        int[][] directions = new int[][]{
            {0,1},
            {1,0},
            {0,-1},
            {-1,0}
        };
        
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            
            for(int dir[] : directions){
                int dx = dir[0];
                int dy = dir[1];
                int nx = x + dx;
                int ny = y + dy;
                if (0 <= nx && nx < n && 0 <= ny && ny < m && maps[nx][ny] == 1){
                    if (distances[nx][ny] == -1){
                        distances[nx][ny] = distances[x][y] + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        
        }
        return distances[n-1][m-1];
    }
}