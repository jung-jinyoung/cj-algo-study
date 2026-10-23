/*
 풀이 전략
 - edges의 각 간선을 양방향으로 저장하여 인접 리스트를 만든다.
    각 리스트 원소에는 {이웃 정점, 이동 비용}을 담는다.
 - dist에는 출발점에서 각 정점까지 현재 발견한 최소 비용을 저장한다.
    출발점은 0, 나머지는 Integer.MAX_VALUE로 초기화한다.
 - 우선순위 큐에는 {누적 비용, 정점}을 넣고
    누적 비용이 작은 항목부터 꺼낸다.
 - 꺼낸 비용이 dist에 저장된 값보다 크면 오래된 경로이므로 건너뛴다.
 - 이웃으로 이동하는 비용이 기존 거리보다 작을 때만
    dist를 갱신하고 새로운 경로를 큐에 넣는다.
 - 탐색이 끝나면 문제의 반환 형식에 맞춰 거리 배열을 리스트로 옮긴다.
 
 시간복잡도: O(V + E log(E + 1))
 - V는 정점 수, E는 간선 수이다.
 - 그래프 구성과 거리 초기화에 O(V + E)가 필요하다.
 - 거리 갱신으로 큐에 들어가는 항목은 전체 O(E)개이며
    큐 삽입과 삭제에는 각각 O(log(E + 1))이 필요하다.
 */


import java.util.*;
class Solution {
    public ArrayList<Integer> dijkstra(int V, int[][] edges, int src) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i = 0;i<V;i++)
        {
            graph.add(new ArrayList<>());
        }
        for(int [] edge:edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            graph.get(u).add(new int[]{v,w});
            graph.get(v).add(new int[]{u,w});
        }
        
        int [] dist = new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a,b)-> Integer.compare(a[0],b[0])
            );
        pq.offer(new int[]{0,src});
        
        while(!pq.isEmpty()){
            int [] current = pq.poll();
            int cost = current[0];
            int node = current[1];
            
            if(cost>dist[node]){
                continue;
            }
            for (int [] next: graph.get(node)){
                int nextNode = next[0];
                int weight = next [1];
                int newCost = cost+weight;
                
                if(newCost<dist[nextNode]){
                    dist[nextNode] = newCost;
                    pq.offer(new int[]{newCost,nextNode});
                }
            }
        }
        for (int distance : dist){
            answer.add(distance);
        }
        return answer;
        
    }
}