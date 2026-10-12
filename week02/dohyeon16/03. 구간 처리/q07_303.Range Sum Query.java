/*
   문제 유형: 누적합을 미리 계산해 원하는 구간의 합을 빠르게 구하기
   문제7 : <LeetCode> - 303. Range Sum Query - Immutable
   난이도: Easy
*/

/*
   전략

   1. nums 배열의 누적합을 저장할 prefixSum 배열을 만든다.
   -> prefixSum[i]에는 nums의 처음부터 i-1번째까지의 합을 저장한다.
   2. prefixSum의 길이는 nums보다 1 크게 만든다.
   -> prefixSum[0]을 0으로 두면 구간합 계산이 간단해진다.
   3. nums를 처음부터 끝까지 확인하면서 누적합을 계산한다.
   -> prefixSum[i + 1] = prefixSum[i] + nums[i]
   4. sumRange(left, right)가 호출되면 left부터 right까지 직접 더하지 않는다.
   5. right까지의 누적합에서 left 이전까지의 누적합을 뺀다.
   -> prefixSum[right + 1] - prefixSum[left]
   6. 계산한 구간합을 반환한다.
*/

class NumArray {
    // 각 위치까지의 누적합을 저장할 배열
    private int[] prefixSum;
    public NumArray(int[] nums) {
        // nums보다 한 칸 큰 누적합 배열 생성
        prefixSum = new int[nums.length + 1];
        // 처음부터 현재 위치까지의 합을 차례대로 저장
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        // right까지의 합에서 left 이전까지의 합을 빼서 구간합 계산
        return prefixSum[right + 1] - prefixSum[left];
    }
}