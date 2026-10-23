/*
 풀이 전략
 - 정점 번호가 1부터 N까지이므로 parent와 size를 N+1 크기로 만든다.
 - 입력 순서대로 간선의 양 끝 정점이 속한 집합을 확인한다.
 - 대표가 다르면 두 집합을 합쳐 연결 관계를 반영한다.
 - 대표가 같으면 이미 두 정점 사이에 경로가 있다는 뜻이다.
   이때 간선을 추가하면 사이클이 생기므로 해당 간선을 반환한다.
 - 반드시 union을 수행하기 전에 같은 집합인지 검사한다.
 - 이 문제는 트리에 간선 하나를 추가한 구조이므로
   사이클이 처음 완성되는 간선이 사이클 내에서 입력상 가장 늦은 간선이다.

 시간복잡도: O(N × α(N))
 - 이 문제에서는 정점 수와 간선 수가 모두 N이다.
 - 초기화에 O(N), 간선별 find와 union에 총 O(N × α(N))이 필요하다.
 - 경로 압축과 크기 기준 합치기를 함께 사용한다.
 */
class Solution {
    int [] parent;
    int [] size;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        //노드 번호는 1~n
        parent = new int[n+1];
        size = new int[n+1];

        for (int i = 0;i<=n;i++){
            parent[i] = i;
            size[i] = 1;
        }

        for(int [] edge : edges){
            int a= edge[0];
            int b = edge[1];

            //이미 연결되어 있는데 다시 연결하면 사이클
            if(find(a) == find(b)){
                return edge;
            }
            union(a,b);
        }
        //문제 조건상 위에서 반드시 답을 찾는다.
        return new int[0];
    }
    int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int a,int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB){
            return;
        }
        if(size[rootA]<size[rootB]){
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else{
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }
}