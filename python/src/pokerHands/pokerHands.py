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
        return self._matchCategories()

    
    def _parseHand(self, hand):
        self.cards = hand.split(' ')   
            
    def _countCards(self):
        self.counter = {}
        for c in self.cards:
            if self.counter.has_key(c):
                self.counter[c] += 1
            else:
                self.counter[c] = 1


    def _findHighestCard(self):
        self.highest = reduce(lambda x, y: 
                              x if self._isFirstCounterHigherThanSecond(x, y) else y, self.counter)

    def _isFirstCounterHigherThanSecond(self, first, second):
        if self.counter[first] > self.counter[second] \
        or self.counter[first] == self.counter[second] \
        and self._isFirstCardHigherThanSecond(first, second):
            return True
        return False
                
    
    def _isFirstCardHigherThanSecond(self, first, second):
        if self.value.index(first[0]) > self.value.index(second[0]) \
        or self.value.index(first[0]) == self.value.index(second[0]) \
        and self.suite.index(first[1]) > self.suite.index(second[1]):
            return True
        return False
            
        
    def _matchCategories(self):
        if self._findThree() == 1:
            return 'three of a kind'

        if self._findPairs() == 2:
            return 'two pairs'

        if self._findPairs() == 1:
            return 'pair'

        return 'high card'

    def _findPairs(self):
        self.categories['pair'] = 0
        for v in self.counter.itervalues():
            if v == 2:
                self.categories['pair'] += 1
        return self.categories['pair']
    
    def _findThree(self):
        self.categories['three'] = 0
        for v in self.counter.itervalues():
            if v == 3:
                self.categories['three'] += 1
        return self.categories['three']
