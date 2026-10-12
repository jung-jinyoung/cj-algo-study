/*
   문제 유형: 잘라서 정렬 후 특정 값 찾기
   문제1 : <프로그래머스> - K번째수
   난이도: Level 1
*/

/*
   전략

   1. commands의 개수만큼 정답을 저장할 answer 배열을 만든다.
   -> 명령 하나마다 결과 숫자 하나가 나오기 때문에 commands의 길이와 answer의 길이는 같다.
   2. commands를 처음부터 끝까지 하나씩 확인한다.
   3. 각 command에서 i, j, k 값을 가져온다.
   -> command[0] = 자르기 시작 위치 i
   -> command[1] = 자르기 끝 위치 j
   -> command[2] = 정렬 후 선택할 위치 k
   4. 문제의 위치는 1부터 시작하지만 Java 배열의 인덱스는 0부터 시작하므로 시작 위치에서 1을 뺀다.
   5. Arrays.copyOfRange()를 사용해 array의 i번째부터 j번째까지 잘라 새로운 배열을 만든다.
   6. 잘라낸 배열을 Arrays.sort()로 오름차순 정렬한다.
   7. 정렬된 배열에서 k번째 숫자를 찾아 answer에 저장한다.
   -> Java 배열은 0부터 시작하므로 k에서도 1을 뺀 위치를 사용한다.
   8. 모든 command 처리가 끝나면 answer 배열을 반환한다.
*/

import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        // 명령 개수만큼 결과를 저장할 배열 생성
        int[] answer = new int[commands.length];
        // commands를 처음부터 끝까지 하나씩 확인
        for (int i = 0; i < commands.length; i++) {
            // 자르기 시작 위치
            int start = commands[i][0] - 1;
            // 자르기 끝 위치
            int end = commands[i][1];
            // 정렬 후 선택할 위치
            int k = commands[i][2] - 1;
            // array의 start부터 end 이전까지 잘라 새로운 배열 생성
            int[] sliced = Arrays.copyOfRange(array, start, end);
            // 잘라낸 배열을 오름차순으로 정렬
            Arrays.sort(sliced);
            // 정렬된 배열에서 k번째 숫자를 answer에 저장
            answer[i] = sliced[k];
        }
        return answer;
    }
}