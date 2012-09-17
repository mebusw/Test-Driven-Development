'''
Created on 2012-9-17

Betrayal at House On the Hill
'''
        
class Tiles():
    def __init__(self):
        self.children = []
        self.visited = []
        self.maxWidth = 0
        self.maxHeight = 0
        
    def add(self, box):
        self.children.append(box)
    
    def count(self):
        self.visited = [False] * len(self.children)
        return len(self.children)
    
    def DFSsearch(self, n, coord):
        '''when yield used, the function 
            generate a iterator'''
        #print 'DFSsearch of Tiles'

        if None == n or self.visited[n] :
            return
        
        box = self.children[n]
        box.coord = coord
        self.visited[n] = True
        yield box
        
        for aaa in box.adj:
            for i in self.DFSsearch(aaa, (0, 0)):
                yield i
        
    def __iter__(self):
        print '__iter__ of Tiles'
        return TilesIterator(self)
    

class Vector2():
    def __init__(self, x=0, y=0):
        self.x = x
        self.y = y
        
    def __str__(self):
        return '<%s,%s>' % (self.x, self.y)
    
    def __add__(self, addee):
        return Vector2(self.x + addee.x, self.y + addee.y)
    
    def __cmp__(self, other):
        return (self.x - other.x) + (self.y - other.y)
     
class Tile():
    def __init__(self, name, *args):
        self.name = name
        '''adj: NESW'''
        self.adj = args
        self.sideLen = 1
        self.coord = Vector2(5, 6)
       
        
    
class TilesIterator():
    def __init__(self, obj):
        self.obj = obj
        self.a = 5

    def next(self):
        #print 'next before %d' % self.a
        if self.a > 8:
            raise StopIteration
        #print 'after %d' % self.a
        self.a += 1
        return self.a
