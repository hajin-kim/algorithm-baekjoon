# 12865번 "평범한 배낭" 풀이 및 후기

| 바로가기  | <https://www.acmicpc.net/problem/12865> |
|-------|-----------------------------------------|
| 레이팅   | g5                                      |
| 푼 날짜  | 2023-08-13                              |
| 걸린 시간 | 30m                                     |

## 후기

## 풀이

물건이 100개 이하라 시간복잡도 엄청 많이 연연하지 않아도 됩니다.

무게를 key로, 그 무게에 대한 최대 가치를 value로 DP를 정의합니다.
새 물건을 골랐을 때, 기존 DP 자료구조에 들어있는 원소 각각에 대하여 다음을 수행합니다.
new.weight + cached.weight <= K 이고, DP[new.weight + cached.weight]가 없거나 new.value + cached.value보다 작다면, 추가 또는 업데이트합니다.

최종 O(NK). 정확히는 N*K/2 정도입니다.
DP를 배열로 짜면 매우 sparse하므로 해시맵을 추천합니다.
