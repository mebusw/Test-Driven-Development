"""
The game of yatzy is a simple dice game. Each player
rolls five six-sided dice. The player places the roll in
a category, such as ones, twos, fives, pair, two pairs
etc (see below).
If the roll is compatible with the category, the player
gets a score for the roll according to the rules. If the
roll is not compatible with the category, the player scores
zero for the roll.

For example, if a player rolls 5,6,5,5,2 and scores the
dice in the fives category they would score 15 (three fives).

Your task is to score a GIVEN roll in a GIVEN category.
You do NOT have to program the random dice rolling.
You do NOT have to program re-rolls (as in the real game).
You do NOT play by letting the computer choose the highest
scoring category for a given roll.


Yatzy Categories and Scoring Rules
==================================

Chance:
  The player scores the sum of all dice,
  no matter what they read.
  For example,
   1,1,3,3,6 placed on "chance" scores 14 (1+1+3+3+6)
   4,5,5,6,1 placed on "chance" scores 21 (4+5+5+6+1)

Yatzy:
  If all dice have the same number,
  the player scores 50 points.
  For example,
   1,1,1,1,1 placed on "yatzy" scores 50
   5,5,5,5,5 placed on "yatzy" scores 50
   1,1,1,2,1 placed on "yatzy" scores 0

Ones, Twos, Threes, Fours, Fives, Sixes:
  The player scores the sum of the dice that reads one,
  two, three, four, five or six, respectively.
  For example,
   1,1,2,4,4 placed on "fours" scores 8 (4+4)
   2,3,2,5,1 placed on "twos" scores 4  (2+2)
   3,3,3,4,5 placed on "ones" scores 0

Pair:
  If exactly two dice have the same value then
  the player scores the sum of the two highest matching dice.
  For example, when placed on "pair"
   3,3,3,4,4 scores 8 (4+4)
   1,1,6,2,6 scores 12 (6+6)
   3,3,3,4,1 scores 0
   3,3,3,3,1 scores 0

Two pairs:
  If exactly two dice have the same value and exactly
  two dice have a different value then the
  player scores the sum of these four dice.
  For example, when placed on "two pairs"
   1,1,2,3,3 scores 8 (1+1+3+3)
   1,1,2,3,4 scores 0
   1,1,2,2,2 scores 0

Three of a kind:
  If there are exactly three dice with the same number
  then the player scores the sum of these dice.
  For example, when placed on "three of a kind"
    3,3,3,4,5 scores 9 (3+3+3)
    3,3,4,5,6 scores 0
    3,3,3,3,1 scores 0

Four of a kind:
  If there are exactly four dice with the same number
  then the player scores the sum of these dice.
  For example, when placed on "four of a kind"
    2,2,2,2,5 scores 8 (2+2+2+2)
    2,2,2,5,5 scores 0
    2,2,2,2,2 scores 0

Small straight:
  When placed on "small straight", if the dice read
  1,2,3,4,5, the player scores 15 (the sum of all the dice).

Large straight:
  When placed on "large straight", if the dice read
  2,3,4,5,6, the player scores 20 (the sum of all the dice).

Full house:
  If the dice are two of a kind and three of a different kind
  then the player scores the sum of all five dice.
  For example, when placed on "full house"
    1,1,2,2,2 scores 8 (1+1+2+2+2)
    2,2,3,3,4 scores 0
    4,4,4,4,4 scores 0
"""
import unittest
from itertools import groupby


