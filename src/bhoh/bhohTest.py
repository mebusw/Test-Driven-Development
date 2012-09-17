'''
Created on 2012-9-17


'''
import unittest
from bhoh import Tiles, Tile, Vector2

class TestTiles(unittest.TestCase):

    def setUp(self):
        pass

    def testTileGenerator(self):
        tiles = Tiles()
        tiles.add(Tile('A', None, 1, 2, None))
        tiles.add(Tile('B', None, 0, 4, 3))
        tiles.add(Tile('C', 0, 4, None, None))
        tiles.add(Tile('D', None, None, None, 1))
        tiles.add(Tile('E', 1, None, None, 2))
        assert 5 == tiles.count()
        
        result = []
        for i in tiles.DFSsearch(0, (0, 0)):
            #print i.name
            result.append(i.name)
        assert ['A', 'B', 'E', 'C', 'D'] == result

    def testTileIterator(self):
        tiles = Tiles()
        
        result = []
        for i in tiles:
            print i

    def testTileCoord(self):
        tile = Tile('Lobby', 5, 6)
        assert Vector2(5, 6) == tile.coord 
        
class TestVector2(unittest.TestCase):
    def testOp(self):
        vec2 = Vector2(2, 3)
        assert '<2,3>' == str(vec2)
        assert '<3,4>' == str(vec2 + Vector2(1, 1))
        assert Vector2(5, 6) == Vector2(5, 6) 
                  
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testTile']
    unittest.main()
