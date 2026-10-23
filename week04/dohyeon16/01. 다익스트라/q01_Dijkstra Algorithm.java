/*
   문제 유형: 다익스트라 알고리즘과 최소 힙으로 출발 정점에서 모든 정점까지의 최단거리 계산
   문제1 : <GeeksforGeeks> - Dijkstra Algorithm
   난이도: Medium
*/

/*
   전략

   1. V개의 정점과 edges 배열에 저장된 간선 정보를 이용해 최단거리를 구한다.
   -> V = 전체 정점 개수
   -> edges[i] = {출발 정점, 도착 정점, 가중치}
   -> src = 최단거리 계산을 시작할 정점
   2. 그래프를 인접 리스트 형태로 구성한다.
   -> graph.get(u) = u번 정점과 연결된 정점 및 가중치 목록
   3. 무방향 그래프이므로 간선 정보를 양방향으로 저장한다.
   -> u에서 v로 이동 가능
   -> v에서 u로 이동 가능
   4. 출발 정점에서 각 정점까지의 최단거리를 저장할 dist 배열을 만든다.
   -> Integer.MAX_VALUE = 아직 최단거리를 찾지 못한 상태
   5. 출발 정점 src의 거리를 0으로 초기화한다.
   6. 가장 거리가 짧은 정점을 먼저 탐색하기 위해 PriorityQueue를 생성한다.
   -> {정점 번호, 출발점부터의 거리} 저장
   -> 거리가 작은 정점부터 꺼내는 최소 힙
   7. 출발 정점 src와 거리 0을 PriorityQueue에 추가한다.
   8. PriorityQueue가 빌 때까지 가장 거리가 짧은 정점을 꺼내 탐색한다.
   9. 꺼낸 거리가 현재 dist 배열에 저장된 거리보다 크면 건너뛴다.
   -> 이미 더 짧은 경로를 발견한 오래된 큐 항목
   10. 현재 정점과 연결된 모든 인접 정점을 확인한다.
   11. 현재 정점을 거쳐 이동하는 새로운 거리를 계산한다.
   -> 새로운 거리 = 현재 정점까지의 최단거리 + 간선 가중치
   12. 새로운 거리가 기존 최단거리보다 작으면 dist 배열을 갱신한다.
   -> 더 짧은 경로를 발견한 상태
   13. 갱신된 정점과 새로운 거리를 PriorityQueue에 추가한다.
   14. 모든 최단거리 탐색이 끝나면 dist 배열을 ArrayList로 변환한다.
   15. 출발 정점에서 모든 정점까지의 최단거리를 반환한다.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public ArrayList<Integer> dijkstra(int V, int[][] edges, int src) {
        // 각 정점과 연결된 정점 및 가중치 저장
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        // 무방향 그래프의 간선 정보를 양방향으로 저장
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }
        // 출발 정점에서 각 정점까지의 최단거리 저장
        int[] dist = new int[V];
        // 모든 거리를 무한대로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 출발 정점까지의 거리는 0
        dist[src] = 0;
        // 거리가 가장 짧은 정점을 먼저 꺼내는 최소 힙
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        // 출발 정점과 거리 0을 큐에 추가
        queue.offer(new int[]{src, 0});
        // 탐색할 정점이 남아 있는 동안 반복
        while (!queue.isEmpty()) {
            // 현재까지 가장 짧은 거리를 가진 정점 꺼내기
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];
            // 이미 더 짧은 경로가 발견된 경우 건너뛰기
            if (distance > dist[node]) {
                continue;
            }
            // 현재 정점과 연결된 모든 정점 확인
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int weight = next[1];
                // 현재 정점을 거쳐 이동하는 새로운 거리 계산
                long newDistance = (long) distance + weight;
                // 기존 최단거리보다 짧으면 갱신
                if (newDistance < dist[nextNode]) {
                    dist[nextNode] = (int) newDistance;
                    // 갱신된 정점과 최단거리를 큐에 추가
                    queue.offer(new int[]{nextNode, dist[nextNode]});
                }
            }
        }
        // 최단거리 배열을 반환 형식으로 변환
        ArrayList<Integer> answer = new ArrayList<>();
        for (int distance : dist) {
            answer.add(distance);
        }
        return answer;
    }
}