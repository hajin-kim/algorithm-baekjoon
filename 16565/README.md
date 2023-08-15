# 16565번 "N포커" 풀이 및 후기

| 바로가기  | <https://www.acmicpc.net/problem/16565> |
|-------|-----------------------------------------|
| 레이팅   | g2                                      |
| 푼 날짜  | 2023-08-11                              |
| 걸린 시간 | 실제로 쓴 건 2h 정도                           |

## 후기

몇 가지 착각으로 시간복잡도 고민을 좀 많이 했는데, 결국 N <= 52여서 꽤나 여유롭습니다.

## 풀이

combination 구할 때 재귀 + DP를 사용해야 합니다.
어차피 N <= 52 여서 $O(N^2)$으로 충분히 풀립니다.
다만 값이 너무 커져서 문제에서 정한 10,007로 매번 나머지 연산을 해줘야 합니다.
그래서 덧셈연산 만으로 구해지는 이항계수로 구하는 게 나머지 정리 적용하기 무난합니다.

이 문제의 공식은 다음과 같습니다.

${13 \choose 1} *{(52 - 4) \choose (n - 4)} - {13 \choose 2}*{(52 - 8) \choose (n - 8)} + ...$

포카드 1개를 확정하고 나머지를 고르는 경우 - 포카드 2개를 확정하고 나머지를 고르는 경우 + 포카드 3개를 확정하고 나머지를 고르는 경우 ...
중복 카운트된 것 제거, 이로 인한 누락된 것 복구의 반복입니다.
i = 1..(n/4) 에 대해 i가 홀수면 더하고, 짝수면 빼면 됩니다.

기저사례로 n < 4인 것만 따로 처리했습니다.
조합 계산할 때마다 mod 해줬기 때문에 뺄셈 연산 시 최종 결과가 음수가 나올 수 있으니 막판에 보정해줬습니다.

해봤자 N <= 52여서 모든 입력들 다 띄우고 눈으로 보면서 디버깅하는 것도 괜찮습니다.