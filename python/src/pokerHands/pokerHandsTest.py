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
        self.assertEqual(8, self.game.rank)

    def testPair1(self):
        hand = '2H 2H 5S 9C KD'
        self.assertEqual('pair', self.game.category(hand))
        self.assertEqual({'2H':2, '5S':1, '9C':1, 'KD':1}, self.game.counter)
        self.assertEqual(('2H', 2), self.game.sortedCounter[0])

    def testPair2(self):
        hand = '2H 3D 5S KS KD'
        self.assertEqual('pair', self.game.category(hand))

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

    def testFullHouse1(self):
        hand = '3S 3S KD KD KD'
        self.assertEqual('full house', self.game.category(hand))
        self.assertEqual(('KD', 3), self.game.sortedCounter[0])
        self.assertEqual(('3S', 2), self.game.sortedCounter[1])

    
    def testFullHouse2(self):
        hand = '2H 4S 4C 2D 4H'
        self.game.category(hand)
        self.assertEqual(2, self.game.rank)

    def testFourOfAKind(self):
        hand = '3S KD KD KD KD'
        self.assertEqual('four of a kind', self.game.category(hand))
        self.assertEqual(('KD', 4), self.game.sortedCounter[0])

    def testStraigthFlush(self):
        hand = 'TD JD QD KD AD'
        self.assertEqual('straight flush', self.game.category(hand))
        self.assertEqual(('AD', 1), self.game.sortedCounter[0])
        self.assertEqual(0, self.game.rank)
        
    def testWinsHighCard1(self):
        self.game.category('2H 3D 5S 9C KD')
        self.assertEqual(8, self.game.rank)
        highest1 = self.game.sortedCounter[0][0]

        self.game.category('2C 3H 4S 8C AH')
        self.assertEqual(8, self.game.rank)
        highest2 = self.game.sortedCounter[0][0]
        
        self.assertTrue(self.game.compareCards(highest1, highest2) < 0)

    def testWinsFullHouse(self):
        self.game.category('2H 4S 4C 2D 4H')
        self.assertEqual(2, self.game.rank)
        highest1 = self.game.sortedCounter[0][0]

        self.game.category('2S 8S AS QS 3S')
        self.assertEqual(3, self.game.rank)
        highest2 = self.game.sortedCounter[0][0]
        
    def testWinsHighCard2(self):
        self.game.category('2H 3D 5S 9C KS')
        self.assertEqual(8, self.game.rank)
        highest1 = self.game.sortedCounter[0][0]

        self.game.category('2C 3H 4S 8C KD')
        self.assertEqual(8, self.game.rank)
        highest2 = self.game.sortedCounter[0][0]
        
        self.assertTrue(self.game.compareCards(highest1, highest2) > 0)

    def testTie(self):
        self.game.category('2H 3D 5S 9C KD')
        self.assertEqual(8, self.game.rank)
        highest1 = self.game.sortedCounter[0][0]

        self.game.category('2H 3D 5S 9C KD')
        self.assertEqual(8, self.game.rank)
        highest2 = self.game.sortedCounter[0][0]

        self.assertTrue(self.game.compareCards(highest1, highest2) == 0)

        
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
