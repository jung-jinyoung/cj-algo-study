/*
 풀이 전략
 - 도로는 양방향이므로 각 도로를 양쪽 마을의 인접 리스트에 저장한다.
 - 1번 마을을 출발점으로 설정하여 다익스트라를 수행한다.
 - 우선순위 큐에서 누적 시간이 작은 경로부터 확인하고,
   더 짧은 경로를 발견하면 해당 마을의 거리와 큐를 갱신한다.
 - 같은 두 마을 사이에 도로가 여러 개 있어도 모두 저장한다.
   거리 갱신 조건을 통해 더 저렴한 경로가 선택된다.
 - 계산된 최단 시간이 K 이하인 마을의 개수를 반환한다.
   1번 마을의 거리는 0이므로 출발 마을도 개수에 포함된다.

 시간복잡도: O(V + E log(E + 1))
 - V는 마을 수, E는 road의 길이이다.
 - 양방향 저장으로 간선 항목은 2E개가 되지만 차수는 O(E)이다.
 - 큐를 이용한 탐색에 O(E log(E + 1)),
   마지막으로 배달 가능한 마을을 세는 데 O(V)가 필요하다.
 */

import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        ArrayList<ArrayList<int []>> graph = new ArrayList<>();
        
        for(int i = 0;i<=N;i++)
        {
            graph.add(new ArrayList<>());
        }
        for (int []edge : road){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            //양방향 도로
            graph.get(u).add(new int[]{v,w});
            graph.get(v).add(new int[]{u,w});
        }
        int [] dist = new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        
        //출발 점은 1번
        dist[1] =0;
        
        PriorityQueue<int []>pq = new PriorityQueue<>(
        (a,b)->Integer.compare(a[0],b[0])
        );
        pq.offer(new int[]{0,1});
        
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int cost = current[0];
            int node = current[1];
            
            if(cost>dist[node]){
                continue;
            }
            for(int [] next:graph.get(node)){
                int nextNode = next[0];
                int weight = next[1];
                int newCost= cost+weight;
                
                //더 짧은 경로를 찾았을때 갱신
                if(newCost < dist[nextNode]){
                    dist[nextNode] = newCost;
                    pq.offer(new int[]{newCost,nextNode});
                }
            }
        }
        // K이하로 도착 가능한 마을 세기
        int answer = 0;
        
        for(int i = 1;i<=N;i++)
        {
            if(dist[i]<=K){
                answer++;
            }
        }
    

        return answer;
    }
}