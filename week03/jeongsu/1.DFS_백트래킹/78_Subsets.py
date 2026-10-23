# 전략: DFS(스택). 숫자마다 '넣는다 / 안 넣는다' 두 갈래로 나뉘는 나무를 탐색한다.
#       스택에 (다음에 볼 숫자 번호, 지금까지 고른 부분집합)을 넣고, 꺼낼 때마다
#       nums[index]를 넣은 갈래와 안 넣은 갈래 두 개를 넣는다. 숫자를 다 본 갈래가 부분집합 하나다.
# 시간복잡도: O(N × 2^N)  — 부분집합 2^N개, 각각 만들 때 최대 N개 복사
# 문제 

class Solution:
    def subsets(self, nums: list[int]) -> list[list[int]]:
        answer = []
        stack = [(0, [])]                    # (index, cur) = (다음에 볼 숫자 번호, 지금까지 고른 것)
        while stack:
            index, cur = stack.pop()
            if index == len(nums):           # 숫자를 다 봤으면 부분집합 하나 완성
                answer.append(cur)
            else:
                stack.append((index+1, cur + [nums[index]]))   # 넣는 갈래
                stack.append((index+1, cur))                    # 안 넣는 갈래
        return answer