/*
   문제 유형: 다익스트라 알고리즘과 최소 힙으로 모든 노드까지의 최단거리를 구해 신호 전달 시간 계산
   문제2 : <LeetCode> - 743. Network Delay Time
   난이도: Medium
*/

/*
   전략

   1. n개의 노드와 times 배열에 저장된 신호 전달 정보를 이용해 최소 전달 시간을 구한다.
   -> n = 전체 노드 개수
   -> times[i] = {출발 노드, 도착 노드, 전달 시간}
   -> k = 신호를 처음 보내는 시작 노드
   2. 그래프를 인접 리스트 형태로 구성한다.
   -> graph.get(u) = u번 노드에서 이동할 수 있는 노드 및 전달 시간 목록
   3. 방향 그래프이므로 times에 주어진 출발 노드에서 도착 노드 방향으로만 저장한다.
   -> u에서 v로 이동 가능
   -> v에서 u로 이동 가능한지는 별도 간선 정보가 필요
   4. 시작 노드에서 각 노드까지의 최단거리를 저장할 dist 배열을 만든다.
   -> Integer.MAX_VALUE = 아직 신호가 도달하지 않은 상태
   5. 시작 노드 k의 거리를 0으로 초기화한다.
   6. 전달 시간이 가장 짧은 노드부터 탐색하기 위해 PriorityQueue를 생성한다.
   -> {노드 번호, 시작 노드부터의 전달 시간} 저장
   -> 전달 시간이 작은 노드부터 꺼내는 최소 힙
   7. 시작 노드 k와 전달 시간 0을 PriorityQueue에 추가한다.
   8. PriorityQueue가 빌 때까지 가장 짧은 전달 시간을 가진 노드를 꺼내 탐색한다.
   9. 꺼낸 전달 시간이 기존 최단거리보다 크면 건너뛴다.
   -> 이미 더 짧은 경로가 발견된 오래된 큐 항목
   10. 현재 노드에서 신호를 보낼 수 있는 모든 인접 노드를 확인한다.
   11. 현재 노드를 거쳐 신호를 전달하는 새로운 시간을 계산한다.
   -> 새로운 시간 = 현재 노드까지의 최단시간 + 간선 전달 시간
   12. 새로운 시간이 기존 최단시간보다 작으면 dist 배열을 갱신한다.
   -> 더 빠르게 신호를 전달할 수 있는 경로 발견
   13. 갱신된 노드와 새로운 전달 시간을 PriorityQueue에 추가한다.
   14. 모든 노드의 최단거리 탐색이 끝나면 dist 배열을 확인한다.
   15. 아직 신호가 도달하지 않은 노드가 있다면 -1을 반환한다.
   -> dist[i] == Integer.MAX_VALUE
   16. 모든 노드에 신호가 도달했다면 최단시간 중 가장 큰 값을 반환한다.
   -> 가장 늦게 신호를 받는 노드의 시간 = 전체 신호 전달 완료 시간
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 각 노드에서 이동할 수 있는 노드 및 전달 시간 저장
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 방향 그래프의 간선 정보 저장
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph.get(u).add(new int[]{v, w});
        }
        // 시작 노드에서 각 노드까지의 최단시간 저장
        int[] dist = new int[n + 1];
        // 모든 거리를 무한대로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 시작 노드까지의 전달 시간은 0
        dist[k] = 0;
        // 전달 시간이 가장 짧은 노드를 먼저 꺼내는 최소 힙
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        // 시작 노드와 전달 시간 0을 큐에 추가
        queue.offer(new int[]{k, 0});
        // 탐색할 노드가 남아 있는 동안 반복
        while (!queue.isEmpty()) {
            // 현재까지 전달 시간이 가장 짧은 노드 꺼내기
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];
            // 이미 더 짧은 경로를 발견한 경우 건너뛰기
            if (distance > dist[node]) {
                continue;
            }
            // 현재 노드에서 이동 가능한 모든 노드 확인
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int weight = next[1];
                // 현재 노드를 거쳐 이동하는 새로운 전달 시간 계산
                int newDistance = distance + weight;
                // 기존 최단시간보다 짧으면 갱신
                if (newDistance < dist[nextNode]) {
                    dist[nextNode] = newDistance;
                    // 갱신된 노드와 최단시간을 큐에 추가
                    queue.offer(new int[]{nextNode, newDistance});
                }
            }
        }
        // 모든 노드에 신호가 도달하는 데 필요한 시간
        int answer = 0;
        // 노드 번호는 1부터 n까지 사용
        for (int i = 1; i <= n; i++) {
            // 신호가 도달하지 못한 노드가 있는 경우
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            // 전체 노드의 최단시간 중 최댓값 확인
            if (dist[i] > answer) {
                answer = dist[i];
            }
        }
        return answer;
    }
}