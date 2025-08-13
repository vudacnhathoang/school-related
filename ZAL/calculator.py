
def addition(x, y):
    return x + y


def subtraction(x, y):
    return x - y


def multiplication(x, y):
    return x * y


def division(x, y):
   if y == 0:
        raise ValueError('This operation is not supported for given input parameters')
   return x/y
def modulo(x, y):
    if y > x or y <= 0:
        raise ValueError('This operation is not supported for given input parameters')
    return x % y
def secondPower(x):
    return x * x

def power(x, y)->float:
    if y < 0:
        raise ValueError('This operation is not supported for given input parameters')
    return  float(x ** y)

def secondRadix(x)->float:
    if  x <= 0:
        raise ValueError('This operation is not supported for given input parameters')
    return x ** 0.5


def magic(x, y, z, k)->float:
    l = x + k
    m = y + z
    if m == 0:
        raise ValueError('This operation is not supported for given input parameters')
    return l/m + 1

def control(a, x, y, z, k):
    if not all(isinstance(i, (int, float)) for i in [x, y, z, k]):
        raise ValueError('This operation is not supported for given input parameters')
    if a == "ADDITION":
        return addition(x, y)
    elif a == "SUBTRACTION":
        return subtraction(x,y)
    elif a == "MULTIPLICATION":
        return multiplication(x,y)
    elif a == "DIVISION":
        return division(x,y)
    elif a == "MOD":
        return modulo(x,y)
    elif a == "SECONDPOWER":
        return secondPower(x)
    elif a == "POWER":
        return power(x,y)
    elif a == "SECONDRADIX":
        return secondRadix(x)
    elif a == "MAGIC":
        return magic(x,y,z,k)
    else:
        raise ValueError('This operation is not supported for given input parameters')


# print(control("ADDITION",0.5,3,4,5))
# print(control("SUBTRACTION",20.5,3,4,5))
# print(control("MULTIPLICATION",2,3,4,5))
# print(control("DIVISION",2,3,4,5))
# print(control("MOD",3,3,4,5))
# print(control("SECONDPOWER",2,3,4,5))
# print(control("POWER",5,23,4,5))
# print(control("SECONDRADIX",0,3,4,5))
# print(control("MAGIC",2,3,4,5))