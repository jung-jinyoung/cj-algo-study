/**
     * =====================================================================
     * [알고리즘 전략 및 복잡도 정리 / 회고]
     * =====================================================================
     * 1. 핵심 전략
     *    - 서로소 집합/ Union-Find 알고리즘 활용
     *    - 그래프의 간선들을 순회하며 이미 같은 집합(루트)에 속한 두 노드(a, b)인 경우 사이클을 형성하는 중복 간선으로 판단
     *    - find 연산 시 경로 압축(Path Compression) 기법을 적용하여 트리 높이 최소화
     *    - 중복 간선 발견 즉시 해당 간선 반환, 그 외의 경우 union 연산으로 집합 병합
     * 
     * 2. 시간 복잡도: O(N) (실질적으로 거의 선형 시간)
     *    - N: 정점 및 간선 개수
     *    - 경로 압축 최적화가 적용된 Union-Find 연산은 실질적으로 거의 상수 시간에 수렴하므로 전체적으로 O(N)에 가까운 효율성 확보
     * 
     * 3. 공간 복잡도: O(N)
     *    - 부모 노드 추적을 위한 parent 배열 저장 공간: O(N)
     * =====================================================================
     */

class Solution {
    int[] parent;
    public int[] findRedundantConnection(int[][] edges) {
        int[] answer = new int[2];
        int n = edges.length;
        parent = new int[n+1];
        for (int i = 1 ; i <= n ; i++) {
            parent[i] = i;
        }

        for (int[]edge : edges) {
            int a = edge[0];
            int b = edge[1];

            if (find(a) == find(b)){
                answer[0] = a;
                answer[1] = b;
                break;
            }
            union(a, b);
        }


        return answer;
        
    }

    private int find(int a){
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = parent[rootA];
        }
    }
}