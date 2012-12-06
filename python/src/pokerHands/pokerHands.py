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
        self.suite = list('DCHS')
        self.categories = {}
    
    def category(self, hand):
        self._parseHand(hand)

        self._countCards()
        
        self._findHighestCard()        
        
        if self._countPair() == 2:
            return 'two pair'

        if self._countPair() == 1:
            return 'pair'

        return 'high card'
    
    def _parseHand(self, hand):
        self.cards = hand.split(' ')   
            
    def _findHighestCard(self):
        self.highest = reduce(lambda x, y: 
                              x if self._isFirstCardHigherThanSecond(x, y) else y, self.counter)

    def _isFirstCardHigherThanSecond(self, first, second):
        if self.value.index(first[0]) > self.value.index(second[0]) \
        or self.value.index(first[0]) == self.value.index(second[0]) \
        and self.suite.index(first[1]) > self.suite.index(second[1]):
            return True
        return False
            
        
    def _countCards(self):
        self.counter = {}
        for c in self.cards:
            if self.counter.has_key(c):
                self.counter[c] += 1
            else:
                self.counter[c] = 1

    def _countPair(self):
        self.categories['pair'] = 0
        for v in self.counter.itervalues():
            if v > 1:
                self.categories['pair'] += 1
        return self.categories['pair']