class Game(object):
    @staticmethod
    def chance(*dice):
        return sum(dice)

    @staticmethod
    def yatzy(*dice):
        return 50 if all(d == dice[0] for d in dice) else 0

    @staticmethod
    def _single(n):
        return lambda *dice: n * dice.count(n)

    def __init__(self):
        self.sixes = Game._single(6)
        self.fives = Game._single(5)
        self.fours = Game._single(4)
        self.threes = Game._single(3)
        self.twos = Game._single(2)
        self.ones = Game._single(1)

    def pair(self, *dice):
        for b in self._buckets(dice):
            if b[1] == 2:
                return b[0] * 2
        return 0

    def _buckets(self, dice):
        g = groupby(sorted(dice, reverse=True), lambda x: x)
        buckets = [(m, len(list(n))) for m, n in g]
        buckets = sorted(buckets, key=lambda x: x[1], reverse=True)
        return buckets

    def three_kind(self, *dice):
        b = self._buckets(dice)
        if b[0][1] == 3:
            return b[0][0] * 3
        return 0

    def four_kind(self, *dice):
        b = self._buckets(dice)
        if b[0][1] == 4:
            return b[0][0] * 4
        return 0

    def two_pairs(self, *dice):
        b = self._buckets(dice)
        if b[0][1] == 2 and b[1][1] == 2:
            return b[0][0] * 2 + b[1][0] * 2
        return 0

    def straight(self, *dice):
        if all(z[0] == z[1] + 1 for z in zip(dice[1:], dice[:-1])):
            return sum(dice)
        return 0

    def full_house(self, *dice):
        b = self._buckets(dice)
        if b[0][1] == 3 and b[1][1] == 2:
            return sum(dice)
        return 0


class YatzyTest(unittest.TestCase):
    def setUp(self):
        pass

    def test_chance(self):
        self.assertEquals(1 + 1 + 3 + 3 + 6, Game.chance(1, 1, 3, 3, 6))
        self.assertEquals(4 + 5 + 5 + 6 + 1, Game.chance(4, 5, 5, 6, 1))

    def test_yatzy(self):
        self.assertEquals(50, Game.yatzy(1, 1, 1, 1, 1))
        self.assertEquals(50, Game.yatzy(5, 5, 5, 5, 5))
        self.assertEquals(0, Game.yatzy(1, 1, 1, 2, 1))

    def test_ones(self):
        self.assertEquals(4 + 4, Game().fours(1, 1, 2, 4, 4))
        self.assertEquals(2 + 2, Game().twos(2, 3, 2, 5, 1))
        self.assertEquals(0, Game().ones(3, 3, 3, 4, 5))

    def test_pair(self):
        self.assertEquals(4 + 4, Game().pair(3, 3, 3, 4, 4))
        self.assertEquals(6 + 6, Game().pair(1, 1, 6, 2, 6))
        self.assertEquals(0, Game().pair(3, 3, 3, 4, 1))
        self.assertEquals(0, Game().pair(3, 3, 3, 3, 1))

    def test_two_pairs(self):
        self.assertEquals(1 + 1 + 3 + 3, Game().two_pairs(1, 1, 2, 3, 3))
        self.assertEquals(0, Game().two_pairs(1, 1, 2, 3, 4))
        self.assertEquals(0, Game().two_pairs(1, 1, 2, 2, 2))

    def test_three_of_a_kind(self):
        self.assertEquals(3 + 3 + 3, Game().three_kind(3, 3, 3, 4, 5))
        self.assertEquals(0, Game().three_kind(3, 3, 4, 5, 6))
        self.assertEquals(0, Game().three_kind(3, 3, 3, 3, 1))

    def test_four_of_a_kind(self):
        self.assertEquals(2 + 2 + 2 + 2, Game().four_kind(2, 2, 2, 2, 5))
        self.assertEquals(0, Game().four_kind(2, 2, 2, 5, 5))
        self.assertEquals(0, Game().four_kind(2, 2, 2, 2, 2))

    def test_straight(self):
        self.assertEquals(1 + 2 + 3 + 4 + 5, Game().straight(1, 2, 3, 4, 5))
        self.assertEquals(2 + 3 + 4 + 5 + 6, Game().straight(2, 3, 4, 5, 6))

    def test_full_house(self):
        self.assertEquals(1 + 1 + 2 + 2 + 2, Game().full_house(1, 1, 2, 2, 2))


if __name__ == "__main__":
    # import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
