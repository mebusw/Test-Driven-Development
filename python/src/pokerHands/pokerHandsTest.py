'''
Created on 2012-12-4

@author: mebusw
'''
import unittest
from pokerHands import Game

#TODO
#//sort by value then suit
#//game.counter should be ordered to find the highest    sorted(a.items(), lambda x,y: cmp(x[1],y[1]))

class GameTest(unittest.TestCase):


    def setUp(self):
        self.game = Game()


    def tearDown(self):
        pass


    def testHighCard(self):
        hand = '2H 3D 5S 9C KD'
        self.assertEqual('high card', self.game.category(hand))
        self.assertEqual(('KD', 1), self.game.sortedCounter[0])
        
        hand = '2C 3H 4S 8C AH'
        self.assertEqual('high card', self.game.category(hand))
        self.assertEqual(['2C', '3H', '4S', '8C', 'AH'], self.game.cards)
        self.assertEqual(('AH', 1), self.game.sortedCounter[0])

    def testHighCardWithSameValue(self):        
        hand = '2H 3D 5S KS KD'
        self.assertEqual('high card', self.game.category(hand))
        self.assertEqual(('KS', 1), self.game.sortedCounter[0])


    def testPair(self):
        hand = '2H 2H 5S 9C KD'
        self.assertEqual('pair', self.game.category(hand))
        self.assertEqual({'2H':2, '5S':1, '9C':1, 'KD':1}, self.game.counter)
        self.assertEqual(('2H', 2), self.game.sortedCounter[0])

    def testTwoPairs(self):
        hand = '2H 2H 5S 5S KD'
        self.assertEqual('two pairs', self.game.category(hand))
        self.assertEqual({'2H':2, '5S':2, 'KD':1}, self.game.counter)
        self.assertEqual(('5S', 2), self.game.sortedCounter[0])
        self.assertEqual(('2H', 2), self.game.sortedCounter[1])
        self.assertEqual(('KD', 1), self.game.sortedCounter[2])

    def testThreeOfAKind(self):
        hand = '2H 5S 5S 5S KD'
        self.assertEqual('three of a kind', self.game.category(hand))
        self.assertEqual({'2H':1, '5S':3, 'KD':1}, self.game.counter)
        self.assertEqual([('5S', 3), ('KD', 1), ('2H', 1)], self.game.sortedCounter)
        self.assertEqual(('5S', 3), self.game.sortedCounter[0])

    def testStraight(self):
        hand = '2H 3S 4S 5S 6D'
        self.assertEqual('straight', self.game.category(hand))
        self.assertEqual(('6D', 1), self.game.sortedCounter[0])

    def testFlush(self):
        hand = '9S 3S 4S KS 6S'
        self.assertEqual('flush', self.game.category(hand))
        self.assertEqual(('KS', 1), self.game.sortedCounter[0])
        self.assertEqual(('9S', 1), self.game.sortedCounter[1])

    def testFullHouse(self):
        hand = '3S 3S KD KD KD'
        self.assertEqual('full house', self.game.category(hand))
        self.assertEqual(('KD', 3), self.game.sortedCounter[0])
        self.assertEqual(('3S', 2), self.game.sortedCounter[1])

    def testFourOfAKind(self):
        hand = '3S KD KD KD KD'
        self.assertEqual('four of a kind', self.game.category(hand))
        self.assertEqual(('KD', 4), self.game.sortedCounter[0])


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
