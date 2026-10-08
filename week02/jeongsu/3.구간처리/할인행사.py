# 전략부터 AI의 도움을 받음.
# 전략: 해시(딕셔너리) + 슬라이딩 윈도우. 원하는 품목을 {이름: 개수} 딕셔너리(need)로 만들고,
#       첫 10일의 할인 품목 개수를 직접 세어 window에 담는다. 이후 창을 한 칸씩 밀 때마다
#       빠지는 품목(discount[end-10])은 -1(0개가 되면 키 삭제), 들어오는 품목(discount[end])은 +1 하고,
#       window == need 이면 그날 가입하면 되므로 개수를 센다.
# 시간복잡도: O(N)  — 창을 N번 밀고, 한 번 밀 때 +1/-1과 딕셔너리 비교(품목 최대 10개)는 상수

def solution(want, number, discount):
    need = {}
    answer = 0
    window = {}
    for i in range(len(want)):
        need[want[i]] = number[i]
    for item in discount[0:10]: # 첫 10일은 직접 센다.
        if item in window:
            window[item]+=1
        else:
            window[item] =1
    if window == need:
        answer +=1
    
    for end in range(10,len(discount)):
        out = discount[end-10]
        window[out] -=1
        if window[out] ==0:
            del window[out]
        new = discount[end]
        if new in window:
            window[new]+=1
        else:
            window[new] = 1
        
        if window == need:
            answer+=1
    return answer
        
            
        
    return answer