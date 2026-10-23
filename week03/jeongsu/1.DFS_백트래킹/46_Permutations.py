# 전략: DFS(스택). 스택에 (지금까지 만든 순서, 아직 안 쓴 숫자들)을 넣는다.
#       꺼낼 때마다 남은 숫자 하나씩을 골라 뒤에 붙인 갈래를 '남은 숫자 개수만큼' 넣는다.
#       남은 숫자가 없으면 순열 하나 완성.
# 시간복잡도: O(N × N!)  — 순열 N!개, 각각 만들 때 최대 N개 복사
# 가끔 씩 나올 수 있다.
# 파이썬은 product, itertools 순열 순회 가능한 리스트, 덱, permutations 

class Solution:
    def permute(self, nums: list[int]) -> list[list[int]]:
        answer = []
        stack = [([], nums)]                     # (cur, rest) = (지금까지 만든 순서, 남은 숫자)
        while stack:
            cur, rest = stack.pop()
            if len(rest) == 0:                   # 남은 숫자가 없으면 순열 하나 완성
                answer.append(cur)
            else:
                for i in range(len(rest)):       # 남은 숫자 하나씩 골라 갈래 만들기
                    stack.append((cur + [rest[i]], rest[:i] + rest[i+1:]))   # i번째만 뺀 나머지
        return answer