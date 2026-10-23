/**
     * =====================================================================
     * [알고리즘 전략 및 복잡도 정리 / 회고]
     * =====================================================================
     * 1. 핵심 전략
     *    - 서로소 집합 / Union-Find 알고리즘 활용
     *    - 그래프의 모든 간선을 순회하며 union 연산으로 정점들을 그룹화
     *    - find 연산 시 경로 압축(Path Compression) 기법을 적용하여 트리의 높이를 최소화
     *    - 출발지(source)와 도착지(destination)의 루트 노드가 같은지 비교하여 경로 존재 여부 판단
     * 
     * 2. 시간 복잡도: (사실상 O(N + E))
     *    - N: 정점 개수, E: 간선 개수
     *    - 부모 배열 초기화 연산: O(N)
     *    - 간선 순회 및 Union-Find 연산: O(E * α(N)) (α(N)는 사실상 거의 상수 시간에 수렴)
     *    - 경로 압축 최적화를 통해 거의 선형 시간에 가까운 압도적인 효율성 확보
     * 
     * 3. 공간 복잡도: O(N)
     *    - 부모 노드 추적을 위한 parent 배열 저장 공간: O(N)
     * =====================================================================
     */


class Solution {
    public int[] parent;
    
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        parent = new int[n];
        for (int i = 0 ; i < n ; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            union(u, v);
        }

        if (find(source) == find(destination)) {
            return true;
        } else{
            return false;
        }
    }

    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = parent[rootA];
        }
        return;
    }
}