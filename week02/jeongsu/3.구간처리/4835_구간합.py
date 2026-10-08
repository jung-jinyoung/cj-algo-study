# 전략: 누적합. 맨 앞에 0을 둔 누적합 배열을 미리 만들고, 이웃한 M개의 합을
#       뺄셈 (prefix_sum[s] - prefix_sum[s-m])으로 구해 리스트에 모은 뒤 최댓값 - 최솟값.
# 시간복잡도: O(N)  — 누적합 만들기 N + 구간 합 N + max/min N

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n,m = map(int,input().split())
    nums = list(map(int,input().split()))
    prefix_sum= [0] # 0개까지의 합. 첫 구간도 빼기 한 번으로 구하려고 넣음
     
    for i in range(n):
            prefix_sum.append(prefix_sum[i]+nums[i]) # 누적합
    result=[]
    for s in range(m,n+1):
        result.append(prefix_sum[s]-prefix_sum[s-m]) # s개까지 합 - (s-m)개까지 합 = 뒤쪽 m개 합
    print("#"+str(test_case),max(result)-min(result))