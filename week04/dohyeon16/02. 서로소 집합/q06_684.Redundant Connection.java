/*
   문제 유형: 서로소 집합(Union-Find)으로 정점의 연결 관계를 관리해 사이클을 만드는 중복 간선 찾기
   문제6 : <LeetCode> - 684. Redundant Connection
   난이도: Medium
*/

/*
   전략

   1. edges 배열에 저장된 간선 정보를 이용해 사이클을 만드는 중복 간선을 찾는다.
   -> edges[i] = {연결된 두 정점}
   -> 정점 번호는 1부터 n까지 사용
   -> n = edges.length
   2. Union-Find 알고리즘으로 연결된 정점들을 하나의 집합으로 관리한다.
   -> Union = 서로 다른 두 집합을 하나로 합치는 연산
   -> Find = 특정 정점이 속한 집합의 대표 정점을 찾는 연산
   3. 각 정점의 부모 정보를 저장할 parent 배열을 생성한다.
   -> parent[i] = i번 정점의 부모 정점
   4. 각 집합의 크기를 저장할 size 배열을 생성한다.
   -> size[i] = i번 정점이 대표인 집합의 크기
   5. 모든 정점이 처음에는 독립된 집합이므로 자기 자신을 부모로 설정한다.
   -> parent[i] = i
   -> size[i] = 1
   6. edges 배열의 간선을 입력된 순서대로 하나씩 확인한다.
   7. 각 간선의 두 정점을 union 함수에 전달한다.
   8. union 함수에서 find를 이용해 두 정점의 대표 정점을 찾는다.
   9. 두 대표 정점이 같으면 이미 같은 집합에 속한 상태이다.
   -> 두 정점 사이에 기존 경로가 존재하는 상태
   -> 새로운 간선을 추가하면 사이클 발생
   10. 사이클이 발생하면 union 함수에서 false를 반환한다.
   11. 대표 정점이 다르면 두 집합을 하나로 합친다.
   -> 크기가 작은 집합을 큰 집합에 합치는 Union by Size
   12. 두 집합을 정상적으로 합치면 true를 반환한다.
   13. find 함수에서는 경로 압축으로 부모를 대표 정점으로 변경한다.
   -> 이후 대표 정점을 더 빠르게 찾기 위한 최적화
   14. union 결과가 false이면 현재 간선이 사이클을 만드는 중복 간선이다.
   15. 해당 간선을 정답으로 반환한다.
   -> 입력 순서상 사이클을 이루는 간선 중 마지막 간선
*/

class Solution {
    private int[] parent;
    private int[] size;
    public int[] findRedundantConnection(int[][] edges) {
        // 전체 정점의 개수
        int n = edges.length;
        // 각 정점의 부모와 집합 크기 저장
        parent = new int[n + 1];
        size = new int[n + 1];
        // 모든 정점을 독립된 집합으로 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        // 입력된 순서대로 간선 확인
        for (int[] edge : edges) {
            // 이미 연결된 두 정점이면 사이클 발생
            if (!union(edge[0], edge[1])) {
                return edge;
            }
        }
        // 문제 조건상 중복 간선이 반드시 존재
        return new int[0];
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
    private boolean union(int a, int b) {
        // 두 정점의 대표 정점 확인
        int rootA = find(a);
        int rootB = find(b);
        // 이미 같은 집합이면 사이클 발생
        if (rootA == rootB) {
            return false;
        }
        // 크기가 작은 집합을 큰 집합에 합치기
        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
        // 서로 다른 두 집합을 정상적으로 합친 경우
        return true;
    }
}