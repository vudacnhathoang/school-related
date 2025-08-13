class Node:
    def __init__(self, nextNode, prevNode, data):
        self.prevNode = prevNode
        self.nextNode = nextNode
        self.data = data


class LinkedList:
    def __init__(self):
        self.head = None


class Car:
    def __init__(self, identification, name, brand, price, active):
        self.identification = identification
        self.name = name
        self.brand = brand
        self.price = price
        self.active = active


db = LinkedList()


def init(cars):
    for car in cars:
        add(car)


def add(car):
    if db.head == None:
        db.head = Node(None, None, car)
    else:
        current = db.head
        if current.data.price > car.price:
            new_node = Node(current, None, car)
            current.prevNode = new_node
            db.head = new_node
            return

        while current.nextNode != None:
            if current.data.price < car.price and current.nextNode.data.price > car.price:
                current.nextNode = Node(current.nextNode, current, car)
                current.nextNode.nextNode.prevNode = current.nextNode
                return
            elif current.data.price == car.price and current.nextNode.data.price  > car.price:
                current.nextNode = Node(current.nextNode, current, car)
                current.nextNode.nextNode.prevNode = current.nextNode
                return
            current = current.nextNode
        current.nextNode = Node(None, current, car)

def updateName(identification, name):
    current = db.head
    while current != None:
        if current.data.identification == identification:
            current.data.name = name
            return
        current = current.nextNode
    return None

def updateBrand(identification, brand):
    current = db.head
    while current != None:
        if current.data.identification == identification:
            current.data.brand = brand
            return
        current = current.nextNode
    return None

def activateCar(identification):
    current = db.head
    while current != None:
        if current.data.identification == identification:
            current.data.active = True 
            return
        current = current.nextNode
    return None

def deactivateCar(identification):
    current = db.head
    while current != None:
        if current.data.identification == identification:
            current.data.active = False 
            return
        current = current.nextNode
    return None

def getDatabaseHead():
    return db.head


def getDatabase():
    return db


def calculateCarPrice():
    sum = 0
    current = db.head
    while current != None:
        if current.data.active:
            sum += current.data.price
        current = current.nextNode
    return sum


def clean():
    db.head = None


# test_cars = [
#     Car(1, "Model S", "Tesla", 1, True),
#     Car(2, "Mustang", "Ford", 2, False),
#     Car(3, "Civic", "Honda", 39, True),
#     Car(4, "Camry", "Toyota", 5, True),
#     Car(5, "911 Carrera", "Porsche", 1, False),
#     Car(6, "Corvette", "Chevrolet", 1, True),
#     Car(7, "A4", "Audi", 39, False),
#     Car(8, "Model 3", "Tesla", 11, True),
#     Car(9, "F-150", "Ford", 10, False),
#     Car(10, "X5", "BMW", 10, True)
# ]

# init(test_cars)

# sum = calculateCarPrice()

# # print(sum)

# # clean()

# current = db.head

# while current != None:
#     print(current.data.name)
#     current = current.nextNode
