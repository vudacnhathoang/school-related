coffeeType ="Coffee"
healthyType = "HealthyFood"

class Item:
    def __init__(self,coffeeCapacity,number):
        self.name = coffeeType if coffeeCapacity> 0 else healthyType
        self.coffeeCapacity = coffeeCapacity
        self.number = number
    def __repr__(self):
        return self.name + "(" + str(self.coffeeCapacity) + "," + str(self.number) + ")"


def aggregateItems(items, item):
    newItems = []
    for i in items:
        newItems.append(Item(i.coffeeCapacity,i.number))
    found = 0
    for i in newItems:
        if i.coffeeCapacity == item.coffeeCapacity:
            i.number += item.number
            found = 1
    if not found:
        newItems.append(item)
    return newItems

def convertFridge(itemsInFridge):
    newItems = []
    for i in itemsInFridge:
        newItems = aggregateItems(newItems,Item(i,1))
    return newItems

def removeItem(items, number):
    new =convertFridge(items)
    for i in new:
       if i.number == number:
           i.number =-1
           if i.number == 0:
               new.remove(i)
    return new

def isEmpty(itemsInFridge):
    nothel = False
    for i in itemsInFridge:
        if i.coffeeCapacity < 0:
            nothel = True
    return nothel


def biggestCofeinInFridge(itemsInFridge):
    biggest = itemsInFridge[0]
    for item in itemsInFridge:
        if item > biggest:
            biggest = item
    return biggest


def isWorkingAllNight(itemsInFridge, cofeinRequired):
    sumCofein = 0

    for item in itemsInFridge:
        if item > 0:
            sumCofein += item
    
    if sumCofein >= cofeinRequired:
         if sumCofein < 1.1 * cofeinRequired:
             return 0
         else: 
             return 1
    else:
        return -1


def whenCallSupplier(itemsInFridge, constumptions):
    sumCofein = 0
    cnt = 0
    for item in itemsInFridge:
        if item > 0:
            sumCofein += item
    for item in constumptions:
        sumCofein -= item
        if sumCofein < 0:
            return cnt
        cnt += 1
    return -1


# itemsToAgregate= [Item(20,1),Item(10,2),Item(-10,3)]
# print (aggregateItems([],Item(20,1)))
# print (aggregateItems(itemsToAgregate,Item(20,2)))
# print (aggregateItems(itemsToAgregate,Item(11,2)))


print(convertFridge([0,1,2,4,1,0,-1]))


# print(whenCallSupplier([1,-3,1,10],[20,30,1,2,0,15]))
# print(whenCallSupplier([1,-3,1,10],[5,4,1,2,0,15]))
# print(whenCallSupplier([1,-3,1,10],[7,8,1,2,0,15]))
# print(whenCallSupplier([1,-3,1,10],[2,3,1,2,0,3]))
# print(whenCallSupplier([1,-3,1,2,1],[2,9,1,2]))
# print(whenCallSupplier([1,-3,3,2,1],[2,5,1,2]))

# print(isWorkingAllNight([1, -3,1,10], 20))
# print(isWorkingAllNight([1, -3,9,10], 20))
# print(isWorkingAllNight([13, -3,13,10], 20))



# print(biggestCofeinInFridge([1, 34, 1, 5, 23, -13, 12, 0, 324, 1, -1000]))
# print(biggestCofeinInFridge([1, 0, 3])) 
