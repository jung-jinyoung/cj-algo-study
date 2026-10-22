/**
 * 1. 핵심 전략
 *    - 다익스트라 알고리즘 및 우선순위 큐 활용
 *    - 출발지(src)에서 각 정점까지의 최단 거리 산출
 *    - 인접 리스트(List<List<int[]>>)를 통한 그래프 표현
 *    - 그리디 방식의 최소 비용 노드 우선 탐색
 *    - 기존 최단 거리 초과 시 탐색 생략(Pruning)을 통한 연산 최소화
 * 
 * 2. 시간 복잡도: O((V + E) log V)
 *    - V: 정점 개수, E: 간선 개수
 *    - 정점 삽입 및 추출 연산: O(V log V)
 *    - 간선 순회 및 큐 갱신 연산: O(E log V)
 * 
 * 3. 공간 복잡도: O(V + E)
 *    - 인접 리스트(graph) 저장 공간: O(V + E)
 *    - 최단 거리 배열(distances) 및 우선순위 큐(pq): O(V)
 * =====================================================================
 */
    
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Solution {
    public ArrayList<Integer> dijkstra(int V, int[][] edges, int src) {
        // 정답 인접 리스트 초기화
        ArrayList<Integer> answer = new ArrayList<>();
        
        // 가중치 반영 그래프 초기화
        List<List<int[]>> graph = new ArrayList<>();
        
        for (int i = 0 ; i < V ; i++){
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            // u, v, w
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
           
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }
        
        // 각 정점까지의 거리 배열 초기화
        int[] distances = new int[V];
        
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;
        
        // 우선순위 큐 초기화 (node, distance)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b) -> Integer.compare(a[1], b[1])
        );
        
        // src 추가
        pq.offer(new int[]{src, 0});
        
        // 다익스트라 실행
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];
            
            // 이미 더 짧은 거리를 확인했을 경우 
            if (distances[node] < distance) {
                continue;
            }
            for (int[] neighbor : graph.get(node)) {
                int nb = neighbor[0];
                int w = neighbor[1];
                int nowDistance = distance + w;
                // 이웃 노드 까지 가는 거리 비교 후 갱신
                if (nowDistance < distances[nb]) {
                    distances[nb] = nowDistance;
                    pq.offer(new int[]{nb, nowDistance});
                }
            }
        }
        
        for (int d : distances) {
            answer.add(d);
        }
        
        return answer;
    }
}