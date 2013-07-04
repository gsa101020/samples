def sieveeratosthenes(n):
	n = [0] * n
	n[0], n[1] = 1, 1
	i, j = 2, 2	
	while (i < len(n)):
		if (n[i] == 0):
			for j in range(i*i, len(n), i):
					n[j] = 1
		i += 1
	
	k = []
	for index in range(len(n)):
		if(n[index] == 0):
			k.append(index)
	return k


def nthprime(x):
	primes = sieveeratosthenes(1001000000)
	return primes[x-1]

print nthprime(1000000)
