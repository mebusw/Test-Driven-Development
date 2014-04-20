'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''

class MonopolyGame:
    def __init__(self):
        pass

    def setupWithPlayerCount(self, playerCount):
        self.playerCount = playerCount
        self.board = [None] * 40
        self.communityChestPile = []
        self.chancePile = []
        self.decideFirstPlayer()

    def roll(self):
        return -1

    def decideFirstPlayer(self):
        max_roll_player = 0
        max_roll = 0
        for i in xrange(self.playerCount):
            r = self.roll() 
            if r > max_roll:
                max_roll = r
                max_roll_player = i
        self.currentPlayer = 1
