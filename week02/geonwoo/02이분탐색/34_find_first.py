# 전략: 양옆에서 찾되 찾으면 기록하고 한쪽으로 밀고나간다
# 시간복잡도: O(n)
# 참고사항: 도저히 시간복잡도 제한을 못 맞추겠어서 AI의 힘을 빌렸고, 아이디어가 생각보다 간단했음

class Solution:
    def searchRange(self, nums, target):
        left = 0
        right = len(nums) - 1
        first_num = -1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                first_num = mid
                right = mid - 1

            elif nums[mid] < target:
                left = mid + 1

            else:
                right = mid - 1

        left = 0
        right = len(nums) - 1
        last_num = -1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                last_num = mid        # 일단 기록하고
                left = mid + 1    # 오른쪽에 더 있는지 계속 탐색
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1

        return [first_num, last_num]
