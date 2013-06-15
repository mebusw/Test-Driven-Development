'''
Created on 2013-6-15

@author: mebusw@gmail.com
'''

RANKS = {'2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9, 'T':10, 'J':11, 'Q':12, 'K':13, 'A':14}

SUITE_RANKS = {'D':1, 'C':2, 'H':3, 'S':4} 

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
		self.cards = cardString.split(' ')
		self.rankedCards = map(lambda card: RANKS[card[0]], self.cards)
		self.rankedCards.sort(reverse=True)
		
		self._features = [self._isFlush(), self.rankedCards]

	def __cmp__(self, other):
		return cmp(self._features, other._features)

	def _isFlush(self):
		return all(self.cards[0][1] == c[1] for c in self.cards)
