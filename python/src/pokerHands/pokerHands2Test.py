'''
Created on 2013-6-15

@author: mebusw@gmail.com
'''
import unittest
from pokerHands2 import Hand


class HandTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testHighCards_HigherShouldWin(self):
        self.assertGreater(Hand('2C 3H 4S 8C AH'), Hand('2H 3D 5S 9C KD'))

    def testHighCards_ShouldIgnoreSuites(self):
        self.assertEqual(Hand('2C 3H 4S 8C AH'), Hand('2H 3D 4S 8C AD'))

    def testOnePairBearHighCard(self):
        self.assertGreater(Hand('2H 3D 5S KS KD'), Hand('2C 3H 4S 8C AH'))    
        
    def testTwoPairsBeatOnePair(self): 
        self.assertGreater(Hand('2C 4H 4S 8C 8H'), Hand('2H 3D 5S 5S 3D'))  

    def testThreeKindBeatTwoPairs(self): 
        self.assertGreater(Hand('2H 5S 5S 5S KD'), Hand('2H 3D 5S 5S 3D'))  

    def testStraightBeatThreeKind(self):
        self.assertGreater(Hand('9S TS JC QS KD'), Hand('2C 3H 4S 5C 6H'))        

    def testFlushBeatStraight(self):
        self.assertGreater(Hand('9S 3S JS QS KS'), Hand('9S TS JC QS KD'))  

    def testFullHouseBeatFlush(self):
        self.assertGreater(Hand('5C 3H 3S 3C 5H'), Hand('5S 9S 7S 2S 3S'))

    def testBiggerFullHouseBeatSmallerFullHouse(self):
        self.assertGreater(Hand('5C 6H 6S 6C 5H'), Hand('4S 4D 4S 6S 6S'))

    def testFourKindsBeatFullHouse(self):
        self.assertGreater(Hand('9S 9S 9S 9S 6S'), Hand('2C 3H 3S 3C 2H'))

    def testStraightFlushBeatFourKind(self):
        self.assertGreater(Hand('5C 6C 7C 8C 9C'), Hand('9S 9S 9S 9S 6S'))

    def testReduceD(self):
        print Hand('5C 6C 7C 8C 9C')._reduceD([6,6,5,3,3])
        print Hand('5C 6C 7C 8C 9C')._reduceD([3,3,3])
        print reduce(lambda x,y: x, range(6), [1,1])

    def testStraight_Minimal(self):
        self.assertGreater(Hand('2C 3H 4S 5C 6H'), Hand('AC 2H 3S 4C 5H'))

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
