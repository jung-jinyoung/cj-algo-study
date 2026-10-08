# 전략: 오른쪽은 계속 이동하고, 왼쪽은 숫자 합이 넘을 때 따라오도록
# 시간 복잡도: O(n)

def solution(sequence, k):
    left = 0
    total = 0
    num_sum = [0, len(sequence)]

    for right in range(len(sequence)):
        total += sequence[right]

        while total > k:
            total -= sequence[left]
            left += 1

        if total == k:
            if right - left < num_sum[1] - num_sum[0]:
                num_sum = [left, right]

    return num_sum