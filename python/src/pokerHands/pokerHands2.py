'''
Created on 2013-6-15

@author: mebusw@gmail.com
'''

RANKS = {'2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9, 'T':10, 'J':11, 'Q':12, 'K':13, 'A':14}

SUITES = {'D':1, 'C':2, 'H':3, 'S':4} 

class Game(object):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''


class Hand(object):
	def __init__(self, cardString):
		cards = cardString.split(' ')
		self.rankedCards = map(lambda card: RANKS[card[0]], cards)
		self.rankedCards.sort(reverse=True)

	def __cmp__(self, other):
		return cmp(self.rankedCards, other.rankedCards)
