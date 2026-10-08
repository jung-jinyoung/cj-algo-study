# 전략: 최대값과 최소값의 인덱스를 먼저 저장하고, 값 최신화, 최신화 전에 차이가 없으면 그만두기
# 시간복잡도: O(D × N)
# 참고사항: if max(box_length) - min(box_length) <= 1: 이줄에서 인덱스를 활용하는 방식으로 바꿔야 불필요한 리스트 참조가 줄음

for test in range(1, 11):
    dump = int(input())
    box_length = list(map(int, input().split()))

    for i in range(dump):
        max_index = box_length.index(max(box_length))
        min_index = box_length.index(min(box_length))

        if box_length[max_index] - box_length[min_index] <= 1:
            break

        box_length[max_index] -= 1
        box_length[min_index] += 1

    print(f"#{test} {max(box_length) - min(box_length)}")
