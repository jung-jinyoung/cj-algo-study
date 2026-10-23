/*
   문제 유형: 서로소 집합(Union-Find)으로 연결된 컴퓨터들을 하나의 집합으로 합쳐 독립된 네트워크 개수 계산
   문제5 : <프로그래머스> - 네트워크
   난이도: Level 3
*/

/*
   전략

   1. n개의 컴퓨터와 computers 배열에 저장된 연결 정보를 이용해 네트워크 개수를 구한다.
   -> n = 전체 컴퓨터 개수
   -> computers[i][j] = 1이면 i번 컴퓨터와 j번 컴퓨터가 연결된 상태
   -> computers[i][j] = 0이면 직접 연결되지 않은 상태
   2. Union-Find 알고리즘으로 연결된 컴퓨터들을 하나의 집합으로 관리한다.
   -> Union = 서로 다른 두 집합을 하나로 합치는 연산
   -> Find = 특정 컴퓨터가 속한 집합의 대표 컴퓨터를 찾는 연산
   3. 각 컴퓨터의 부모 정보를 저장할 parent 배열을 생성한다.
   -> parent[i] = i번 컴퓨터의 부모 컴퓨터
   4. 각 집합의 크기를 저장할 size 배열을 생성한다.
   -> size[i] = i번 컴퓨터가 대표인 집합의 크기
   5. 처음에는 모든 컴퓨터가 독립된 네트워크이므로 자기 자신을 부모로 설정한다.
   -> parent[i] = i
   -> size[i] = 1
   6. 처음 네트워크 개수는 전체 컴퓨터 수와 같으므로 answer를 n으로 초기화한다.
   7. computers 배열을 확인하면서 연결된 두 컴퓨터를 찾는다.
   -> computers[i][j] == 1이면 연결된 상태
   -> j = i + 1부터 탐색해 중복 연결 정보 제외
   8. 연결된 두 컴퓨터를 발견하면 union 함수를 호출한다.
   9. union 함수에서 find를 이용해 두 컴퓨터의 대표 컴퓨터를 찾는다.
   10. 두 대표 컴퓨터가 같으면 이미 같은 네트워크이므로 false를 반환한다.
   11. 대표 컴퓨터가 다르면 두 집합을 하나로 합친다.
   -> 크기가 작은 집합을 큰 집합에 합치는 Union by Size
   12. 집합을 성공적으로 합치면 true를 반환한다.
   13. union 결과가 true이면 독립된 네트워크가 하나 줄어들므로 answer를 1 감소시킨다.
   14. find 함수에서는 경로 압축으로 부모를 대표 컴퓨터로 변경한다.
   -> 이후 대표 컴퓨터를 더 빠르게 찾기 위한 최적화
   15. 모든 컴퓨터의 연결 관계를 확인한 뒤 남아 있는 네트워크 개수를 반환한다.
*/

class Solution {
    private int[] parent;
    private int[] size;
    public int solution(int n, int[][] computers) {
        // 각 컴퓨터의 부모와 집합 크기 저장
        parent = new int[n];
        size = new int[n];
        // 모든 컴퓨터를 독립된 집합으로 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        // 처음에는 모든 컴퓨터가 독립된 네트워크
        int answer = n;
        // 컴퓨터 간 연결 관계 확인
        for (int i = 0; i < n; i++) {
            // 중복 확인 방지를 위해 i보다 큰 컴퓨터만 탐색
            for (int j = i + 1; j < n; j++) {
                // 두 컴퓨터가 연결된 경우
                if (computers[i][j] == 1) {
                    // 서로 다른 네트워크를 합친 경우
                    if (union(i, j)) {
                        // 독립된 네트워크 개수 1 감소
                        answer--;
                    }
                }
            }
        }
        return answer;
    }
    // 컴퓨터가 속한 집합의 대표 컴퓨터 탐색
    private int find(int node) {
        // 자기 자신이 대표 컴퓨터가 아닌 경우
        if (parent[node] != node) {
            // 경로 압축으로 부모를 대표 컴퓨터로 변경
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
    // 두 컴퓨터가 속한 집합을 하나로 합치기
    private boolean union(int a, int b) {
        // 두 컴퓨터의 대표 컴퓨터 확인
        int rootA = find(a);
        int rootB = find(b);
        // 이미 같은 네트워크에 속한 경우
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
        // 서로 다른 두 네트워크를 합친 경우
        return true;
    }
}