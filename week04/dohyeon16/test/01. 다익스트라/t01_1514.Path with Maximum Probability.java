/*
   문제 유형: 최대 힙 기반 다익스트라로 출발 정점부터 도착 정점까지 성공 확률이 가장 높은 경로 탐색
   문제1 : <LeetCode> - 1514. Path with Maximum Probability
   난이도: Medium
*/

/*
   전략

   1. edges와 succProb 배열을 이용해 정점 사이의 연결 관계와 이동 성공 확률을 확인한다.
   -> edges[i] = 연결된 두 정점
   -> succProb[i] = 해당 간선을 통과할 성공 확률
   -> 정점 번호 = 0부터 n-1까지
   2. 그래프는 무방향 그래프이므로 양쪽 방향으로 간선 정보를 저장한다.
   3. 인접 리스트를 사용해 각 정점과 연결된 간선 정보만 저장한다.
   -> graph.get(node) = 해당 정점과 연결된 간선 목록
   4. Edge 클래스로 다음 정점과 해당 간선의 성공 확률을 관리한다.
   -> to = 다음 정점
   -> probability = 해당 간선의 성공 확률
   5. State 클래스로 탐색 중인 정점과 출발점부터의 누적 성공 확률을 관리한다.
   -> node = 현재 정점
   -> probability = 현재 정점까지의 누적 성공 확률
   6. 각 정점까지 도달할 수 있는 최대 성공 확률을 best 배열에 저장한다.
   -> best[node] = 출발 정점부터 node까지의 최대 성공 확률
   -> 초기값 = 0.0
   7. 출발 정점에서는 이동하지 않았으므로 성공 확률을 1.0으로 설정한다.
   -> best[start_node] = 1.0
   8. 성공 확률이 높은 경로부터 탐색하기 위해 최대 힙을 사용한다.
   -> PriorityQueue의 확률 내림차순 정렬
   9. 출발 정점과 초기 확률 1.0을 우선순위 큐에 삽입한다.
   10. 큐에서 누적 성공 확률이 가장 높은 정점을 꺼낸다.
   11. 이미 더 높은 확률의 경로가 발견된 정점이면 탐색을 생략한다.
   -> current.probability < best[current.node]
   12. 도착 정점에 도달하면 현재 확률을 반환한다.
   -> 최대 힙에서 꺼낸 도착 정점의 최대 확률 확정
   13. 현재 정점과 연결된 다음 정점들을 확인한다.
   14. 현재 누적 확률에 다음 간선의 성공 확률을 곱한다.
   -> newProbability = current.probability * next.probability
   15. 기존 최대 성공 확률보다 크면 best 배열을 갱신한다.
   -> newProbability > best[next.to]
   16. 확률이 개선된 정점을 우선순위 큐에 추가한다.
   17. 큐가 빌 때까지 도착 정점에 도달하지 못하면 0.0을 반환한다.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    // 간선의 다음 정점과 이동 성공 확률
    private static class Edge {
        int to;
        double probability;
        Edge(int to, double probability) {
            this.to = to;
            this.probability = probability;
        }
    }
    // 현재 정점과 출발점부터의 누적 성공 확률
    private static class State {
        int node;
        double probability;
        State(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // 무방향 그래프의 인접 리스트 생성
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 양방향 간선과 성공 확률 저장
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            double probability = succProb[i];
            graph.get(a).add(new Edge(b, probability));
            graph.get(b).add(new Edge(a, probability));
        }
        // 각 정점까지의 최대 성공 확률 저장
        double[] best = new double[n];
        // 출발 정점의 초기 성공 확률
        best[start_node] = 1.0;
        // 성공 확률이 높은 순서대로 꺼내는 최대 힙
        PriorityQueue<State> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b.probability, a.probability)
        );
        // 출발 정점 추가
        pq.offer(new State(start_node, 1.0));
        // 최대 성공 확률을 기준으로 다익스트라 탐색
        while (!pq.isEmpty()) {
            State current = pq.poll();
            // 이미 더 높은 확률의 경로가 존재하면 생략
            if (current.probability < best[current.node]) {
                continue;
            }
            // 도착 정점의 최대 성공 확률 확정
            if (current.node == end_node) {
                return current.probability;
            }
            // 현재 정점과 연결된 모든 간선 확인
            for (Edge next : graph.get(current.node)) {
                // 현재 누적 확률과 다음 간선의 확률 곱셈
                double newProbability = current.probability * next.probability;
                // 더 높은 성공 확률을 발견한 경우
                if (newProbability > best[next.to]) {
                    best[next.to] = newProbability;
                    pq.offer(new State(next.to, newProbability));
                }
            }
        }
        // 도착 정점까지 이동할 수 없는 경우
        return 0.0;
    }
}