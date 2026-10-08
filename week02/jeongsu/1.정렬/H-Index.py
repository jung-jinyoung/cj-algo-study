# 접근 방법 잘못이해함. h를 인용횟수 값 중 하나로 보고 중간값을 구하려고 했음 
 
# 막혔던 구간(range 시작값, 인용횟수가 h이상인 거 세는 반복문 누락, 비교 연산자, cnt 초기화 위치)

# 전략: 완전탐색. h 후보를 n(논문 수)부터 0까지 큰 쪽에서 내려가며, 인용 횟수가 h 이상인 논문 수(cnt)를 센다.
#       cnt가 h 이상이 되는 첫 h가 가능한 최댓값이므로 바로 반환한다.
# 시간복잡도: O(N²)  — h 후보 N+1개 × 논문 N편 세기 (정렬 N log N보다 커서 N²만 남김)

def solution(citations):
    answer = 0
    citations.sort()
    n = len(citations)
    
    for h in range(n,-1,-1):
        cnt =0
        for c in citations:
            if c>=h:
                cnt+=1
        if cnt >=h:
                return h
    
    return answer