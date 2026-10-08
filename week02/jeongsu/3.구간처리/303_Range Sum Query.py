# 전략: 누적합. __init__에서 맨 앞에 0을 둔 누적합 배열을 한 번 만들어 self.prefix_sum에 저장하고
#       sumRange는 빼기 한 번. right가 포함 범위라서 prefix_sum[right+1] - prefix_sum[left].
# 시간복잡도: __init__ O(N), sumRange O(1)  — 누적합은 한 번만 만들고, 호출마다 빼기 한 번

class NumArray:

    def __init__(self, nums: list[int]):
        self.prefix_sum =[0]
        for i in range(len(nums)):
            self.prefix_sum.append(self.prefix_sum[i]+nums[i])


    def sumRange(self, left: int, right: int) -> int:
        return self.prefix_sum[right+1]-self.prefix_sum[left]
