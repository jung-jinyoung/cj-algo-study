# 전략: 이진 탐색. 초반에 전략을 잘 못 잡음. 중간값을 구해서, 중간값이 타겟값보다 작으면 중간값 기준 왼쪽을 슬라이싱 해버림.
# 그 반대로 중간값이 타겟값보다 크면 중간값 기준 오른쪽을 슬라이싱해버림. 이후 배열은 자르지 않고 
# 남은 범위의 양 끝(left, right)만 움직인다는 것을 깨달음.
#       가운데 값(mid)이 target보다 작으면 왼쪽 절반을, 크면 오른쪽 절반을 버린다.
# 시간복잡도: O(log N)  — 매번 범위가 절반으로 줄어듦

class Solution:
    def search(self, nums: list[int], target: int) -> int:
        n = len(nums) #6
        left = 0
        right = n-1

        while left <= right :
                mid = int((left+right)//2) 
                if target == nums[mid]:
                    return mid
                    break
                elif target > nums[mid]:
                    left = mid+1
                elif target<nums[mid]:
                    right = mid-1  
                
        return -1


            

        