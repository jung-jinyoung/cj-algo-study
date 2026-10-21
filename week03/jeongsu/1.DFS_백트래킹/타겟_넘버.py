# 전략: DFS(스택). 숫자마다 +를 붙이는 갈래와 -를 붙이는 갈래로 나뉘는 나무를 탐색한다.
#       스택에 (다음에 쓸 숫자 번호, 지금까지의 합)을 넣고, 꺼낼 때마다
#       numbers[index]를 더한 갈래와 뺀 갈래 두 개를 넣는다.
#       숫자를 다 쓴 갈래(index == len(numbers))에서 합이 target이면 개수를 센다.
# 시간복잡도: O(2^N)  — 숫자마다 갈래가 2배로 늘어남 (N ≤ 20이라 약 100만)

def solution(numbers, target):
    answer = 0
    stack =[(0,0)]
    while stack:
        index, total = stack.pop()
        if index == len(numbers):
            if total== target:
                answer +=1
        else:
            stack.append((index+1,total+numbers[index]))
            stack.append((index+1,total -numbers[index]))
    return answer