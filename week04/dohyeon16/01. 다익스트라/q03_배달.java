/*
   문제 유형: 다익스트라 알고리즘과 최소 힙으로 각 마을까지의 최단거리를 구해 K시간 이내 배달 가능한 마을 개수 계산
   문제3 : <프로그래머스> - 배달
   난이도: Level 2
*/

/*
   전략

   1. N개의 마을과 road 배열에 저장된 도로 정보를 이용해 배달 가능한 마을의 개수를 구한다.
   -> N = 전체 마을 개수
   -> road[i] = {출발 마을, 도착 마을, 이동 시간}
   -> K = 배달 가능한 최대 시간
   2. 그래프를 인접 리스트 형태로 구성한다.
   -> graph.get(a) = a번 마을과 연결된 마을 및 이동 시간 목록
   3. 도로는 양방향이므로 간선 정보를 양쪽 마을에 모두 저장한다.
   -> a에서 b로 이동 가능
   -> b에서 a로 이동 가능
   4. 1번 마을에서 각 마을까지의 최단거리를 저장할 dist 배열을 만든다.
   -> Integer.MAX_VALUE = 아직 최단거리를 찾지 못한 상태
   5. 시작 마을인 1번 마을의 거리를 0으로 초기화한다.
   6. 이동 시간이 가장 짧은 마을부터 탐색하기 위해 PriorityQueue를 생성한다.
   -> {마을 번호, 시작 마을부터의 이동 시간} 저장
   -> 이동 시간이 작은 마을부터 꺼내는 최소 힙
   7. 시작 마을 1번과 이동 시간 0을 PriorityQueue에 추가한다.
   8. PriorityQueue가 빌 때까지 가장 짧은 이동 시간을 가진 마을을 꺼내 탐색한다.
   9. 꺼낸 이동 시간이 기존 최단거리보다 크면 건너뛴다.
   -> 이미 더 짧은 경로가 발견된 오래된 큐 항목
   10. 현재 마을과 연결된 모든 인접 마을을 확인한다.
   11. 현재 마을을 거쳐 이동하는 새로운 시간을 계산한다.
   -> 새로운 시간 = 현재 마을까지의 최단시간 + 도로 이동 시간
   12. 새로운 시간이 기존 최단시간보다 작으면 dist 배열을 갱신한다.
   -> 더 빠르게 배달할 수 있는 경로 발견
   13. 갱신된 마을과 새로운 이동 시간을 PriorityQueue에 추가한다.
   14. 모든 최단거리 탐색이 끝나면 1번 마을부터 N번 마을까지 확인한다.
   15. 최단거리가 K 이하인 마을을 발견하면 answer를 1 증가시킨다.
   -> dist[i] <= K이면 배달 가능한 마을
   -> 출발 마을인 1번 마을도 배달 가능한 마을에 포함
   16. 배달 가능한 마을의 총 개수를 반환한다.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(int N, int[][] road, int K) {
        // 각 마을과 연결된 마을 및 이동 시간 저장
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        // 양방향 도로의 간선 정보 저장
        for (int[] edge : road) {
            int a = edge[0];
            int b = edge[1];
            int time = edge[2];
            graph.get(a).add(new int[]{b, time});
            graph.get(b).add(new int[]{a, time});
        }
        // 1번 마을에서 각 마을까지의 최단거리 저장
        int[] dist = new int[N + 1];
        // 모든 거리를 무한대로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 시작 마을까지의 이동 시간은 0
        dist[1] = 0;
        // 이동 시간이 가장 짧은 마을을 먼저 꺼내는 최소 힙
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        // 시작 마을과 이동 시간 0을 큐에 추가
        queue.offer(new int[]{1, 0});
        // 탐색할 마을이 남아 있는 동안 반복
        while (!queue.isEmpty()) {
            // 현재까지 이동 시간이 가장 짧은 마을 꺼내기
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];
            // 이미 더 짧은 경로를 발견한 경우 건너뛰기
            if (distance > dist[node]) {
                continue;
            }
            // 현재 마을과 연결된 모든 마을 확인
            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int time = next[1];
                // 현재 마을을 거쳐 이동하는 새로운 시간 계산
                int newDistance = distance + time;
                // 기존 최단시간보다 짧으면 갱신
                if (newDistance < dist[nextNode]) {
                    dist[nextNode] = newDistance;
                    // 갱신된 마을과 최단시간을 큐에 추가
                    queue.offer(new int[]{nextNode, newDistance});
                }
            }
        }
        // 배달 가능한 마을의 개수
        int answer = 0;
        // 1번 마을부터 N번 마을까지 최단시간 확인
        for (int i = 1; i <= N; i++) {
            // K시간 이내 배달 가능한 마을 확인
            if (dist[i] <= K) {
                answer++;
            }
        }
        return answer;
    }
}