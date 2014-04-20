'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''

import unittest
from mock import MagicMock, Mock
from monopoly import MonopolyGame

class GameTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testSetupBoardAndEquipmentFor2Player(self):
        game = MonopolyGame()
        self.assertIsNotNone(game)



if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
