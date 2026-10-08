# 전략: 그리디 + 정렬. 덤프마다 정렬해서 가장 높은 상자(맨 끝, pop())에서 1을 빼고
#       가장 낮은 상자(맨 앞, pop(0))에 1을 더한다. 매번 최고점에서 최저점으로 옮기는 것이 
# 그리디(매 순간 가장 좋아 보이는 선택)다. 덤프를 다 하면 최고 - 최저를 출력한다.
# 시간복잡도: O(D × N log N)  — 덤프 D번 × 정렬 N log N (N=100, D≤1000)


T = 10

for test_case in range(1, T + 1):
    cnt =int(input())
    boxes = list(map(int, input().split()))
    for _ in range(cnt):
        boxes.sort()
        higher =boxes.pop() # 가장 큰 값
        lower = boxes.pop(0) # 가장 낮은 값
        boxes.append(higher-1)
        boxes.append(lower+1)
        boxes.sort()
        #print(boxes)
    print("#"+str(test_case),max(boxes)-min(boxes))