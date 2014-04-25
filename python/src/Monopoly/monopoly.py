'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''

class MonopolyGame:
    def __init__(self):
        self.MAX_MOVE = 40
        self.RENT_RATE = 0.15

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
        landProperty.owner = self
        self.toll(landProperty.price)

    def toll(self, amount):
        self.bank.tollFrom(self, amount)

    def withdraw(self, amount):
        self.bank.withdrawTo(self, amount)        

    def rent(self, landProperty):
        rentPrice = landProperty.updatedPrice() * 0.15
        self.balance -= rentPrice
        landProperty.owner.balance += rentPrice

class Bank(object):
    """docstring for Bank"""
    def __init__(self):
        super(Bank, self).__init__()
    
    def withdrawTo(self, player, amount):
        player.balance += amount
        
    def tollFrom(self, player, amount):
        player.balance -= amount
        
class LandProperty:
    def __init__(self, name, price, section):
        self.name = name
        self.price = price
        self.section = section
        section.add(self)
        self.owner = None

    def updatedPrice(self):
        return self.price * self.section.caculateRate()

class PropertySection:
    def __init__(self, name):
        self.name = name
        self.landProperties = []

    def add(self, landProperty):
        self.landProperties.append(landProperty)

    def caculateRate(self):
        RATE_FACTOR = 1.2
        soldProperties = filter(lambda p: p.owner is not None, self.landProperties)
        return RATE_FACTOR ** len(soldProperties)

