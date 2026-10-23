/*
   문제 유형: BFS(너비 우선 탐색)로 한 글자씩 다른 단어를 탐색해 목표 단어까지의 최소 변환 횟수 계산
   문제2 : <프로그래머스> - 단어 변환
   난이도: Level 3
*/

/*
   전략

   1. begin에서 시작해 target으로 변환하는 최소 횟수를 구한다.
   -> 한 번에 알파벳 한 글자만 변경 가능
   -> words 배열에 포함된 단어로만 변경 가능
   2. BFS(너비 우선 탐색)를 사용해 변환 횟수가 적은 단어부터 순서대로 탐색한다.
   -> BFS = 가까운 단계부터 탐색하므로 최소 변환 횟수 계산에 적합
   3. 이미 확인한 단어를 구분하기 위해 boolean 배열 visited를 만든다.
   -> true = 이미 방문한 단어
   -> false = 아직 방문하지 않은 단어
   4. 탐색할 단어를 저장할 Queue를 만든다.
   -> Queue = 먼저 들어온 단어를 먼저 꺼내는 FIFO 구조
   5. 시작 단어 begin을 Queue에 추가하고 변환 횟수 steps를 0으로 초기화한다.
   6. Queue에 탐색할 단어가 남아 있는 동안 BFS를 반복한다.
   7. 현재 Queue에 저장된 단어의 개수를 size에 저장한다.
   -> size = 현재 변환 단계에서 확인할 단어의 개수
   8. Queue에서 단어를 꺼내 target과 같은지 확인한다.
   -> target과 같으면 현재 변환 횟수 steps 반환
   9. words 배열에서 아직 방문하지 않은 단어를 하나씩 확인한다.
   10. 현재 단어와 다음 단어의 각 위치에 있는 알파벳을 비교한다.
   -> charAt(i) = 문자열에서 i번째 알파벳 확인
   -> diff = 서로 다른 알파벳의 개수
   11. 서로 다른 알파벳이 정확히 1개이면 변환 가능한 단어로 판단한다.
   -> diff == 1이면 한 번의 변환으로 이동 가능
   12. 변환 가능한 단어를 방문 처리하고 Queue에 추가한다.
   -> visited[i] = true
   13. 현재 변환 단계의 모든 단어를 확인하면 steps를 1 증가시킨다.
   14. 다음 변환 단계에서도 같은 과정을 반복한다.
   15. Queue가 빌 때까지 target을 찾지 못하면 0을 반환한다.
   -> target으로 변환할 수 없는 경우
*/

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // 각 단어의 방문 여부를 저장
        boolean[] visited = new boolean[words.length];
        // BFS 탐색에 사용할 큐
        Queue<String> queue = new ArrayDeque<>();
        // 시작 단어를 큐에 추가
        queue.offer(begin);
        // 단어를 변환한 횟수
        int steps = 0;
        // 탐색할 단어가 남아 있는 동안 반복
        while (!queue.isEmpty()) {
            // 현재 변환 단계에서 확인할 단어의 개수
            int size = queue.size();
            // 현재 변환 단계에 해당하는 단어만 탐색
            for (int i = 0; i < size; i++) {
                // 큐에서 가장 앞에 있는 단어 꺼내기
                String current = queue.poll();
                // 목표 단어에 도달하면 변환 횟수 반환
                if (current.equals(target)) {
                    return steps;
                }
                // words 배열의 모든 단어 확인
                for (int j = 0; j < words.length; j++) {
                    // 이미 방문한 단어는 건너뛰기
                    if (visited[j]) {
                        continue;
                    }
                    // 현재 단어와 비교할 단어의 알파벳 차이 개수
                    int diff = 0;
                    // 각 위치에 있는 알파벳 비교
                    for (int k = 0; k < current.length(); k++) {
                        if (current.charAt(k) != words[j].charAt(k)) {
                            diff++;
                        }
                    }
                    // 서로 다른 알파벳이 정확히 1개인지 확인
                    if (diff == 1) {
                        // 변환 가능한 단어를 방문 처리
                        visited[j] = true;
                        // 다음 변환 단계에서 탐색할 단어 추가
                        queue.offer(words[j]);
                    }
                }
            }
            // 현재 단계의 탐색이 끝났으므로 변환 횟수 증가
            steps++;
        }
        // 목표 단어로 변환할 수 없는 경우
        return 0;
    }
}