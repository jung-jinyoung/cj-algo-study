/*
   문제7 : <프로그래머스> - 올바른 괄호
   난이도: Level 2
*/

import java.util.Stack;

class Solution {
    boolean solution(String s) {
        // 여는 괄호 저장을 위한 스택 생성
        Stack<Character> stack = new Stack<>();

        // 문자열의 각 문자 순차 확인
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 여는 괄호인 경우 스택에 저장
            if (c == '(') {
                stack.push(c);
            } else {
                // 닫는 괄호인데 스택이 비어 있으면 올바르지 않은 괄호
                if (stack.isEmpty()) {
                    return false;
                }
                // 가장 최근에 여는 괄호 제거
                stack.pop();
            }
        }
        // 모든 확인 후 스택이 비어 있으면 올바른 괄호
        return stack.isEmpty();
    }
}