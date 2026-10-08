# 전략: 정렬. 명령마다 array의 i번째~j번째(인덱스 i-1 ~ j-1)를 cut에 담아 정렬한 뒤,
#       k번째 수(인덱스 k-1)를 answer에 넣는다. 다음 명령 전에 cut을 비운다.
# 시간복잡도: O(C × N log N)  — 명령 C개 × (자르기 N + 정렬 N log N)

def solution(array, commands):
    answer = []
    cut = [] # 자른 배열을 가두는 곳
    #1. i부터 j 까지 배열 자른다.
    for a in range(len(commands)):
        i = commands[a][0] # commands의 첫번째 원소
        j = commands[a][1] # commands의 두번째 원소
        k = commands[a][2] #commands의  세번째 원소
        for b in range(i-1,j): #b는 i 부터 j까지
            cut.append(array[b]) 
        #2. 그 배열을 정렬한다.
        cut.sort()
        #3. K번째 있는 수를 구한다.
        answer.append(cut[k-1])
        cut.clear()

    return answer