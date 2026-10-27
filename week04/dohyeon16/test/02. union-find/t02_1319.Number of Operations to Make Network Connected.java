/*
   문제 유형: 서로소 집합(Union-Find)으로 연결된 네트워크 개수를 계산해 모든 컴퓨터를 연결하는 최소 케이블 이동 횟수 구하기
   문제2 : <LeetCode> - 1319. Number of Operations to Make Network Connected
   난이도: Medium
*/

/*
   전략

   1. n개의 컴퓨터와 connections 배열에 저장된 케이블 연결 정보를 확인한다.
   -> n = 전체 컴퓨터 수
   -> connections[i] = 케이블로 연결된 두 컴퓨터
   -> 컴퓨터 번호는 0부터 n-1까지 사용
   2. 모든 컴퓨터를 하나로 연결하려면 최소 n-1개의 케이블이 필요하다.
   -> 전체 컴퓨터 n개를 연결하는 최소 간선 수 = n-1
   3. 케이블 개수가 n-1개보다 적다면 연결이 불가능하므로 -1을 반환한다.
   4. Union-Find 알고리즘으로 연결된 컴퓨터들을 하나의 집합으로 관리한다.
   -> Find = 특정 컴퓨터가 속한 집합의 대표 정점 탐색
   -> Union = 서로 다른 두 집합을 하나로 병합
   5. 각 컴퓨터의 부모 정보와 집합 크기를 저장할 배열을 생성한다.
   -> parent[i] = i번 컴퓨터의 부모 정점
   -> size[i] = i번 컴퓨터가 대표인 집합의 크기
   6. 모든 컴퓨터가 처음에는 독립된 집합이므로 자기 자신을 부모로 설정한다.
   -> parent[i] = i
   -> size[i] = 1
   7. 연결된 네트워크의 개수를 저장할 components 변수를 n으로 초기화한다.
   8. connections 배열의 케이블 연결 정보를 순서대로 확인한다.
   9. find 함수를 이용해 연결된 두 컴퓨터의 대표 정점을 찾는다.
   10. 두 대표 정점이 같으면 이미 같은 네트워크에 속한 상태이다.
   -> 해당 케이블은 제거해도 연결 관계를 유지할 수 있는 여분 케이블
   -> 별도의 병합 없이 다음 간선 확인
   11. 두 대표 정점이 다르면 서로 다른 네트워크이므로 집합을 병합한다.
   -> unionRoots(rootA, rootB)
   12. 집합 병합 시 크기가 작은 집합을 큰 집합에 합친다.
   -> Union by Size로 트리 깊이 최소화
   13. 서로 다른 두 네트워크를 병합하면 components를 1 감소시킨다.
   14. find 함수에서는 경로 압축으로 부모를 대표 정점으로 변경한다.
   -> 이후 대표 정점 탐색을 빠르게 수행하기 위한 최적화
   15. 모든 연결 관계를 확인한 뒤 남아 있는 네트워크 개수를 확인한다.
   16. 네트워크가 components개라면 하나로 연결하는 데 components-1번의 이동이 필요하다.
   -> 최소 이동 횟수 = components-1
   17. 최종적으로 필요한 최소 케이블 이동 횟수를 반환한다.
*/

class Solution {
    private int[] parent;
    private int[] size;
    public int makeConnected(int n, int[][] connections) {
        // 모든 컴퓨터를 연결할 케이블이 부족한 경우
        if (connections.length < n - 1) {
            return -1;
        }
        // 각 컴퓨터의 부모와 집합 크기 저장
        parent = new int[n];
        size = new int[n];
        // 모든 컴퓨터를 독립된 집합으로 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        // 처음에는 모든 컴퓨터가 별도의 네트워크
        int components = n;
        // 연결된 두 컴퓨터의 대표 정점 확인
        for (int[] edge : connections) {
            int rootA = find(edge[0]);
            int rootB = find(edge[1]);
            // 이미 같은 네트워크이면 여분 케이블에 해당
            if (rootA == rootB) {
                continue;
            }
            // 서로 다른 네트워크를 하나로 병합
            unionRoots(rootA, rootB);
            components--;
        }
        // 남은 네트워크들을 하나로 연결할 최소 이동 횟수
        return components - 1;
    }
    // 경로 압축으로 대표 정점 탐색
    private int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
    // 이미 찾은 두 대표 정점을 집합 크기에 따라 병합
    private void unionRoots(int rootA, int rootB) {
        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }
}