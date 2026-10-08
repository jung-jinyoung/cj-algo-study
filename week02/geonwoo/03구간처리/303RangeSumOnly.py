#전략: 누적합을 미리 만들어서 시간을 단축
#시간 복잡도: O(n)

class NumArray:
    def __init__(self, nums):
        self.prefix = [0]
        for i in nums:
            self.prefix.append(self.prefix[-1] + i)

    def sumRange(self, left, right):
        return self.prefix[right + 1] - self.prefix[left]