'''
Created on 2012-9-17

Betrayal at House On the Hill
'''
        
class Tiles():
    def __init__(self):
        self.boxes = []
        self.a = 5
        self.visited = []
        
    def add(self, box):
        self.boxes.append(box)
    
    def count(self):
        self.visited = [False] * len(self.boxes)
        return len(self.boxes)
    
    def search(self, n):
        box = self.boxes[n]
        if self.visited[n]:
            return
        yield box
        self.visited[n] = True
        for aaa in box.adj:
            for i in self.search(aaa):
                yield i
        
    def __iter__(self):
        print '__iter__ of Tiles'
        return TilesIterator(self)
    
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

class Tile():
    def __init__(self, name, *args):
        self.name = name
        self.adj = args
        print self.adj
        
