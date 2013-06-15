'''
Created on 2013-6-15

@author: mebusw@gmail.com
'''
import unittest
from pokerHands2 import Game, Hand


class GameTest(unittest.TestCase):


    def setUp(self):
        self.game = Game()


    def tearDown(self):
        pass


    def testHighCards(self):
        self.assertGreater(Hand('2C 3H 4S 8C AH'), Hand('2H 3D 5S 9C KD'))

    # def testFlushBeatHighCards(self):
    #     self.assertGreater(Hand('2H 3D 5S 9C KD'), Hand('2C 3H 4S 8C AH'))

        
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
