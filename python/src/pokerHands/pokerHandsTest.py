'''
Created on 2012-12-4

@author: mebusw
'''
import unittest
from pokerHands import Game




class Test(unittest.TestCase):


    def setUp(self):
        self.game = Game()


    def tearDown(self):
        pass


    def testHighCard(self):
        hand = '2H 3D 5S 9C KD'
        self.assertEqual('high card', self.game.category(hand))
        self.assertEqual('K', self.game.highest)
        
        hand = '2C 3H 4S 8C AH'
        self.assertEqual('high card', self.game.category(hand))
        self.assertEqual('A', self.game.highest)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()