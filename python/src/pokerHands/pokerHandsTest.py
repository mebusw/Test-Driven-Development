'''
Created on 2012-12-4

@author: mebusw
'''
import unittest
from pokerHands import Game

#TODO
#//sort by value then suit
#game.counter should be ordered to find the highest

class GameTest(unittest.TestCase):


    def setUp(self):
        self.game = Game()


    def tearDown(self):
        pass


    def testHighCard(self):
        hand = '2H 3D 5S 9C KD'
        self.assertEqual('high card', self.game.category(hand))
        self.assertEqual('KD', self.game.highest)
        
        hand = '2C 3H 4S 8C AH'
        self.assertEqual('high card', self.game.category(hand))
        self.assertEqual(['2C', '3H', '4S', '8C', 'AH'], self.game.cards)
        self.assertEqual('AH', self.game.highest)

    def testHighCardWithSameValue(self):        
        hand = '2H 3D 5S KS KD'
        self.assertEqual('high card', self.game.category(hand))
        self.assertEqual('KS', self.game.highest)


    def testPair(self):
        hand = '2H 2H 5S 9C KD'
        self.assertEqual('pair', self.game.category(hand))
        self.assertEqual({'2H':2, '5S':1, '9C':1, 'KD':1}, self.game.counter)
        self.assertEqual('2H', self.game.highest)

    def testTwoPairs(self):
        hand = '2H 2H 5S 5S KD'
        self.assertEqual('two pairs', self.game.category(hand))
        self.assertEqual({'2H':2, '5S':2, 'KD':1}, self.game.counter)
        self.assertEqual('5S', self.game.highest)

    def testThreeOfAKind(self):
        hand = '2H 5S 5S 5S KD'
        self.assertEqual('three of a kind', self.game.category(hand))
        self.assertEqual({'2H':1, '5S':3, 'KD':1}, self.game.counter)
        self.assertEqual('5S', self.game.highest)

    def testStraight(self):
        hand = '2H 3S 4S 5S 6D'
#        self.assertEqual('straight', self.game.category(hand))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
