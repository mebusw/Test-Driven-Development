#!/usr/bin/python
# -*- coding: utf-8 -*-

'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''
from spot import *

MAX_MOVE = 40

class MonopolyGame:

    def __init__(self):
        self.RENT_RATE = 0.15

    def setupWithPlayerNames(self, *names):
        self.bank = Bank(self)
        self.board = [None] * MAX_MOVE
        self.players = []
        for name in names:
            self.players.append(Player(name, self.bank, self.board))
        self.communityChestPile = []
        self.chancePile = []
        self.currentPlayer = 0
        self.houseCount = 32
        self.hotelCount = 12
        self.winner = None

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

    def endGame(self):
        self.winner = max(self.players, key=lambda p: p.balance)

class Player(object):
    """docstring for Player"""
    def __init__(self, name, bank, board):
        super(Player, self).__init__()
        self.name = name
        self.pos = 0
        self.balance = 0
        self.properties = []
        self.bank = bank
        self.board = board
        self.isMakingSettlement = False
        self.isInJail = False
 
    # def __str__(self):
    #     return self.name + ' $' + self.balance + ' @' + self.pos

    def roll(self, roll):
        if self.isInJail:
            if self._isEvenNumber(roll):
                self.isInJail = False
            else:
                return
        self.moveBy(roll)

    def _isEvenNumber(self, number):
        return number % 2 ==0

    def moveBy(self, step):
        self.pos += step
        if self.pos > MAX_MOVE:
            self.pos -= MAX_MOVE
            self.balance += 200

    def purchaseProperty(self, realEstate):
        self.properties.append(realEstate)
        realEstate.owner = self
        self.toll(realEstate.price)

    def toll(self, amount):
        self.bank.tollFrom(self, amount)

    def withdraw(self, amount):
        self.bank.withdrawTo(self, amount)        

    def rent(self, realEstate):
        rentPrice = realEstate.rentPrice()
        self.balance -= rentPrice
        realEstate.owner.balance += rentPrice

    def motage(self, realEstate):
        self.withdraw(realEstate.purchasePrice() * 0.5)

    def payForFreedom(self):
        self.toll(50)
        self.isInJail = False
        
    def resolveSpotRule(self):
        landingSpot = self.board[self.pos]
        if isinstance(landingSpot, ArrestingSpot):
            self.pos = 10

class Bank(object):
    """docstring for Bank"""
    def __init__(self, game):
        super(Bank, self).__init__()
        self.game = game
    
    def withdrawTo(self, player, amount):
        player.balance += amount
        
    def tollFrom(self, player, amount):
        player.balance -= amount
        if player.balance > 0:
            return
        if player.isMakingSettlement:
            self.game.endGame()
        else:
            player.isMakingSettlement = True

        

