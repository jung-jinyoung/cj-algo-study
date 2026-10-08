# 전략: 투포인터. end를 한 칸씩 늘리며 합을 더하고, 합이 k를 넘으면 맨 왼쪽(start)을 빼며
#       start를 앞으로 옮긴다. 합이 k가 될 때마다 지금 구간 길이(end-start)가 저장된 것보다
#       짧으면 교체한다. '<'라서 길이가 같으면 먼저 찾은(start가 앞선) 구간이 남는다.
# 시간복잡도: O(N)  — start와 end가 각각 최대 N번만 앞으로 움직임(while이 for 안에 있어도 곱이 아님)

def solution(sequence, k):
    n = len(sequence)
    answer = [0,n]
    start = 0
    total = 0


    for end in range(n):
        total+=sequence[end]
        while total > k:
            total -=sequence[start]
            start+=1
        if total == k:
            if end-start <answer[1]-answer[0]:
                answer=[start,end]
    return answer