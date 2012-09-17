'''
Created on 2012-9-17


'''
import unittest
from BHOH import Tiles, Tile

class Test1(unittest.TestCase):

    def setUp(self):
        pass

    def testTileGenerator(self):
        tiles = Tiles()
        tiles.add(Tile('A', 1, 2))
        tiles.add(Tile('B', 0, 4, 3))
        tiles.add(Tile('C', 0, 4))
        tiles.add(Tile('D', 1))
        tiles.add(Tile('E', 1, 2))
        assert 5 == tiles.count()
        for i in tiles.search(0):
            print i.name

    def testTileIterator(self):
        tiles = Tiles()
        for i in tiles:
            print i
                   
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testTile']
    unittest.main()
