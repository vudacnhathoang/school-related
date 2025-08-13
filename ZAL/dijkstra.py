class Vertex:
    def __init__(self, id, name):
        self.id = id
        self.name = name
        self.edges= []
        self.minDistance = float('inf')
        self.previousVertex = None
        self.visited = False


class Edge:
    def __init__(self, source, target, weight):
        self.source = source
        self.target = target
        self.weight = weight


class Dijkstra:
    def __init__(self):
        self.vertexes = []

    def computePath(self, sourceId):
        for vertex in self.vertexes:
            if vertex.id == sourceId:
                curr = vertex
                break
        curr.minDistance = 0
        curr.visited = True
        while True:
            minDistance = float('inf')
            for edge in curr.edges:
                for vertex in self.vertexes:
                    if vertex.id == edge.target and vertex.visited == False:
                        if vertex.minDistance >= curr.minDistance + edge.weight:
                            vertex.minDistance = curr.minDistance + edge.weight
                            vertex.previousVertex = curr

                        break
            for vertex in self.vertexes:
                if vertex.minDistance < minDistance and vertex.visited == False:
                    minDistance = vertex.minDistance
                    nextV = vertex
            if minDistance == float('inf'):
                break
            curr = nextV
            curr.visited = True


        
    def getShortestPathTo(self, targetId):
        for vertex in self.vertexes:
            if vertex.id == targetId:
                target = vertex
                break
        path = []
        path.insert(0, target)
        while target.previousVertex != None:
            path.insert(0,target.previousVertex)
            target = target.previousVertex
        return path

    def createGraph(self, vertexes, edgesToVertexes):
        self.vertexes = vertexes
        for i in self.vertexes:
            for j in edgesToVertexes:
                if j.source == i.id:
                    i.edges.append(j)
        

    def resetDijkstra(self):
        for vertex in self.vertexes:
            vertex.minDistance = float('inf')
            vertex.previousVertex = None
            vertex.visited = False

    def getVertexes(self):
        return self.vertexes


# vertexes = [
#   Vertex(0, 'Redville'),
#   Vertex(1, 'Blueville'),
#   Vertex(2, 'Greenville'),
#   Vertex(3, 'Orangeville'),
#   Vertex(4, 'Purpleville')
# ]
# edges = [
#   Edge(0, 1, 9),
#   Edge(0, 2, 1),
#   Edge(0, 3, 4),
#   Edge(1, 0, 5),
#   Edge(1, 2, 3),
#   Edge(1, 4, 3),
#   Edge(2, 0, 3),
#   Edge(2, 1, 3),
#   Edge(3, 0, 7),
#   Edge(3, 4, 1),
#   Edge(4, 1, 8),
#   Edge(4, 3, 8)
# ]

# dijkstra = Dijkstra()
# dijkstra.createGraph(vertexes,edges)
# dijkstra.computePath(0)
# dijkstra.resetDijkstra()
# dijkstra.computePath(1)

# # # # print (str(dijkstra.vertexes[1].minDistance))
# # print (dijkstra.getShortestPathTo(1))


# dijkstra2 = Dijkstra()
# dijkstra2.createGraph(vertexes,edges)
# dijkstra2.computePath(0)
# print (str(dijkstra2.vertexes[1].minDistance))
# print (dijkstra2.getShortestPathTo(1))


