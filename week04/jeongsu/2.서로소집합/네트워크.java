/*
 풀이 전략
 - 컴퓨터마다 별도 집합을 만들고 parent와 size를 초기화한다.
 - computers[i][j]가 1이면 두 컴퓨터의 집합을 합친다.
 - 자기 자신과의 연결 및 같은 연결의 중복 처리를 피하기 위해
   j는 i+1부터 확인한다.
 - 직접 연결된 컴퓨터들을 합치면 간접 연결된 컴퓨터도
   같은 대표를 갖는 하나의 집합으로 묶인다.
 - 모든 연결을 처리한 뒤 parent[i] == i인 컴퓨터의 수를 센다.
   각 집합에는 대표가 하나씩 있으므로 이 값이 네트워크 수이다.

 시간복잡도: O(N² × α(N))
 - N은 컴퓨터 수이다.
 - 연결 행렬의 절반을 확인하므로 검사 횟수는 O(N²)이다.
 - 연결된 쌍마다 분할상환 O(α(N))인 union을 수행한다.
 - 대표 개수를 세는 마지막 반복문은 O(N)이다.
 */

class Solution {
    int [] parent;
    int [] size ;
    public int solution(int n, int[][] computers) {
        parent = new int[n];
        size = new int[n];
        
        //처음에는 각 컴퓨터가 별도 그룹
        for(int i = 0;i<n;i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
        //연결된 컴퓨터의 그룹 합치기
        for(int i =0;i<n;i++){
            for(int j = i+1;j<n;j++){
                if(computers[i][j] ==1){
                    union(i,j);
                }
            }
        }
        //그룹 대표의 개수 세기
        int answer = 0;
        
        for(int i = 0;i<n;i++){
            if(parent[i] ==i){
                answer++;
            }
        }
        return answer;
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
        }
        else{
            parent[rootB] = rootA;
            size[rootA] +=size[rootB];
        }
    }
}