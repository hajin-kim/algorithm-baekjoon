# [Gold III] 파티 - 1238

[문제 링크](https://www.acmicpc.net/problem/1238)

### 성능 요약

메모리: 28600 KB, 시간: 1096 ms

### 분류

데이크스트라, 그래프 이론, 최단 경로

### 후기

그래프 최단경로 문제인데, 최적 아이디어가 상당히 기발합니다.
안 그래도 다익스트라 알고리즘인데, 새로운 인사이트를 얻을 수 있습니다.

다만 시간제한이 여유로워서, 단순하게 풀어도 풀립니다.
조금 더 타이트했으면 좋았겠다는 생각이 드네요.

### 풀이

저희가 구해야 하는 것은 V개의 노드에 대해 (노드 → x 까지의 거리) + (x → 노드 까지의 거리) 의 최솟값을 구하는 것입니다.

**풀이 #1: 플로이드-워셜**

직관적으로 생각해볼 수 있는 것은 “플로이드-워셜” 알고리즘입니다.
모든 노드에서 모든 노드로 가는 최단경로를 O(V^3)에 구할 수 있습니다.
이렇게 구해진 최단경로들로 위의 공식을 계산하면 됩니다.

O(V^3) 입니다.

**풀이 #2: 다익스트라 V회**

다익스트라 알고리즘을 쓰면 특정 노드에서 다른 모든 노드까지의 최단경로들을 O(V log V)에 구할 수 있습니다.
V개의 노드 전부에 다익스트라를 적용해, 각 노드에서 x 까지의 최단경로를 구합니다.
x에서 각 노드까지의 최단경로도 방금 같이 구해졌을 것입니다.
이렇게 구해진 최단경로들로 위의 공식을 계산하면 됩니다.

O(V^2 log V) 입니다.

**풀이 #3: 다익스트라 2회**

이 문제는 **모든 노드가 x와 연결된 유향(방향이 있는)** 가중치 그래프를 다룹니다.
다익스트라는 한 노드에서 다른 노드까지의 최단경로를 구해줍니다.
그렇다면 **그래프를 뒤집어서 x에 다익스트라를 적용**하면 어떻게 될까요?

****************모든 노드에서 x까지의 최단경로를 구할 수 있게 됩니다.****************

- 그래프에 dijkstra(x): x에서 각 노드까지의 최단경로
- 뒤집은 그래프에 dijkstra(x): 각 노드에서 x까지의 최단경로

다익스트라 2번만으로 저희가 공식을 적용할 때 필요한 모든 값이 모였습니다.

O(V log V) 입니다.

[풀이 1](./Floyd.kt)과 [풀이 3](./파티.kt)을 첨부합니다.

---

### 문제 설명

<p>N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.</p>

<p>어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 T<sub>i</sub>(1 ≤ T<sub>i</sub> ≤ 100)의 시간을 소비한다.</p>

<p>각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.</p>

<p>이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.</p>

### 입력

 <p>첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다. 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 T<sub>i</sub>가 들어온다. 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.</p>

<p>모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.</p>

### 출력

 <p>첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.</p>

