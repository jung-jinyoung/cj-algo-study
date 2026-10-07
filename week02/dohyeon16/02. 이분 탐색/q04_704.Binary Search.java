/*
   문제 유형: 정렬된 배열에서 탐색 범위를 절반씩 줄여 target 위치 찾기
   문제4 : <LeetCode> - 704. Binary Search
   난이도: Easy
*/

/*
   전략

   1. 배열의 탐색 범위를 나타낼 left와 right를 만든다.
   -> left는 배열의 첫 번째 인덱스인 0으로 시작한다.
   -> right는 배열의 마지막 인덱스인 nums.length - 1로 시작한다.
   2. left가 right보다 커지지 않는 동안 계속 탐색한다.
   3. 현재 탐색 범위의 가운데 위치 mid를 구한다.
   4. nums[mid]가 target과 같은지 확인한다.
   -> 같으면 target을 찾은 것이므로 mid를 반환한다.
   5. nums[mid]가 target보다 작으면 target은 오른쪽에 있다.
   -> left를 mid + 1로 이동한다.
   6. nums[mid]가 target보다 크면 target은 왼쪽에 있다.
   -> right를 mid - 1로 이동한다.
   7. target을 찾을 때까지 탐색 범위를 절반씩 줄여나간다.
   8. 반복이 끝날 때까지 target을 찾지 못하면 -1을 반환한다.
*/

class Solution {
    public int search(int[] nums, int target) {
        // 탐색 범위의 시작 위치
        int left = 0;
        // 탐색 범위의 마지막 위치
        int right = nums.length - 1;
        // 탐색할 범위가 남아 있는 동안 반복
        while (left <= right) {
            // 현재 탐색 범위의 가운데 위치
            int mid = left + (right - left) / 2;
            // 가운데 값이 target과 같으면 해당 인덱스 반환
            if (nums[mid] == target) {
                return mid;
            }
            // 가운데 값보다 target이 크면 오른쪽 범위 탐색
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 가운데 값보다 target이 작으면 왼쪽 범위 탐색
                right = mid - 1;
            }
        }
        // target이 배열에 없는 경우
        return -1;
    }
}