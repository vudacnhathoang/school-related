import math

def newtonPi(init):
    x = init
    while(1):
        y = x -  (math.sin(x) / math.cos(x))
        if (x == y):
            return y
        x = y

def leibnizPi(iterations):
    res = 0
    sign = 1
    for i in range(1,iterations * 2, 2):
        res += sign * 4/i
        sign *= -1
    return float(res)

def nilakanthaPi(iterations):
    res = 3
    sign = 1
    for i in range(2,iterations * 2, 2):
        res += sign * 4/(i * (i + 1) * (i + 2))
        sign *= -1
    return float(res)
# print(leibnizPi(int(input("number: "))))
# print(nilakanthaPi(int(input("number: "))))
# print(newtonPi(int(input("number: "))))