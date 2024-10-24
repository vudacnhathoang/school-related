def writeTextToFile(argument):
    STATICKY_TEXT = "This is my static text which must be added to file. It is very long text and I do not know what they want to do with this terrible text. "
    STATICKY_TEXT = STATICKY_TEXT + str(argument)
    file = "random.txt"
    f = open(file, "w")
    f.write(STATICKY_TEXT)
    f.close()
    return file

