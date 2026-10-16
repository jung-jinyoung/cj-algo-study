/**
 * 1. 접근 전략 
 * - 너비 우선 탐색(BFS) 기반 2차원 격자 영역 확장 알고리즘 적용
 * - `ArrayDeque` 기반 큐(Queue) 활용을 통한 탐색 성능 최적화
 * - 방향 벡터(`dr`, `dc`) 활용 상하좌우 인접 칸 탐색 구현
 * - 초기 색상과 목표 색상이 동일할 경우 무한 루프 방지를 위한 조기 리턴(Early Return) 예외 처리
 * - 방문 처리와 색상 변경(`image[nr][nc] = color`)의 동시 수행을 통한 중복 방문 방지
 * 
 * 2. 복잡도 분석 
 * - 시간 복잡도: O(N * M)
 *   - 세로 크기 N, 가로 크기 M인 2차원 격자의 모든 픽셀을 최악의 경우 한 번씩 방문
 * - 공간 복잡도: O(N * M)
 *   - 큐(Queue)가 최악의 경우 전체 격자의 크기만큼 공간 소모
 */

import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        // 예외 처리 : 이미 바꾸고자 하는 색상과 같다면 탐색 할 필요 없음
        if (originalColor == color) return image;

        int rows = image.length;
        int cols = image[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        // 시작 좌표 추가
        queue.add(new int[]{sr, sc});
        // 방문 처리 
        image[sr][sc] = color;

        // 방향 벡터 
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            // 4방향 탐색
            for (int i = 0 ; i < 4 ; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 격자 범위 안인지 확인
                // 연결된 칸인지 (기존 색상과 동일)
                if (0 <= nr && nr < rows && 0 <= nc && nc < cols
                    && image[nr][nc] == originalColor) {
                        image[nr][nc] = color;
                        queue.add(new int[]{nr, nc});
                }
            }
        }

        return image;
    }
}