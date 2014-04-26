#!/usr/bin/python
# -*- coding: utf-8 -*-

'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''
MAX_MOVE = 40

class MonopolyGame:

    def __init__(self):
        self.RENT_RATE = 0.15

    def setupWithPlayerNames(self, *names):
        self.bank = Bank()
        self.players = []
        for name in names:
            self.players.append(Player(name, self.bank))
        self.board = [None] * MAX_MOVE
        self.communityChestPile = []
        self.chancePile = []
        self.currentPlayer = 0

    def roll(self):
        return -1

    def playerCount(self):
        return len(self.players)

    def decideFirstPlayer(self):
        max_roll_player = 0
        max_roll = 0
        for i in xrange(self.playerCount()):
            r = self.roll() 
            if r > max_roll:
                max_roll = r
                max_roll_player = i
        self.currentPlayer = max_roll_player

    def getCurrentPlayer(self):
        return self.players[self.currentPlayer]

    def turnToNextPlayer(self):
        self.currentPlayer = (self.currentPlayer + 1 + self.playerCount()) % self.playerCount()


class Player(object):
    """docstring for Player"""
    def __init__(self, name, bank):
        super(Player, self).__init__()
        self.name = name
        self.pos = 0
        self.balance = 0
        self.properties = []
        self.bank = bank
 
    def __str__(self):
        return self.name + ' $' + self.balance + ' @' + self.pos

    def moveBy(self, step):
        self.pos += step
        self.pos %= MAX_MOVE

    def purchaseProperty(self, landProperty):
        self.properties.append(landProperty)
        landProperty.owner = self
        self.toll(landProperty.price)

    def toll(self, amount):
        self.bank.tollFrom(self, amount)

    def withdraw(self, amount):
        self.bank.withdrawTo(self, amount)        

    def rent(self, landProperty):
        rentPrice = landProperty.rentPrice()
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
        

class Spot(object):
    """Spot represent a step on the map"""
    def __init__(self, name):
        super(Spot, self).__init__()
        self.name = name
        
class LandProperty(Spot):
    def __init__(self, name, price, section):
        Spot.__init__(self, name)
        self.price = price
        self.section = section
        section.add(self)
        self.owner = None
        self.buildingLevel = 0

    def purchasePrice(self):
        return self.price * self.section.caculateRate()

    def rentPrice(self):
        return self.purchasePrice() * 0.15 * (self.buildingLevel + 1)

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

