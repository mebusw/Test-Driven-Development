"""
- start game
- input char
    - correct char
        - win
    - wrong char
        - game over
- display result


Problem: A___E
Solution: APPLE

Tries: 12
Length:
Used:AEIOU
"""

import unittest


class Hangman(object):
    tries = 12
    used = 'AEIOU'
    problem = ''
    solution = ''

    def __init__(self):
        self.length = 0

    def start(self, solution):
        self.solution = solution
        self.problem = '_' * self.length
        vowel = 'AEIOU'
        for c in vowel:
            self._replace_char(c)

    @property
    def length(self):
        return len(self.solution)

    @length.setter
    def length(self, v):
        # print v
        pass

    @property
    def puzzle(self):
        return self.problem

    def try_char(self, char):
        touched = self._replace_char(char)

        self.used += char

        if not touched:
            self.tries -= 1
        return touched

    def _replace_char(self, char):
        touched = False
        prblm = list(self.problem)
        for i in xrange(len(self.solution)):
            if char == self.solution[i]:
                prblm[i] = char
                touched = True
        self.problem = ''.join(prblm)
        return touched

    def has_won(self):
        return self.problem == self.solution

    def is_game_over(self):
        return self.tries == 0


class ATest(unittest.TestCase):
    def test_should_start_game(self):
        hangman = Hangman()
        hangman.start('APPLE')
        self.assertEquals(12, hangman.tries)
        self.assertEquals(5, hangman.length)
        self.assertEquals('AEIOU', hangman.used)
        self.assertEquals('A___E', hangman.puzzle)

        hangman.start('CARE')
        self.assertEquals(12, hangman.tries)
        self.assertEquals(4, hangman.length)
        self.assertEquals('_A_E', hangman.puzzle)

    def test_try_with_correct_char(self):
        hangman = Hangman()
        hangman.start('APPLE')

        is_matched = hangman.try_char('P')

        self.assertTrue(is_matched)
        self.assertEquals(12, hangman.tries)
        self.assertEquals('AEIOUP', hangman.used)
        self.assertEquals('APP_E', hangman.puzzle)

    def test_try_with_wrong_char(self):
        hangman = Hangman()
        hangman.start('APPLE')

        is_matched = hangman.try_char('X')

        self.assertFalse(is_matched)
        self.assertEquals(11, hangman.tries)
        self.assertEquals('AEIOUX', hangman.used)
        self.assertEquals('A___E', hangman.puzzle)

    def test_win_game(self):
        hangman = Hangman()
        hangman.start('APPLE')

        hangman.try_char('P')
        hangman.try_char('L')

        self.assertTrue(hangman.has_won())

    def test_game_over(self):
        hangman = Hangman()
        hangman.start('APPLE')

        for x in xrange(12):
            hangman.try_char('X')

        self.assertTrue(hangman.is_game_over())


if __name__ == '__main__':
    unittest.main()
