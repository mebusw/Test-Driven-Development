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
        self._parseAndSortHand(hand)
        self._countCards()        
        self._sortCounters()        
        return self._matchCategories()

    
    def _parseAndSortHand(self, hand):
        self.cards = hand.split(' ')  
#        self.cards.sort(cmp=) 
            
    def _countCards(self):
        self.counter = {}
        for c in self.cards:
            if self.counter.has_key(c):
                self.counter[c] += 1
            else:
                self.counter[c] = 1


    def _sortCounters(self):
#        self.highest = reduce(lambda x, y: 
#                              x if self._compareCounter(x, y) > 0 else y, self.counter)
        print self.counter
        sorted(self.counter.items(), cmp=lambda x, y: _compareCounter([1], y[1]), reverse=True)
        print self.counter
        self.highest = self.counter.items()[0]

    def _compareCounter(self, first, second):
        '''return positive if first greater than second, negative if less, or 0 if equal
        ''' 
        if self.counter[first] > self.counter[second]:
            return 1
        elif self.counter[first] < self.counter[second]:
            return -1
        else:
            return self._compareCards(first, second)
                
    
    def _compareCards(self, first, second):
        '''return positive if first greater than second, negative if less, or 0 if equal
        ''' 
        v = self.value.index(first[0]) - self.value.index(second[0])
        if v <> 0:
            return v
        return self.suite.index(first[1]) - self.suite.index(second[1])
            
        
    def _matchCategories(self):
        if self._isStraight():
            return 'straight'
        
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

    def _isStraight(self):
        #reduce(lambda x, y: if  , self.counter)
        return False
    
    
