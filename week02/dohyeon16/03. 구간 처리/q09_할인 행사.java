/*
   문제 유형: 슬라이딩 윈도우로 10일 구간을 이동하며 원하는 상품과 수량이 모두 맞는지 확인
   문제9 : <프로그래머스> - 할인 행사
   난이도: Level 2
*/

/*
   전략

   1. 원하는 상품과 필요한 수량을 HashMap에 저장한다.
   -> 상품 이름을 key, 필요한 수량을 value로 저장한다.
   -> ex) banana 3개가 필요하면 ("banana", 3) 저장
   2. 회원가입 기간은 항상 10일이므로 discount에서 연속된 10일씩 확인한다.
   3. 현재 10일 동안 할인하는 상품의 개수를 current HashMap에 저장한다.
   4. wanted와 current를 비교한다.
   -> 원하는 모든 상품이 필요한 개수만큼 정확히 존재하면 회원가입 가능한 날짜이다.
   5. 조건을 만족하면 answer를 1 증가시킨다.
   6. 다음 날짜를 확인할 때 10일을 전부 다시 세지 않는다.
   -> 현재 구간의 가장 앞 상품 하나를 제거한다.
   -> 새로운 날짜의 상품 하나를 뒤에 추가한다.
   7. 이렇게 10일 구간을 하루씩 오른쪽으로 이동한다.
   8. 이동한 구간에서도 wanted와 current를 다시 비교한다.
   9. 마지막 10일 구간까지 반복한다.
   10. 회원가입 가능한 날짜의 총 개수를 반환한다.
*/

import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        // 원하는 상품과 필요한 수량을 저장
        // ex) banana 3개, apple 2개 -> wanted = {banana=3, apple=2, ...}
        HashMap<String, Integer> wanted = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wanted.put(want[i], number[i]);
        }
        // 현재 확인하고 있는 10일 동안 각 상품이 몇 번 할인되는지 저장
        HashMap<String, Integer> current = new HashMap<>();
        // 첫 번째 10일 구간인 discount[0] ~ discount[9] 확인
        for (int i = 0; i < 10; i++) {
            // 이미 있던 상품이면 기존 개수 + 1, 처음 나오는 상품이면 0 + 1
            current.put(discount[i], current.getOrDefault(discount[i], 0) + 1);
        }
        // 회원가입 가능한 날짜 수
        int answer = 0;
        // 첫 번째 10일 구간이 원하는 상품 구성과 일치하는지 확인
        if (isMatch(want, wanted, current)) {
            answer++;
        }
        // 두 번째 10일 구간부터 마지막까지 하루씩 이동
        // i = 새롭게 10일 구간 안으로 들어오는 날짜의 인덱스
        for (int i = 10; i < discount.length; i++) {
            // 이전 10일 구간의 가장 앞에 있던 상품
            String removeProduct = discount[i - 10];
            // 빠져나가는 상품의 개수를 1 감소
            current.put(removeProduct, current.get(removeProduct) - 1);
            // 새로운 날짜에 할인하는 상품
            String addProduct = discount[i];
            // 새롭게 들어온 상품의 개수를 1 증가
            current.put(addProduct, current.getOrDefault(addProduct, 0) + 1);
            // 이동한 10일 구간이 원하는 상품과 수량을 만족하는지 확인
            if (isMatch(want, wanted, current)) {
                answer++;
            }
        }
        return answer;
    }
    // 현재 10일 할인 상품이 원하는 상품과 수량을 모두 만족하는지 확인
    private boolean isMatch(String[] want, HashMap<String, Integer> wanted, HashMap<String, Integer> current) {
        // 원하는 상품을 하나씩 확인
        for (String product : want) {
            // 현재 10일 동안 해당 상품이 몇 개 있는지 확인, 상품이 없으면 0으로 처리
            int currentCount = current.getOrDefault(product, 0);
            // 필요한 상품 수량
            int wantedCount = wanted.get(product);
            // 필요한 수량과 현재 수량이 다르면 조건 불만족
            if (currentCount != wantedCount) {
                return false;
            }
        }
        // 모든 상품의 수량이 일치
        return true;
    }
}