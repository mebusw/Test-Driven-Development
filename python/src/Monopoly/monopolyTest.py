'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''

import unittest
from mock import MagicMock, Mock
from monopoly import MonopolyGame
from monopoly import *

class GameTest(unittest.TestCase):
    def setUp(self):
        self.game = MonopolyGame()
        self.game.setupWithPlayerCount(3, 'Alice', 'Bob', 'Carol')

    def tearDown(self):
        pass

    def testSetupBoardAndEquipmentFor3Player(self):
        self.assertEquals(3, self.game.playerCount)
        self.assertEquals(40, len(self.game.board))
        self.assertIsNotNone(self.game.communityChestPile)
        self.assertIsNotNone(self.game.chancePile)

    def testCreatePlayers(self):
        self.assertEqual(3, len(self.game.players))
        self.assertEqual('Alice', self.game.players[0].name)
        self.assertEqual(0, self.game.players[0].pos)

    def testPlayerWhoRollHighestDiceBecomeFirstPlayer(self):
        self._mockTheRoll(4, 5, 2)

        self.game.decideFirstPlayer()
        
        self.assertEquals(1, self.game.currentPlayer)


    def testFirstPlayerRollDiceToMove(self):
        ROLL = 6
        self._mockTheRoll(ROLL)
        
        self.game.currentPlayerMove()

        self.assertEquals(0, self.game.currentPlayer)
        self.assertEquals(0 + ROLL, self.game.players[0].pos)


    def testSecondPlayerRollDiceToMoveReversely(self):
        ROLL = 6
        self.game.currentPlayer = 1
        self.game.players[1].pos = 38
        self._mockTheRoll(ROLL)
        
        self.game.currentPlayerMove()

        self.assertEquals(1, self.game.currentPlayer)
        self.assertEquals(38 + ROLL - self.game.MAX_MOVE, self.game.players[1].pos)

    def _mockTheRoll(self, *seq):
        self.game.roll = Mock()
        returns = list(seq) + [Exception()]
        def side_effect(*args):
            result = returns.pop(0)
            if isinstance(result, Exception):
                raise result
            return result
        self.game.roll = Mock(side_effect=side_effect)  


    def testPlayerCanWithdrawFromBank(self):
        AMOUNT = 100

        self.game.players[0].withdraw(AMOUNT)
        
        self.assertEquals(AMOUNT, self.game.players[0].balance)

    def testPlayerCanPayToBank(self):
        AMOUNT = 100
        self.game.players[0].balance = 200

        self.game.players[0].toll(AMOUNT)
        
        self.assertEquals(200 - AMOUNT, self.game.players[0].balance)

    def testPlayerCanPurchasePropertyWhenLandOnUnownedParcel(self):
        unownedProperty = LandProperty('Shanghai', 'China')
        PRICE = 300
        unownedProperty.price = PRICE
        self.game.players[0].balance = 2000

        self.game.players[0].purchaseProperty(unownedProperty)
        
        self.assertIn(unownedProperty, self.game.players[0].properties)
        self.assertEquals(2000 - PRICE, self.game.players[0].balance)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
