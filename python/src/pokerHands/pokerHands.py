'''
Created on 2012-12-4

@author: mebusw
'''

class Game(object):
    '''
    classdocs
    '''

    def __init__(self):
        '''
        Constructor
        '''
        self.value = list('23456789TJQKA')
    
    def category(self, hand):
        self._parseHand(hand)
        self._findHighestCard()
        self._countCards()

        for v in self.counter.itervalues():
            if v > 1:
                return 'pair'
        return 'high card'
    
    def _parseHand(self, hand):
        self.cards = hand.split(' ')   
            
    def _findHighestCard(self):
        self.highest = reduce(lambda x, y: 
                              x if self.value.index(x[0]) >= self.value.index(y[0]) else y, self.cards)
        
    def _countCards(self):
        self.counter = {}
        for c in self.cards:
            if self.counter.has_key(c):
                self.counter[c] += 1
            else:
                self.counter[c] = 1
        
