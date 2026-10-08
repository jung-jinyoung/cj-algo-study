# 전략: 이진 탐색 2번. 첫 위치는 target을 찾아도 왼쪽(right=mid-1)으로 계속 좁혀 가장 왼쪽을 찾고
#       끝 위치는  target을 찾아도 오른쪽(left=mid+1)으로 계속 좁혀 가장 오른쪽을 기록한다.
# 시간복잡도: O(log N)  — log N 탐색을 두 번 (상수배는 무시: 2 log N = O(log N))

class Solution:
    def searchRange(self, nums: list[int], target: int) -> list[int]:
        n = len(nums)
        mx = -1
        mi = -1

        # 처음 위치
        left = 0
        right = n-1
        while left<=right:
            mid = int((left+right))//2

            if target == nums[mid]:
                    mi = mid
                    right = mid-1
            elif target >nums[mid]:
                left = mid+1
            else:
                right = mid-1
        # 마지막 위치
        left = 0
        right = n-1
        while left<=right:
            mid = int((left+right))//2

            if target == nums[mid]:
                    mx = mid
                    left = mid+1
            elif target >nums[mid]:
                left = mid+1
            else:
                right = mid-1


        return [mi,mx]
        