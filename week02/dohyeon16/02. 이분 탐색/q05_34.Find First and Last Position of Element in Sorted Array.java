/*
   문제 유형: 이분 탐색을 두 번 사용해 target의 시작 위치와 마지막 위치 찾기
   문제5 : <LeetCode> - 34. Find First and Last Position of Element in Sorted Array
   난이도: Medium
*/

/*
   전략

   1. 정답은 [target의 첫 번째 위치, target의 마지막 위치] 형태로 반환한다.
   2. 먼저 이분 탐색으로 target이 처음 나타나는 위치를 찾는다.
   3. nums[mid]가 target과 같더라도 바로 끝내지 않고 더 왼쪽에 같은 값이 있는지 계속 확인한다.
   -> target을 찾으면 현재 mid를 저장하고 right를 mid - 1로 이동한다.
   4. 첫 번째 위치를 끝까지 찾지 못했다면 target이 배열에 없는 것이므로 [-1, -1]을 반환한다.
   5. 이번에는 다시 이분 탐색을 시작해 target이 마지막으로 나타나는 위치를 찾는다.
   6. nums[mid]가 target과 같더라도 더 오른쪽에 같은 값이 있는지 계속 확인한다.
   -> target을 찾으면 현재 mid를 저장하고 left를 mid + 1로 이동한다.
   7. 첫 번째 위치와 마지막 위치를 answer 배열에 저장한다.
   8. 완성된 answer 배열을 반환한다.
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        // target이 처음 나타나는 위치 찾기
        int first = findFirst(nums, target);
        // target이 배열에 없는 경우
        if (first == -1) {
            return new int[]{-1, -1};
        }
        // target이 마지막으로 나타나는 위치 찾기
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    // target의 첫 번째 위치 탐색
    private int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // target을 찾았지만 더 왼쪽에 같은 값이 있는지 확인
                result = mid;
                right = mid - 1;
            } else if (nums[mid] < target) {
                // target이 더 크면 오른쪽 범위 탐색
                left = mid + 1;
            } else {
                // target이 더 작으면 왼쪽 범위 탐색
                right = mid - 1;
            }
        }
        return result;
    }

    // target의 마지막 위치 탐색
    private int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // target을 찾았지만 더 오른쪽에 같은 값이 있는지 확인
                result = mid;
                left = mid + 1;
            } else if (nums[mid] < target) {
                // target이 더 크면 오른쪽 범위 탐색
                left = mid + 1;
            } else {
                // target이 더 작으면 왼쪽 범위 탐색
                right = mid - 1;
            }
        }
        return result;
    }
}