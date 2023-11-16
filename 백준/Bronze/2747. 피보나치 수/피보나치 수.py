fibonacci = {0:0, 1:1}
n = int(input())
for i in range(2, n+1):
	fibonacci[i] = fibonacci[i-2] + fibonacci[i-1]
print(fibonacci[n])
