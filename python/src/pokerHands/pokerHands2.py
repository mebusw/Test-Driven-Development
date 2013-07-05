'''
Created on 2013-6-15

@author: mebusw@gmail.com
'''

RANKS = {'2':2, '3':3, '4':4, '5':5, '6':6, '7':7, '8':8, '9':9, 'T':10, 'J':11, 'Q':12, 'K':13, 'A':14}

class Hand(object):
    def __init__(self, cardString):
        self.cards = cardString.split(' ')
        self.rankedCards = map(lambda card: RANKS[card[0]], self.cards)
        self.rankedCards.sort(reverse=True)
        
        self.pairs = []
        self.fourKinds = []
        self.threeKinds = []
        self._findDuplicates()

        self._features = [
            self._isStraight() and self._isFlush(), 
            self.fourKinds, 
            self._isFullHouse(), 
            self._isFlush(), 
            self._isStraight(), 
            self.threeKinds, 
            len(self.pairs), 
            self.pairs, 
            self.rankedCards]

    def __cmp__(self, other):
        return cmp(self._features, other._features)

    def _isFullHouse(self):
        return len(self.threeKinds) == 1 and len(self.pairs) == 1

    def _isFlush(self):
        return all(self.cards[0][1] == c[1] for c in self.cards)

    def _isStraight(self):
        return all([z[0] == z[1] + 1 for z in zip(self.rankedCards[:-1], self.rankedCards[1:])])

    def _reduceD(self, cards):
        print [pair[0] for pair in zip(cards[:-1], cards[1:]) if pair[0] == pair[1]]
        return reduce(lambda cards, y: [pair[0] for pair in zip(cards[:-1], cards[1:]) if pair[0] == pair[1]], range(1), cards)#self.rankedCards)

    def _findDuplicates(self):
        self._duplicates = {}
        for c in self.cards:
            if self._duplicates.has_key(c[0]):
                self._duplicates[c[0]] += 1
            else:
                self._duplicates[c[0]] = 1

        for k, v in self._duplicates.iteritems():
            if v == 4:
                self.fourKinds.append(k)
            if v == 3:
                self.threeKinds.append(k)
            if v == 2:
                self.pairs.append(k)
        self.pairs.sort(reverse=True)
