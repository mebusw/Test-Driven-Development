'''
Created on May 20, 2013

@author: 
'''
import unittest
from game import *

class Test(unittest.TestCase):


    def setUp(self):
        self.game = Game()


    def tearDown(self):
        pass


    def testResolveOnlyByStrengh_player1Win(self):
        self.game.player1_send_spawn(Gandalf())
        self.game.player2_send_spawn(Golum())        
        
        self.game.resolve()
        
        self.assertTrue(self.game.player1.alive)
        self.assertFalse(self.game.player2.alive)

    def testResolveOnlyByStrengh_player2Win(self):
        self.game.player1_send_spawn(Gandalf())
        self.game.player2_send_spawn(Aragon())        
        
        self.game.resolve()
        
        self.assertFalse(self.game.player1.alive)
        self.assertTrue(self.game.player2.alive)

    def testResolveOnlyByStrengh_draw(self):
        self.game.player1_send_spawn(Gandalf())
        self.game.player2_send_spawn(Saruman())        
        
        self.game.resolve()
        
        self.assertFalse(self.game.player2.alive)
        self.assertFalse(self.game.player1.alive)

    def testResolveByAblity_Lagolas_beat_Dragon(self):
        self.game.player1_send_spawn(Lagolas())
        self.game.player2_send_spawn(Dragon())        
        
        self.game.resolve()
        
        self.assertTrue(self.game.player1.alive)
        self.assertFalse(self.game.player2.alive)
        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()