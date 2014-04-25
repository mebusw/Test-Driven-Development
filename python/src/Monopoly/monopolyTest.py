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
        self.Alice = self.game.players[0]
        self.Bob = self.game.players[1]
        self.Carol = self.game.players[2]

    def tearDown(self):
        pass

    def testSetupBoardAndEquipmentFor3Player(self):
        self.assertEquals(3, self.game.playerCount)
        self.assertEquals(40, len(self.game.board))
        self.assertIsNotNone(self.game.communityChestPile)
        self.assertIsNotNone(self.game.chancePile)

    def testCreatePlayers(self):
        self.assertEqual(3, len(self.game.players))
        self.assertEqual('Alice', self.Alice.name)
        self.assertEqual(0, self.Alice.pos)

    def testPlayerWhoRollHighestDiceBecomeFirstPlayer(self):
        self._mockTheRoll(4, 5, 2)

        self.game.decideFirstPlayer()
        
        self.assertEquals(1, self.game.currentPlayer)


    def testFirstPlayerRollDiceToMove(self):
        ROLL = 6
        self._mockTheRoll(ROLL)
        
        self.game.currentPlayerMove()

        self.assertEquals(0, self.game.currentPlayer)
        self.assertEquals(0 + ROLL, self.Alice.pos)


    def testSecondPlayerRollDiceToMoveReversely(self):
        ROLL = 6
        self.game.currentPlayer = 1
        self.Bob.pos = 38
        self._mockTheRoll(ROLL)
        
        self.game.currentPlayerMove()

        self.assertEquals(1, self.game.currentPlayer)
        self.assertEquals(38 + ROLL - self.game.MAX_MOVE, self.Bob.pos)

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

        self.Alice.withdraw(AMOUNT)
        
        self.assertEquals(AMOUNT, self.Alice.balance)

    def testPlayerCanPayToBank(self):
        AMOUNT = 100
        self.Alice.balance = 200

        self.Alice.toll(AMOUNT)
        
        self.assertEquals(200 - AMOUNT, self.Alice.balance)

    def testPlayerCanPurchasePropertyWhenLandOnUnownedParcel(self):
        PRICE = 300
        sectionChina = PropertySection('China')
        unownedProperty = LandProperty('Shanghai', PRICE, sectionChina)
        self.Alice.balance = 2000

        self.Alice.purchaseProperty(unownedProperty)
        
        self.assertIn(unownedProperty, self.Alice.properties)
        self.assertEquals(2000 - PRICE, self.Alice.balance)

    def testPriceOfLandPropertyChangedAfterAnotherInSameSectionBeBought(self):
        sectionChina = PropertySection('China')
        Shanghai = LandProperty('Shanghai', 1000, sectionChina)
        Beijing = LandProperty('Beijing', 1500, sectionChina)
        Tianjin = LandProperty('Tianjin', 1200, sectionChina)

        self.assertEquals(3, len(sectionChina.landProperties))
        self.assertEquals(1500, Beijing.price)

        self.Alice.purchaseProperty(Tianjin)
        self.assertEquals(1200, Shanghai.updatedPrice())        

        self.Alice.purchaseProperty(Beijing)
        self.assertEquals(1440, Shanghai.updatedPrice())        

        self.Alice.purchaseProperty(Shanghai)
        self.assertAlmostEquals(1728, Shanghai.updatedPrice())        

    def testPlayerMustRentWhenHeStandOnOthersLandProperty(self):
        sectionChina = PropertySection('China')
        Shanghai = LandProperty('Shanghai', 1000, sectionChina)
        Shanghai.owner = self.Bob
        RENT_RATE = 0.15
        self.Alice.balance = 2000
        self.Bob.balance = 3000

        self.Alice.rent(Shanghai)

        self.assertEquals(2000 - 1000 * 1.2 * RENT_RATE, self.Alice.balance)
        self.assertEquals(3000 + ga1000 * 1.2 * RENT_RATE, self.Bob.balance)



if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
