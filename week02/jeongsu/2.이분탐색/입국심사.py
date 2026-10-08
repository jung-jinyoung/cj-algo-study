# 전략: 전략부터 AI의 도움을 받음.
# 이분탐색(답을 탐색). 배열이 아니라 '걸리는 시간'을 1 ~ max(times)*n 범위에서 이분탐색한다.
#       mid분 동안 심사할 수 있는 사람 수 = 각 심사관의 mid // t 를 모두 더한 값.
#       n명 이상이면 답 후보로 기록하고 더 짧은 시간도 되는지 왼쪽(right=mid-1)을 보고,
#       모자라면 오른쪽(left=mid+1)을 본다. (LeetCode 34 '첫 위치 찾기'와 같은 틀)
# 시간복잡도: O(M × log(max(times) × n))  — 이분탐색 log(범위)번 × 심사관 M명 세기

def solution(n, times):
    answer = 0
    left = 1
    right = max(times)*n
    answer = right
    while left<=right:
        mid = (left+right)//2
        people = 0
        for t in times:
            people+=mid//t
        if people >=n:
            answer = mid
            right = mid-1
        else:
            left = mid+1
    return answer
        