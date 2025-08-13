class Node:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


class BinarySearchTree:
    def __init__(self):
        self.root = None
        self.cnt = 0

    def insert(self, value):
        if self.root == None:
            self.root = Node(value)
            return
        cur = self.root
        while cur != None:
            if cur.value > value:
                if cur.left == None:
                    cur.left = Node(value)
                    return
                cur = cur.left
            elif cur.value < value:
                if cur.right == None:
                    cur.right =Node(value)
                    return
                cur = cur.right

    def fromArray(self, array):
        for i in array:
            self.insert(i)


    def search(self, value):
        cur = self.root
        self.cnt = 1
        while cur != None:
            if cur.value == value:
                return True
            elif cur.value < value:
                cur =cur.right
                self.cnt += 1
            elif cur.value > value:
                cur = cur.left
                self.cnt +=1
        self.cnt -= 1
        return False

    def min(self):
        cur = self.root
        self.cnt = 1
        while cur.left != None:
            cur = cur.left
            self.cnt +=1
        return cur.value

    def max(self):
        cur = self.root
        self.cnt = 1
        while cur.right != None:
            cur = cur.right
            self.cnt +=1
        return cur.value
    def visitedNodes(self):
        return self.cnt

# bst2 = BinarySearchTree()
# bst2.fromArray([5, 3, 1, 4, 7, 6, 8])

# print(bst2.search(5))
# print(bst2.visitedNodes())
# print(bst2.search(7))
# print(bst2.visitedNodes())
# print(bst2.search(6))
# print(bst2.visitedNodes())
# print(bst2.search(10))
# print(bst2.visitedNodes())
# print('MIN:' + str(bst2.min()))
# print(bst2.visitedNodes())
# print('MAX: ' + str(bst2.max()))
# print(bst2.visitedNodes())