# 13335 - 트럭

<https://www.acmicpc.net/problem/13335> s1

시간이 여유로워서 큐를 쓴 시뮬레이션으로 풀었습니다.

time: O(nTrucks * bridgeLength) if trucks are all the same weight with maxWeight.
다리의 무게가 꽉 찼을 때 불필요한 반복을 생략하면 조금 더 최적화할 수 있습니다.

space: O(nTrucks + bridgeLength)
