

def getPlanetIndex(planets,planet):
    for i in range(len(planets)):
        if planets[i] == planet:
            return i
def getPlanetName(planets, index):
    for i in range(len(planets)):
        if i == index:
            return planets[i]
    
def getMinEnergyTargetPlanet(hyperTable,planets,planet):
    i = int(getPlanetIndex(planets,planet))
    mini = -1
    index = 0
    for j in range(len(hyperTable[[0][0]][i])):
        if hyperTable[[0][0]][i][j] < mini and mini[[0][0]][i][j] != -1:
            mini = mini[i][j]
            index = j
    return getPlanetName(planets,index)



hyperTable = [
        [(-1, 40, 20, 150, 130, 210),
         (140, -1, 335, 70, 45, 198),
         (120, 125, -1, 20, 60, 164),
         (140, 45, 50, -1, 125, 157),
         (230, 45, 60, 112, -1, 151),
         (218, 158, 166, 82, 15, -1)]
    ]
planets = ["Tatooine", "Naboo", "Hoth", "Devaron", "Dantooine", "Alderaan"]


print(getMinEnergyTargetPlanet(hyperTable,planets,"Naboo"))
# print(hyperTable[[0][0]][1][1])
