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
    def start(self, solution):
        self.tries = 12
        self.used = 'AEIOU'
        self.solution = solution

    @property
    def puzzle(self):
        return ''.join(map(lambda x: x if x in self.used else '_', self.solution))

    def try_char(self, char):
        self.used += char
        self.tries = self.tries if char in self.solution else self.tries - 1

    def has_won(self):
        return self.puzzle == self.solution

    def is_game_over(self):
        return self.tries == 0


class ATest(unittest.TestCase):
    def test_should_start_game(self):
        hangman = Hangman()
        hangman.start('APPLE')
        self.assertEquals(12, hangman.tries)
        self.assertEquals('AEIOU', hangman.used)
        self.assertEquals('A___E', hangman.puzzle)

        hangman.start('CARE')
        self.assertEquals(12, hangman.tries)
        self.assertEquals('_A_E', hangman.puzzle)

    def test_try_with_correct_char(self):
        hangman = Hangman()
        hangman.start('APPLE')

        hangman.try_char('P')

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
