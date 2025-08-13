def sortNumbers(data, condition):     
        for i in range(len(data)- 1, 0, -1):
            for j in range(0,i):
                if condition == 'ASC':
                     if data[j] > data[j + 1]:
                          data[j], data[j + 1] = data[j+ 1], data[j]
                elif condition == 'DESC':
                     if data[j] < data[j + 1]:
                          data[j], data[j + 1] = data[j+ 1], data[j]
        return data
def sortData(weights, data, condition):
    if len(weights) != len(data):
        raise ValueError('Invalid input data')
    else:
        for i in range(len(data)- 1, 0, -1):
            for j in range(0,i):
                if condition == 'ASC':
                     if weights[j] > weights[j + 1]:
                        weights[j], weights[j + 1] = weights[j+ 1], weights[j]
                        data[j], data[j + 1] = data[j+ 1], data[j]
                elif condition == 'DESC':
                     if weights[j] < weights[j + 1]:
                        weights[j], weights[j + 1] = weights[j+ 1], weights[j]
                        data[j], data[j + 1] = data[j+ 1], data[j]  
    return data
# sortNumbers(array, 'DESC');
# print(array);

# print(sortData([3,2,4],['Ford','BMW','Audi'], 'DESC'));
# print(sortData([2,5,6], ['Ford','BMW','Audi'], 'ASC'));
