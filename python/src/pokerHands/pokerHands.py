'''
Created on 2012-12-4

@author: mebusw
'''
MAX_LEN = 5

class Game(object):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''
        self.VALUES = list('23456789TJQKA')
        self.SUITES = list('DCHS')
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
        self.sortedCounter = sorted(self.counter.items(), cmp=(lambda x, y: self._compareCounterItems(x, y)), reverse=True)
        self.highest = self.sortedCounter[0]

    def _compareCounterItems(self, first, second):
        '''return positive if first greater than second, negative if less, or 0 if equal
            @attention: input is not an element of a dict, but one of the dict items
            @param first: ('5H', 2) not {'5H':2}
        ''' 
        if first[1] > second[1]:
            return 1
        elif first[1] < second[1]:
            return -1
        else:
            return self._compareCards(first[0], second[0])
                
    
    def _compareCards(self, first, second):
        '''return positive if first greater than second, negative if less, or 0 if equal
            @param first: '5H'
        ''' 
        v = self.VALUES.index(first[0]) - self.VALUES.index(second[0])
        if v <> 0:
            return v
        return self.SUITES.index(first[1]) - self.SUITES.index(second[1])
            
        
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
        return self._isAllCardsIdentical() and self._isConsecutiveValues()


    def _isAllCardsIdentical(self):
        return MAX_LEN == len(self.sortedCounter)

    def _isConsecutiveValues(self):
        highest = self.sortedCounter[0]
        lowest = self.sortedCounter[4]
        if (MAX_LEN - 1) == self.VALUES.index(highest[0][0]) - self.VALUES.index(lowest[0][0]):
            return True        
    
