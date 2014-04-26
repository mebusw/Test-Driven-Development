#!/usr/bin/python
# -*- coding: utf-8 -*-

'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''

import unittest
from mock import MagicMock, Mock
from monopoly import *

class GameTest(unittest.TestCase):
    def setUp(self):
        self.game = MonopolyGame()
        self.game.setupWithPlayerNames('Alice', 'Bob', 'Carol')
        self.Alice10M = self.game.players[0]
        self.Alice10M.balance = 10000
        self.Bob20M = self.game.players[1]
        self.Bob20M.balance = 20000
        self.Carol30M = self.game.players[2]
        self.Carol30M.balance = 30000

    def tearDown(self):
        pass

    def testSetupBoardAndEquipmentFor3Player(self):
        self.assertEquals(3, self.game.playerCount())
        self.assertEquals(MAX_MOVE, len(self.game.board))
        self.assertIsNotNone(self.game.communityChestPile)
        self.assertIsNotNone(self.game.chancePile)
        self.assertEqual(self.Alice10M, self.game.getCurrentPlayer())
        self.assertEqual(32, self.game.houseCount)
        self.assertEqual(12, self.game.hotelCount)

    def testCreatePlayers(self):
        self.assertEqual(3, len(self.game.players))
        self.assertEqual('Alice', self.Alice10M.name)
        self.assertEqual(0, self.Alice10M.pos)

    def testPlayerWhoRollHighestDiceBecomeFirstPlayer(self):
        self._mockTheRoll(4, 5, 2)

        self.game.decideFirstPlayer()
        
        self.assertEquals(1, self.game.currentPlayer)


    def testFirstPlayerRollDiceToMove(self):
        ROLL = 6
        
        self.game.getCurrentPlayer().roll(ROLL)

        self.assertEquals(0 + ROLL, self.Alice10M.pos)


    def testSecondPlayerRollDiceToMoveReversely(self):
        ROLL = 6
        self.Alice10M.pos = 38
        
        self.game.getCurrentPlayer().roll(ROLL)

        self.assertEquals(38 + ROLL - MAX_MOVE, self.Alice10M.pos)

    def testPlayerReceive200WhenPassTheStartingPoint(self):
        ROLL = 6
        self.Alice10M.pos = 38
        
        self.game.getCurrentPlayer().roll(ROLL)

        self.assertEquals(10000 + 200, self.Alice10M.balance)

    def testTurnToNextPlayer(self):
        self.assertEquals(self.Alice10M, self.game.getCurrentPlayer())

        self.game.turnToNextPlayer()

        self.assertEquals(self.Bob20M, self.game.getCurrentPlayer())

        self.game.turnToNextPlayer()

        self.assertEquals(self.Carol30M, self.game.getCurrentPlayer())

        self.game.turnToNextPlayer()

        self.assertEquals(self.Alice10M, self.game.getCurrentPlayer())

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

        self.Alice10M.withdraw(AMOUNT)
        
        self.assertEquals(10000 + AMOUNT, self.Alice10M.balance)

    def testPlayerCanPayToBank(self):
        AMOUNT = 100

        self.Alice10M.toll(AMOUNT)
        
        self.assertEquals(10000 - AMOUNT, self.Alice10M.balance)

    def testPlayerCanPurchasePropertyWhenLandOnUnownedParcel(self):
        PRICE = 300
        sectionChina = RealEstateSection('China')
        unownedProperty = RealEstateSpot('Shanghai', PRICE, sectionChina)

        self.Alice10M.purchaseProperty(unownedProperty)
        
        self.assertIn(unownedProperty, self.Alice10M.properties)
        self.assertEquals(self.Alice10M, unownedProperty.owner)
        self.assertEquals(10000 - PRICE, self.Alice10M.balance)

    def testPlayerMustRentWhenHeStandOnOthersRealEstateSpot(self):
        sectionChina = RealEstateSection('China')
        Shanghai = RealEstateSpot('Shanghai', 1000, sectionChina)
        Shanghai.owner = self.Bob20M
        rentPrice = Shanghai.rentPrice()

        self.Alice10M.rent(Shanghai)

        self.assertEquals(10000 - rentPrice, self.Alice10M.balance)
        self.assertEquals(20000 + rentPrice, self.Bob20M.balance)

    def testPlayerCanMakeASettlementWhenBankruptFirstTime(self):
        self.Alice10M.isMakingSettlement = False

        self.Alice10M.toll(10010)

        self.assertEqual(None, self.game.winner)
        self.assertTrue(self.Alice10M.isMakingSettlement)

    def testGameOverAndRichestWinWhenAPlayerBankruptAgain(self):
        self.Alice10M.isMakingSettlement = True

        self.Alice10M.toll(10010)

        self.assertEqual(self.Carol30M, self.game.winner)


    def testPlayerCanMotageUnimprovedLandToBank(self):
        sectionChina = RealEstateSection('China')
        Shanghai = RealEstateSpot('Shanghai', 1000, sectionChina)
        Shanghai.owner = self.Alice10M

        self.Alice10M.motage(Shanghai)

        self.assertEquals(10000 + Shanghai.purchasePrice() * 0.5, self.Alice10M.balance)

    def testPlayerMoveToJailWhenStopAtGoToJailLand(self):
        self.game.board[30] = ArrestingSpot()
        self.Alice10M.pos = 30
        
        self.Alice10M.resolveSpotRule()

        self.assertEqual(10, self.Alice10M.pos)

    def testPlayerMoveToJailWhenDrewGoToJailCard(self):
        pass

    def testPlayerMoveToJailWhenRollThreeDoubles(self):
        pass

    def testPlayerCanGetOutOfJailAndMoveWhenRollADouble(self):
        ROLL = 4
        self.Alice10M.pos = 10
        self.Alice10M.isInJail = True

        self.game.getCurrentPlayer().roll(ROLL)

        self.assertEquals(10 + ROLL, self.Alice10M.pos)
        self.assertFalse(self.Alice10M.isInJail)

    def testPlayerCanNotGetOutOfJailWhenRollASingle(self):
        ROLL = 3
        self.Alice10M.pos = 10
        self.Alice10M.isInJail = True

        self.game.getCurrentPlayer().roll(ROLL)

        self.assertEquals(10, self.Alice10M.pos)
        self.assertTrue(self.Alice10M.isInJail)

    def testPlayerCanPay50ToGetOutOfJail(self):
        self.Alice10M.pos = 10
        self.Alice10M.isInJail = True

        self.game.getCurrentPlayer().payForFreedom()

        self.assertEquals(10, self.Alice10M.pos)
        self.assertFalse(self.Alice10M.isInJail)

class SpotTest(unittest.TestCase):
    def setUp(self):
        pass
    def testSpotCreated(self):
        spot = Spot('somewhere')

        self.assertIsNotNone(spot)
        self.assertEquals('somewhere', spot.name)

class RealEstateSpotTest(unittest.TestCase):
    def setUp(self):
        self.sectionGreen = RealEstateSection('Green')
        self.greenLand1 = RealEstateSpot('威尼斯大街', 320, self.sectionGreen)
        self.greenLand2 = RealEstateSpot('汤姆大街', 360, self.sectionGreen)
        self.greenLand3 = RealEstateSpot('太平洋大街', 300, self.sectionGreen)

    def testPurchasePriceOfRealEstateSpotDependsOnPriceAndBuildingLevel(self):
        self.assertAlmostEquals(320 * 0.15, self.greenLand1.rentPrice())

        self.greenLand1.buildingLevel = 1

        self.assertAlmostEquals(320 * 0.15 * 2, self.greenLand1.rentPrice())

        self.greenLand1.buildingLevel = 2

        self.assertAlmostEquals(320 * 0.15 * 3, self.greenLand1.rentPrice())

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
