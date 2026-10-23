/**
     * =====================================================================
     * [알고리즘 전략 및 복잡도 정리 / 회고]
     * =====================================================================
     * 1. 핵심 전략
     *    - 서로소 집합 / Union-Find 알고리즘 활용
     *    - 인접 행렬 형태의 네트워크 연결 정보(computers)를 순회하며 연결된 컴퓨터들을 union 연산으로 그룹화
     *    - find 연산 시 경로 압축(Path Compression) 기법을 적용하여 트리 높이 최소화
     *    - 모든 컴퓨터의 최종 루트 노드를 HashSet에 담아 중복을 제거하고 독립된 네트워크 개수 산출
     * 
     * 2. 시간 복잡도: O(n^2) (실질적으로 거의 선형 시간)
     *    - n: 컴퓨터 개수
     *    - 인접 행렬 완전 탐색: O(n^2)
     *    - 경로 압축 최적화가 적용된 Union-Find 연산은 실질적으로 거의 상수 시간에 수렴하므로 전체적으로 O(n^2)에 가까운 효율성 확보
     * 
     * 3. 공간 복잡도: O(n)
     *    - 부모 노드 추적을 위한 parent 배열 및 루트 개수 저장을 위한 HashSet 공간: O(n)
     * =====================================================================
     */

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class Solution {
    int[] parent;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        parent = new int[n];
        for (int i = 0 ; i < n ; i++){
            parent[i] = i;
        }
        
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++) {
                if (computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < n ; i++) {
            set.add(find(parent[i]));
        }
        
        return set.size();
    }
    
    private int find(int a) {
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