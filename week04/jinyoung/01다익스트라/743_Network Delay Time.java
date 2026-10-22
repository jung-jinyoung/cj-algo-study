/**
     * =====================================================================
     * [알고리즘 전략 및 복잡도 정리 / 회고]
     * =====================================================================
     * 1. 핵심 전략
     *    - 다익스트라 알고리즘 및 우선순위 큐 활용
     *    - 출발지(k)에서 각 정점까지의 최단 시간 산출
     *    - 인접 행렬(int[][] graph)을 통한 그래프 표현 (미연결 간선 -1 처리)
     *    - 그리디 방식의 최소 비용 노드 우선 탐색
     *    - 기존 최단 거리 초과 시 탐색 생략(Pruning)을 통한 연산 최소화
     * 
     * 2. 시간 복잡도: O(V^2)
     *    - V: 정점 개수
     *    - 정점 삽입 및 추출 연산: O(V log V)
     *    - 인접 행렬 탐색을 통한 이웃 노드 확인: 각 노드마다 모든 정점(V)을 순회하므로 O(V^2) 소요
     *    - 인접 행렬 사용으로 인해 희소 그래프(Sparse Graph) 환경에서도 불필요한 순회 비용 발생
     * 
     * 3. 공간 복잡도: O(V^2)
     *    - 인접 행렬(graph) 저장 공간: O(V^2)
     *    - 최단 거리 배열(distances) 및 우선순위 큐(pq): O(V)
     * 
     * 4. 개선 방향
     *    - 인접 행렬 구조로 인해 시간 및 공간 복잡도가 O(V^2)로 증가함
     *    - 다음 구현 시 인접 리스트(List<List<int[]>>)를 활용하여 시간 복잡도를 O((V + E) log V)로 단축하고 메모리 사용량 최적화 필요
     * =====================================================================
     */

import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // [현재 노드, 현재까지의 거리]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b) -> Integer.compare(a[1], b[1])
        );
        // 이중 배열 선언 (i 노드에서 j노드까지의 가중치)
        int[][] graph = new int[n + 1][n + 1];
        for (int[] row : graph) {
            Arrays.fill(row, -1);
        }
        for (int[] time : times) { 
            int u = time[0];
            int v = time[1];
            int w = time[2]; 
            // 방향성 가중 그래프 정보 추가
            graph[u][v] = w;
        }

        int[] distances = new int[n+1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        pq.offer(new int[]{k, 0});
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];

            if (distance > distances[node]) {
                continue;
            }

            for (int nx = 1; nx <= n ; nx++) {
                if (graph[node][nx] == -1) {
                    continue;
                }

                int newDistance = distance + graph[node][nx];
                if (newDistance < distances[nx]) {
                    distances[nx] = newDistance;
                    pq.offer(new int[]{nx, newDistance});
                }
            }
        }

        int answer = -1;
        for (int node = 1 ; node <= n ; node++){
            if (distances[node] == Integer.MAX_VALUE) {
                return -1;
            } else {
                answer = Math.max(answer, distances[node]);
            }
        }
        return answer;
    }
}