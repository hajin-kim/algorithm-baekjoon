# 10942번 "팰린드롬?" 풀이 및 후기

| 바로가기  | <https://www.acmicpc.net/problem/10942> |
|-------|-----------------------------------------|
| 레이팅   | g4                                      |
| 푼 날짜  | 2023-08-10                              |
| 걸린 시간 | 25m                                     |

## 후기

D?P

## 풀이

길이 2000인 입력 문자열의 모든 substring에 대해 팰린드롬 체크가 필요합니다.
이때 같은 문자열에 대해 테스트를 1,000,000번 해야하므로 모든 substring에 대한 팰린드롬 여부를 미리 계산해둘 필요가 있습니다.

재귀 이용해서 홀수 길이, 짝수 길이 각각 모두 테스트해보면 됩니다.

꼬리 재귀(tailrec) 최적화는 쓸 수 있어서 적용해봤습니다.

마지막에 100만 번 println하는 것때문에 시간초과 났었는데, 결과를 문자열로 join시키고 한 번만 println해서 해결했습니다.
