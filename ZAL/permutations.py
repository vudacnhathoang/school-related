def permutations(array):
    list = []

    if len(array) == 1 or len(array) == 0:
        return [array]

    for i in range(len(array)):
        m = array.pop(i)
        remLst = permutations(array)
        for p in remLst:
            list.append([m] + p)
        array.insert(i, m)

    return list


# print(permutations([4])) 
# print(permutations([]))
# print(permutations(['a','b','c']))
# print(permutations([1,2,3,4])) 


    
