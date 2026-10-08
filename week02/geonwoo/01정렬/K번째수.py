# 전략: 각 시도마다 배열을 i~j로 자른다, 정렬 후 K번째 값을 꺼낸다.
# 시간복잡도: O(C × N log N)  (N = array 길이, C = commands 길이)

def solution(array, commands):
    answer = []
    for i, j, k in commands:
        sliced = sorted(array[i-1:j])  # i번째~j번째 자르고 정렬
        answer.append(sliced[k-1])     # k번째 수
    return answer