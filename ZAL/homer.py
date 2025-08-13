"""
Homer's fridge
Course: B0B36ZAL
"""

#nasledujici kod nijak nemodifikujte!
class Food:
    def __init__(self, name, expiration):
        self.name = name
        self.expiration = expiration
#predesly kod nijak nemodifikujte!

def openFridge(fridge):
    print("Following items are in Homer's fridge:")
    for food in fridge:
        print("{0} (expires in: {1} days)".format(
            str(food.name), str(food.expiration))
        )
    print("")

# test vypisu - pri odevzdani smazte, nebo zakomentujte
# fridge = [Food("beer", 4), Food("steak", 1), Food("hamburger", 1), Food("donut", 3)]
# openFridge(fridge)


"""
Task #1
"""
def maxExpirationDay(fridge):
    max = -1
    for food in fridge:
        if food.expiration >max:
            max = food.expiration
    return max

# test vypisu - pri odevzdani smazte, nebo zakomentujte
# print(maxExpirationDay(fridge))
# The command should print 4


"""
Task #2
"""
def histogramOfExpirations(fridge):
    list = [0] * (maxExpirationDay(fridge) + 1)
    for food in fridge:
        list[food.expiration] += 1
    return list
    

# test vypisu - pri odevzdani smazte, nebo zakomentujte
# print(histogramOfExpirations(fridge))
# The command should print [0, 2, 0, 1, 1]


"""
Task #3
"""
def cumulativeSum(histogram):
    list = [0] * len(histogram)
    sum = 0
    for i in range (len(histogram)):
        sum += histogram[i]
        list[i]= sum
    return list
# test vypisu - pri odevzdani smazte, nebo zakomentujte
# print(cumulativeSum([0, 2, 0, 1, 1]))
# The command should print [0, 2, 2, 3, 4]


"""
Task #4
"""
def sortFoodInFridge(fridge):
    cumulative = cumulativeSum(histogramOfExpirations(fridge))
    newFridge = [0] * len(fridge)
    for food in fridge:
        newFridge[cumulative[food.expiration]-1] = food
        cumulative[food.expiration] -= 1
    return newFridge

# test vypisu - pri odevzdani smazte, nebo zakomentujte
# openFridge(sortFoodInFridge(fridge))
# The command should print
# Following items are in Homer's fridge:
# hamburger (expires in: 1 days)
# steak (expires in: 1 days)
# donut (expires in: 3 days)
# beer (expires in: 4 days)


"""
Task #5
"""
def reverseFridge(fridge):
    newFridge = fridge.copy()
    leng = len(newFridge)
    for i in range(int(leng/2)):
        newFridge[i], newFridge[leng-i-1] = newFridge[leng-i-1], newFridge[i]
    return newFridge    

# test vypisu - pri odevzdani smazte, nebo zakomentujte
# openFridge(reverseFridge(fridge))
# The command should print
# Following items are in Homer's fridge:
# donut (expires in: 3 days)
# hamburger (expires in: 1 days)
# steak (expires in: 1 days)
# beer (expires in: 4 days)

# test vypisu - pri odevzdani smazte, nebo zakomentujte
# openFridge(sortFoodInFridge(reverseFridge(fridge)))
# The command should print
# Following items are in Homer's fridge:
# steak (expires in: 1 days)
# hamburger (expires in: 1 days)
# donut (expires in: 3 days)
# beer (expires in: 4 days)


"""
Task #6
"""
def eatFood(name, fridge):
    tmp = Food(name, float("inf"))
    found = False
    for food in fridge:
        if food.name == name and food.expiration < tmp.expiration:
            found = True
            tmp = food
    newFridge = fridge.copy()
    if found:
        newFridge.remove(tmp)
    return newFridge


            
# test vypisu - pri odevzdani smazte, nebo zakomentujte
# openFridge(
#     eatFood("donut",
#         [Food("beer", 4), Food("steak", 1), Food("hamburger", 1),
#         Food("donut", 3), Food("donut", 1), Food("donut", 6)]
#     ))
# The command should print
# Following items are in Homer's fridge:
# beer (expires in: 4 days)
# steak (expires in: 1 days)
# hamburger (expires in: 1 days)
# donut (expires in: 3 days)
# donut (expires in: 6 days)

# fridge = [Food("beer", 4), Food("steak", 1),  Food("donut", 3), Food("donut", 1),  Food("donut", 6)]

# openFridge(reverseFridge(fridge))

# openFridge(eatFood("donut", fridge))