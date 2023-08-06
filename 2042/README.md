# 2042번 "구간 합 구하기" 풀이 및 후기

| 바로가기  | <https://www.acmicpc.net/problem/2042> |
|-------|----------------------------------------|
| 레이팅   | g1                                     |
| 푼 날짜  | 2023-08-05                             |
| 걸린 시간 | 15m                                    |

## 관찰

구간 합 구할 때 선형시간이면 무조건 시간 초과됩니다.
따라서 prefix sum 혹은 segment tree를 사용해야 합니다.

Long 사용 필수입니다.

## Prefix sum

구간 합을 prefix sum으로 구현합니다.
모든 변경사항은 map에 모조리 집어넣고, 구간 합을 쿼리할 때마다 map을 순회하며 구간에 포함되는 값들을 더해줍니다.

업데이트 O(1)
쿼리는 O(M)

종합 O(MK)이지만 M, K가 1만까지여서 아슬아슬하게 통과합니다.
시간복잡도는 높지만, 구현이 간단합니다.

제출 결과 메모리 191052 KB, 시간 2408 ms

## Segment tree

업데이트 무조건 O(log N)
쿼리 무조건 O(log N)

종합 $O((M + K) log N)$ 입니다.

제출 결과 메모리 179384 KB, 시간 1352 ms