def polyEval(poly, x)->float:
    length = len(poly)
    result = 0
    for i in range(length -1, -1, -1):
        result = poly[i] + result * float(x)
    return float(result)

def polySum(poly1, poly2):
    len1 = len(poly1)
    len2 = len(poly2)
    if len1 > len2:
        max = len1
    else:
        max = len2

    res = []
    for i in range(max):
        if i < len1 and i < len2:
            res.append(poly1[i] + poly2[i])
        elif i < len1:
            res.append(poly1[i])
        else:
            res.append(poly2[i])
    while res[-1] == 0:
        res.pop()
    return res
def polyMultiply(poly1, poly2):
    len1 = len(poly1)
    len2 = len(poly2)
    lenres = len1 + len2 -1
    res = [0] * (lenres)
    for i in range(len1):
        for j in range(len2):
            res[i+j] += poly1[i] * poly2[j]
    return res    

#poly1 = [1, 2.5, 3.5, 0, 5.4]
#print(polyEval(poly1, 2))
#print(polySum([1, 2.5, 3.5, 0, 5.4],[-23, 0, 0, 0, -2]))
#print(polyMultiply([1, 2, 5], [2, 0, 1, -7]))
