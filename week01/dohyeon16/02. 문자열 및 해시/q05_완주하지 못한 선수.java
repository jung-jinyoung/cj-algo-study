/*
   문제5 : <프로그래머스> - 완주하지 못한 선수
   난이도: Level 1
*/

import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        // 참가자 이름별 등장 횟수 저장
        HashMap<String, Integer> count = new HashMap<>();

        // 참가자 명단의 이름별 인원 수 계산
        for (String name : participant) {
            count.put(name, count.getOrDefault(name, 0) + 1);
        }

        // 완주자 명단에 포함된 이름의 인원 수 차감
        for (String name : completion) {
            count.put(name, count.get(name) - 1);
        }

        // 남은 인원 수가 1 이상인 선수 탐색
        for (String name : count.keySet()) {
            if (count.get(name) > 0) {
                return name;
            }
        }
        return "";
    }
}