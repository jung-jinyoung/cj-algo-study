/*
 풀이 전략
 - 처음에는 각 정점이 자기 자신을 대표로 갖는 별도 집합으로 시작한다.
 - 모든 간선을 순회하면서 양 끝 정점이 속한 집합을 union으로 합친다.
 - find는 부모를 따라가 집합의 대표를 찾는다.
   찾은 대표를 부모로 다시 저장하는 경로 압축을 적용한다.
 - union에서는 작은 집합을 큰 집합 아래에 연결한다.
   이를 위해 대표별 집합 크기를 size 배열로 관리한다.
 - 모든 간선을 처리한 뒤 source와 destination의 대표를 비교한다.
   대표가 같으면 직접 또는 간접적으로 연결되어 있으므로 true이다.

 시간복잡도: O(N + E × α(N))
 - N은 정점 수, E는 간선 수이다.
 - parent와 size 초기화에 O(N)이 필요하다.
 - 경로 압축과 크기 기준 합치기를 함께 사용하므로
   find와 union의 연산당 분할상환 시간은 O(α(N))이다.
 */

class Solution {
    int [] parent;
    int [] size;
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        
        parent = new int[n];
        size = new int[n];

        //처음에는 자기자신이 대표
        for(int i = 0;i<n;i++){
            parent[i] = i;
            size[i] = 1;
        }
        //간선으로 연결된 그룹 합치기
        for (int [] edge:edges){
            union(edge[0],edge[1]);
        }
        //대표가 같으면 연결되어 있음
        return find(source) == find(destination);
    }
    //x가 속한 그룹의 대표 찾기
    int find(int x){
        if(parent[x]!=x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    //두 그룹 합치기
    void union(int a,int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB){
            return;
        }
        if(size[rootA]<size[rootB]){
            parent[rootA] = rootB;
            size[rootB] +=size[rootA];
        }else{
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }
}
