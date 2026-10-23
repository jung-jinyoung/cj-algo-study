/*
풀이 전략
 - times의 {출발 노드, 도착 노드, 시간}을 단방향 인접 리스트로 옮긴다.
 - 노드 번호가 1부터 n까지이므로 배열과 리스트를 n+1 크기로 만든다.
 - 출발 노드 k의 거리를 0으로 설정하고 다익스트라를 수행한다.
 - 큐에서 누적 시간이 작은 경로부터 꺼내 이웃의 거리를 갱신한다.
   이미 더 짧은 경로가 기록되어 있다면 해당 항목은 건너뛴다.
 - 탐색 후 거리가 초기값으로 남아 있는 노드가 있으면 -1을 반환한다.
 - 모든 노드에 도달했다면 최단 시간 중 최댓값을 반환한다.
   가장 늦게 도착하는 노드가 신호를 받아야 전체 전달이 완료된다.

 시간복잡도: O(V + E log(E + 1))
 - V는 노드 수, E는 times의 길이이다.
 - 그래프 구성과 배열 초기화에 O(V + E),
   우선순위 큐를 이용한 탐색에 O(E log(E + 1))이 필요하다.
 - 마지막으로 모든 노드의 거리를 확인하는 데 O(V)가 필요하다.

 */
import java.util.*;
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        //연결 정보 : {도착 노드, 소요시간}
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

        //노드가 1~n이므로 0번은 비워두고 n+1칸 생성
        for (int i= 0; i<=n;i++){
            graph.add(new ArrayList<>());
        }

        for(int [] edge: times){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph.get(u).add(new int[]{v,w});
            //단방향이므로 v->u는 추가하지 않음
        }
        //출발 노드 까지의 거리는 0
        int [] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[k] = 0;

        //{누적시간, 노드} : 시간이 작은 것 부터 꺼냄
        PriorityQueue<int[]> pq = new PriorityQueue<>(
        (a,b)->Integer.compare(a[0],b[0])
        );
        pq.offer(new int[]{0,k});

        //다익스트라
        while (!pq.isEmpty()){
            int [] current = pq.poll();
            int cost = current[0];
            int node = current[1];

            if(cost>dist[node]){
                continue;
            }

            for(int [] next: graph.get(node)){
                int nextNode = next[0];
                int weight = next[1];
                int newCost = cost+weight;

                if(newCost<dist[nextNode]){
                    dist[nextNode] = newCost;
                    pq.offer(new int[]{newCost, nextNode});
                }
            }
        }
        //모두 도달할 수 있는지 확인하고 가장 늦은 시간 구하기
        int answer = 0;

        for(int i = 1;i<=n; i++){
            if(dist[i]==Integer.MAX_VALUE){
                return -1;
            }
            answer = Math.max(answer,dist[i]);
        }
        return answer;
    }
}