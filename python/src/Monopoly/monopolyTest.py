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
        self.game.setupWithPlayerCount(3)

    def tearDown(self):
        pass

    def testSetupBoardAndEquipmentFor3Player(self):
        self.assertEquals(3, self.game.playerCount)
        self.assertEquals(40, len(self.game.board))
        self.assertIsNotNone(self.game.communityChestPile)
        self.assertIsNotNone(self.game.chancePile)

    def testRollDiceToDecideFirstPlayer(self):
        self._mockTheRoll(4, 5, 2)

        self.game.decideFirstPlayer()
        
        self.assertEquals(1, self.game.currentPlayer)


    def testFirstPlayerRollDiceToMove(self):
        ROLL = 6
        self._mockTheRoll(ROLL)
        
        self.game.currentPlayerMove()

        self.assertEquals(0, self.game.currentPlayer)
        self.assertEquals(0 + ROLL, self.game.playersPos[0])


    def testSecondPlayerRollDiceToMoveReversely(self):
        ROLL = 6
        self.game.currentPlayer = 1
        self.game.playersPos[1] = 38
        self._mockTheRoll(ROLL)
        
        self.game.currentPlayerMove()

        self.assertEquals(1, self.game.currentPlayer)
        self.assertEquals(38 + ROLL - self.game.MAX_MOVE, self.game.playersPos[1])


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
