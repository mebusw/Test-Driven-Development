'''
Created on 2012-12-4

@author: mebusw
'''
MAX_LEN = 5
RANK = ['straight flush', 'four of a kind', 'full house', 'flush', \
        'straight', 'three of a kind', 'two pairs', 'pair', 'high card']
VALUES = list('23456789TJQKA')
SUITES = list('DCHS')

class Game(object):
    '''
    classdocs
    '''
    def __init__(self):
        '''
        Constructor
        '''
    
    def category(self, hand):
        self.categories = {'pair':0, 'three':0, 'four':0, 'straight':0, 'flush':0}
        self._parseAndSortHand(hand)
        self._countCards()        
        self._sortCounters()        
        return self._matchCategories()

    
    def _parseAndSortHand(self, hand):
        self.cards = hand.split(' ')  
            
    def _countCards(self):
        self.counter = {}
        self.valueCounter = {}
        for c in self.cards:
            if self.counter.has_key(c):
                self.counter[c] += 1
            else:
                self.counter[c] = 1
            if self.valueCounter.has_key(c[0]):
                self.valueCounter[c[0]] += 1
            else:
                self.valueCounter[c[0]] = 1


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
            return self.compareCards(first[0], second[0])
                
    
    def compareCards(self, first, second):
        '''
            @param first: '5H'
            @return positive if first greater than second, negative if less, or 0 if equal
        ''' 
        v = VALUES.index(first[0]) - VALUES.index(second[0])
        if v <> 0:
            return v
        return SUITES.index(first[1]) - SUITES.index(second[1])
            
        
    def _matchCategories(self):
        self._findDuplicates()
        self._findFlush()
        self._findStraight()
        
        if self.categories['flush'] == 1 and self.categories['straight'] == 1:
            self.rank = 0
        elif self.categories['four'] == 1:
            self.rank = 1
        elif self.categories['three'] == 1 and self.categories['pair'] == 1:
            self.rank = 2
        elif self.categories['flush'] == 1:
            self.rank = 3
        elif self.categories['straight'] == 1:
            self.rank = 4
        elif self.categories['three'] == 1:
            self.rank = 5
        elif self.categories['pair'] == 2:
            self.rank = 6
        elif self.categories['pair'] == 1:
            self.rank = 7
        else:
            self.rank = 8

        return RANK[self.rank]

    def _findDuplicates(self):
        for v in self.valueCounter.itervalues():
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
        if (MAX_LEN - 1) == VALUES.index(highest[0][0]) - VALUES.index(lowest[0][0]):
            return True        
    
    def _findFlush(self):
        if self._isAllCardsIdentical() and self._isSameSuite():
            self.categories['flush'] = 1

    def _isSameSuite(self):
        suites = set()
        for c in self.sortedCounter:
            suites.add(c[0][1])
        return 1 == len(suites)