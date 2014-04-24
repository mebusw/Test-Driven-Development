'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''

class MonopolyGame:
    def __init__(self):
        self.MAX_MOVE = 40

    def setupWithPlayerCount(self, playerCount, *names):
        self.playerCount = playerCount
        self.bank = Bank()
        self.players = []
        for name in names:
            self.players.append(Player(name, self.bank))
        self.board = [None] * self.MAX_MOVE
        self.communityChestPile = []
        self.chancePile = []
        self.currentPlayer = 0

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
        self.currentPlayer = max_roll_player

    def currentPlayerMove(self):
        self.players[self.currentPlayer].pos += self.roll()
        self.players[self.currentPlayer].pos %= self.MAX_MOVE

class Player(object):
    """docstring for Player"""
    def __init__(self, name, bank):
        super(Player, self).__init__()
        self.name = name
        self.pos = 0
        self.balance = 0
        self.properties = []
        self.bank = bank
 
    def purchaseProperty(self, landProperty):
        self.properties.append(landProperty)
        self.toll(landProperty.price)

    def toll(self, amount):
        self.bank.tollFrom(self, amount)

    def withdraw(self, amount):
        self.bank.withdrawTo(self, amount)        

class Bank(object):
    """docstring for Bank"""
    def __init__(self):
        super(Bank, self).__init__()
    
    def withdrawTo(self, player, amount):
        player.balance += amount
        
    def tollFrom(self, player, amount):
        player.balance -= amount
        
class LandProperty:
    def __init__(self, name, section):
        self.name = name
        self.section = section



