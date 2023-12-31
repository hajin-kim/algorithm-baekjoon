# [Gold V] 로봇 청소기 - 14503

[문제 링크](https://www.acmicpc.net/problem/14503)

### 성능 요약

메모리: 25396 KB, 시간: 188 ms

### 분류

구현, 시뮬레이션

### 풀이

방향 전환 부분입니다.

- 방향은 0이 북쪽, 1이 동쪽, …, 입니다.
- 이렇게 하면 시계방향 회전은 (방향 + 1)이고, 반시계방향 회전은 (방향 - 1)로 정의할 수 있습니다.
- 이걸 modulo 연산을 이용해 표현하면 `nextDirection = (direction + rotation + 4) % 4` 입니다. `rotation`은 회전을 의미하고, `+ 4` 는 반시계방향 회전 시
  음수가 되지 않게 막고, `% 4` 는 시계방향 회전 시 4 이상이 되지 않도록 막습니다.
- `deltas` 는 각 방향별 row와 column의 변화를 나타냅니다. 예컨대 북쪽의 방향은 `0`이고, 이에 대한  `deltas[0]` 는 `dr, dc = -1, 0` 입니다. 즉, 위로 한 칸을
  의미합니다.
- 이러한 회전 테크닉은 잘 익혀두면 유용하게 이용할 수 있습니다.
- 아래 코드를 참고해보세요.

```kotlin
val deltas = arrayOf(
    -1 to 0, // 북
    0 to 1, // 동
    1 to 0, // 남
    0 to -1, // 서
)

fun move(r: Int, c: Int, d: Int): Triple<Int, Int, Int> {
    val (dr, dc) = deltas[d]
    val nr = r + dr
    val nc = c + dc

    return Triple(nr, nc, d)
}

val nextOrNull = listOf(
    -1, // -90도
    -2, // -180도
    -3, // -270도
    -4, // -360도 (같은 방향)
)
    .map { rotation ->
        val nextDirection = (d + rotation + 4) % 4

        move(r, c, nextDirection)
    }
    .firstOrNull { (nr, nc) ->
        isValidMovement(nr, nc) && !isClean[nr][nc]
    }
```

---

### 문제 설명

<p>로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.</p>

<p>로봇 청소기가 있는 방은 $N \times M$ 크기의 직사각형으로 나타낼 수 있으며, $1 \times 1$ 크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다. 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북 중 하나이다. 방의 각 칸은 좌표 $(r, c)$로 나타낼 수 있고, 가장 북쪽 줄의 가장 서쪽 칸의 좌표가 $(0, 0)$, 가장 남쪽 줄의 가장 동쪽 칸의 좌표가 $(N-1, M-1)$이다. 즉, 좌표 $(r, c)$는 북쪽에서 $(r+1)$번째에 있는 줄의 서쪽에서 $(c+1)$번째 칸을 가리킨다. 처음에 빈 칸은 전부 청소되지 않은 상태이다.</p>

<p>로봇 청소기는 다음과 같이 작동한다.</p>

<ol>
	<li>현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.</li>
	<li>현재 칸의 주변 $4$칸 중 청소되지 않은 빈 칸이 없는 경우,
	<ol>
		<li>바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.</li>
		<li>바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.</li>
	</ol>
	</li>
	<li>현재 칸의 주변 $4$칸 중 청소되지 않은 빈 칸이 있는 경우,
	<ol>
		<li>반시계 방향으로 $90^\circ$ 회전한다.</li>
		<li>바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.</li>
		<li>1번으로 돌아간다.</li>
	</ol>
	</li>
</ol>

### 입력

 <p>첫째 줄에 방의 크기 $N$과 $M$이 입력된다. $(3 \le N, M \le 50)$  둘째 줄에 처음에 로봇 청소기가 있는 칸의 좌표 $(r, c)$와 처음에 로봇 청소기가 바라보는 방향 $d$가 입력된다. $d$가 $0$인 경우 북쪽, $1$인 경우 동쪽, $2$인 경우 남쪽, $3$인 경우 서쪽을 바라보고 있는 것이다.</p>

<p>셋째 줄부터 $N$개의 줄에 각 장소의 상태를 나타내는 $N \times M$개의 값이 한 줄에 $M$개씩 입력된다. $i$번째 줄의 $j$번째 값은 칸 $(i, j)$의 상태를 나타내며, 이 값이 $0$인 경우 $(i, j)$가 청소되지 않은 빈 칸이고, $1$인 경우 $(i, j)$에 벽이 있는 것이다. 방의 가장 북쪽, 가장 남쪽, 가장 서쪽, 가장 동쪽 줄 중 하나 이상에 위치한 모든 칸에는 벽이 있다. 로봇 청소기가 있는 칸은 항상 빈 칸이다.</p>

### 출력

 <p>로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.</p>

