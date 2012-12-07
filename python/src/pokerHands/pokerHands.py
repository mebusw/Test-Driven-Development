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
        self.categories = {'pair':0, 'three':0, 'four':0, 'straight':0, 'flush':0}
    
    def category(self, hand):
        self._parseAndSortHand(hand)
        self._countCards()        
        self._sortCounters()        
        return self._matchCategories()

    
    def _parseAndSortHand(self, hand):
        self.cards = hand.split(' ')  
            
    def _countCards(self):
        self.counter = {}
        for c in self.cards:
            if self.counter.has_key(c):
                self.counter[c] += 1
            else:
                self.counter[c] = 1


    def _sortCounters(self):
        self.sortedCounter = sorted(self.counter.items(), cmp=(lambda x, y: self._compareCounterItems(x, y)), reverse=True)

    def _compareCounterItems(self, first, second):
        '''
            @attention: input is not an element of a dict, but one of the dict.items
            @param first: ('5H', 2) not {'5H':2}
            @return positive if first greater than second, negative if less, or 0 if equal
        ''' 
        if first[1] > second[1]:
            return 1
        elif first[1] < second[1]:
            return -1
        else:
            return self._compareCards(first[0], second[0])
                
    
    def _compareCards(self, first, second):
        '''
            @param first: '5H'
            @return positive if first greater than second, negative if less, or 0 if equal
        ''' 
        v = self.VALUES.index(first[0]) - self.VALUES.index(second[0])
        if v <> 0:
            return v
        return self.SUITES.index(first[1]) - self.SUITES.index(second[1])
            
        
    def _matchCategories(self):
        self._findDuplicates()
        self._findFlush()
        self._findStraight()
        
        if self.categories['four'] == 1:
            return 'four of a kind'
        
        if self.categories['three'] == 1 and self.categories['pair'] == 1:
            return 'full house'
        
        if self.categories['flush'] == 1:
            return 'flush'

        if self.categories['straight'] == 1:
            return 'straight'
        
        if self.categories['three'] == 1:
            return 'three of a kind'

        if self.categories['pair'] == 2:
            return 'two pairs'

        if self.categories['pair'] == 1:
            return 'pair'

        return 'high card'

    def _findDuplicates(self):
        for v in self.counter.itervalues():
            if v == 4:
                self.categories['four'] += 1
            if v == 3:
                self.categories['three'] += 1
            if v == 2:
                self.categories['pair'] += 1

    def _findStraight(self):
        if self._isAllCardsIdentical() and self._isConsecutiveValues():
            self.categories['straight'] = 1


    def _isAllCardsIdentical(self):
        return MAX_LEN == len(self.sortedCounter)

    def _isConsecutiveValues(self):
        highest = self.sortedCounter[0]
        lowest = self.sortedCounter[4]
        if (MAX_LEN - 1) == self.VALUES.index(highest[0][0]) - self.VALUES.index(lowest[0][0]):
            return True        
    
    def _findFlush(self):
        if self._isAllCardsIdentical() and self._isSameSuite():
            self.categories['flush'] = 1

    def _isSameSuite(self):
        suites = set()
        for c in self.sortedCounter:
            suites.add(c[0][1])
        return 1 == len(suites)