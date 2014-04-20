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
		self.currentPlayer = 1

	def roll(self):
		return -1
