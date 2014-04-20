'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''

import unittest
from mock import MagicMock, Mock
from monopoly import MonopolyGame

class GameTest(unittest.TestCase):
    def setUp(self):
        self.game = MonopolyGame()

    def tearDown(self):
        pass

    def testSetupBoardAndEquipmentFor2Player(self):
        self.game.setupWithPlayerCount(2)
        
        self.assertEquals(2, self.game.playerCount)
        self.assertEquals(40, len(self.game.board))
        self.assertIsNotNone(self.game.communityChestPile)
        self.assertIsNotNone(self.game.chancePile)

    def testRollDiceToDecideFirstPlayer(self):
        self._mockTheRoll(4, 5, 2)
        
        self.game.setupWithPlayerCount(3)
        
        self.assertEquals(3, self.game.playerCount)

        max_roll_player = 0
        max_roll = 0
        for i in xrange(self.game.playerCount):
            r = self.game.roll() 
            print r
            if r > max_roll:
                max_roll = r
                max_roll_player = i

        self.assertEquals(max_roll_player, self.game.currentPlayer)

    def _mockTheRoll(self, *seq):
        self.game.roll = Mock()
        returns = list(seq) + [Exception()]
        def side_effect(*args):
            result = returns.pop(0)
            if isinstance(result, Exception):
                raise result
            return result
        self.game.roll = Mock(side_effect=side_effect)        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
