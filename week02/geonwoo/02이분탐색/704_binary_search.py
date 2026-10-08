# 전략: left와 right 비교해서 옮겨주기
# 시간 복잡도: O(log n)

class Solution:
    def search(self, nums, target):
        left = 0
        right = len(nums)-1

        while left <= right:
            mid = (left + right) // 2

            if nums[mid] == target:
                return mid

            if nums[mid] <= target:
                left = mid + 1

            if nums[mid] >= target:
                right = mid - 1

        return -1

            