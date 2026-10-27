/*
   문제 유형: 서로소 집합(Union-Find)으로 정점의 연결 관계를 관리해 두 정점 사이의 경로 존재 여부 확인
   문제4 : <LeetCode> - 1971. Find if Path Exists in Graph
   난이도: Easy
*/

/*
   전략

   1. n개의 정점과 edges 배열에 저장된 간선 정보를 이용해 두 정점의 연결 여부를 확인한다.
   -> n = 전체 정점 개수
   -> edges[i] = {연결된 두 정점}
   -> source = 출발 정점
   -> destination = 도착 정점
   2. Union-Find 알고리즘을 사용해 연결된 정점들을 하나의 집합으로 관리한다.
   -> Union = 서로 다른 두 집합을 하나로 합치는 연산
   -> Find = 특정 정점이 속한 집합의 대표 정점을 찾는 연산
   3. 각 정점의 부모 정보를 저장할 parent 배열을 생성한다.
   -> parent[i] = i번 정점의 부모 정점
   4. 각 집합의 크기를 저장할 size 배열을 생성한다.
   -> size[i] = i번 정점이 대표인 집합의 크기
   5. 처음에는 모든 정점이 서로 다른 집합이므로 자신의 부모를 자기 자신으로 설정한다.
   -> parent[i] = i
   -> size[i] = 1
   6. edges 배열의 간선을 하나씩 확인하면서 연결된 두 정점을 같은 집합으로 합친다.
   7. union 함수에서 두 정점의 대표 정점을 find 함수로 찾는다.
   8. 대표 정점이 같으면 이미 같은 집합이므로 합치지 않는다.
   9. 대표 정점이 다르면 크기가 작은 집합을 큰 집합에 합친다.
   -> 집합의 크기를 기준으로 합치는 Union by Size
   -> 트리 깊이가 불필요하게 증가하는 상황 방지
   10. 집합을 합친 뒤에는 대표 정점의 size 값을 갱신한다.
   11. find 함수에서는 부모를 따라 올라가 최종 대표 정점을 찾는다.
   12. 경로 압축(Path Compression)을 이용해 탐색한 정점의 부모를 대표 정점으로 변경한다.
   -> 이후 동일한 집합의 대표 정점을 더 빠르게 찾기 위한 최적화
   13. 모든 간선 처리가 끝나면 source와 destination의 대표 정점을 비교한다.
   14. 두 대표 정점이 같으면 연결된 상태이므로 true를 반환한다.
   15. 두 대표 정점이 다르면 연결되지 않은 상태이므로 false를 반환한다.
*/

class Solution {
    private int[] parent;
    private int[] size;
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 각 정점의 부모와 집합 크기를 저장
        parent = new int[n];
        size = new int[n];
        // 모든 정점을 독립된 집합으로 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        // 간선으로 연결된 두 정점을 같은 집합으로 합치기
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
        // 출발 정점과 도착 정점의 대표 정점 비교
        return find(source) == find(destination);
    }
    // 정점이 속한 집합의 대표 정점 탐색
    private int find(int node) {
        // 자기 자신이 대표 정점이 아닌 경우
        if (parent[node] != node) {
            // 경로 압축으로 부모를 대표 정점으로 변경
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
    // 두 정점이 속한 집합을 하나로 합치기
    private void union(int a, int b) {
        // 두 정점이 속한 집합의 대표 정점 확인
        int rootA = find(a);
        int rootB = find(b);
        // 이미 같은 집합에 속한 경우
        if (rootA == rootB) {
            return;
        }
        // 크기가 작은 집합을 큰 집합에 합치기
        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }
}