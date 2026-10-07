/*
   문제3 : 프로그래머스 - 문자열 압축
   난이도: Level 2
*/

class Solution {

    public int solution(String s) {

        // 원본 문자열 길이를 최소 길이로 초기 설정
        int minLength = s.length();

        // 1글자 단위부터 문자열 절반 길이까지 압축 단위 탐색
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            StringBuilder compressed = new StringBuilder();

            // 첫 번째 문자열 조각과 반복 횟수 설정
            String prev = s.substring(0, unit);
            int count = 1;

            // 설정한 단위만큼 문자열 분할 및 비교
            for (int i = unit; i < s.length(); i += unit) {
                int end = Math.min(i + unit, s.length());
                String current = s.substring(i, end);

                // 동일한 문자열 조각의 반복 횟수 증가
                if (prev.equals(current)) {
                    count++;
                } else {
                    // 반복 횟수가 2 이상인 경우 숫자 추가
                    if (count > 1) {
                        compressed.append(count);
                    }

                    // 이전 문자열 조각 추가
                    compressed.append(prev);

                    // 다음 비교를 위한 기준 문자열 및 반복 횟수 초기화
                    prev = current;
                    count = 1;
                }
            }

            // 마지막 문자열 조각 처리
            if (count > 1) {
                compressed.append(count);
            }
            compressed.append(prev);

            // 가장 짧은 압축 문자열 길이 갱신
            minLength = Math.min(minLength, compressed.length());
        }

        return minLength;
    }
}