# 전략:
# 정수에 인덱스를 부여하고, 일단 두개 이상인 것은 분리(앞자리만 기억)
# 맨 앞자리가 가장 큰 거부터 인덱스 정렬

def solution(numbers):
    numbers = list(map(str, numbers))

    for i in range(len(numbers)):
        for j in range(len(numbers) - 1 - i):
            a = numbers[j]
            b = numbers[j + 1]
            if int(b + a) > int(a + b): 
                numbers[j], numbers[j + 1] = b, a


    answer = str(int(''.join(numbers)))
    return answer


def solution(numbers):
    numbers = list(map(str, numbers))

    pairs = []
    for x in numbers:
        pairs.append((x * 3, x))   # (비교용 값, 원래 값)

    pairs.sort(reverse=True)       # 비교용 값 기준으로 큰 순서

    result = ''
    for p in pairs:
        result += p[1]             # 원래 값만 꺼내서 이어붙이기

    answer = str(result)
    return answer