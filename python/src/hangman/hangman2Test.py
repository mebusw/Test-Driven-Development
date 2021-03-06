"""
 * hangman-easy java8   http://www.twoplayergames.org/play/668-Hangman.html
 * length
 * used
 * tries
 * discovered answer
 * gamewin
 * gameover
 * mock callback => type().checkWin()
"""

import unittest
# from mock import *

VOWELS = 'AEIOU'


class Hangman(object):

    def __init__(self, word):
        self.word = word
        self.length = len(word)
        self._used = VOWELS
        self.tries = 12
        self.is_win = False
        self.is_winning = lambda: self.word == self.discovered()
        self.is_losing = lambda: self.tries <= 0


    def used(self):
        return self._used

    def tap(self, char):
        self._consume_a_try(char)
        self._mark_as_used(char)

    def _consume_a_try(self, char):
        if self._is_uncontained(char) or self._is_already_tapped(char):
            self.tries -= 1

    def _is_uncontained(self, char):
        return char not in self.word

    def _is_already_tapped(self, char):
        return char in self._used

    def _mark_as_used(self, char):
        if char not in self._used:
            self._used += char

    def discovered(self):
        return ''.join(map(lambda c: c if c in self._used else '_', self.word))

        
class Hangman_LengthTest(unittest.TestCase):

    def setUp(self):
        self.hangman = Hangman("XYZ")

    def test_length_of_word_when_start(self):
        self.assertEquals("XYZ", self.hangman.word)
        self.assertEquals(3, self.hangman.length)


class Hangman_UsedTest(unittest.TestCase):

    def setUp(self):
        self.hangman = Hangman("XYZ")

    def test_used_should_be_only_all_vowels_when_start(self):
        self.assertEquals(VOWELS, self.hangman.used())

    def test_used_should_be_all_vowels_when_typing_a_vowel(self):
        self.hangman.tap('A')

        self.assertEquals(VOWELS, self.hangman.used())

    def test_used_should_be_increase_when_typing_a_consonant(self):
        self.hangman.tap('T')

        self.assertEquals(VOWELS + 'T', self.hangman.used())

    def test_used_should_not_change_when_typing_same_consonant(self):
        self.hangman.tap('T')

        self.hangman.tap('T')

        self.assertEquals(VOWELS + 'T', self.hangman.used())


class Hangman_TriesTest(unittest.TestCase):

    def setUp(self):
        self.hangman = Hangman("XYZ")

    def test_tries_when_start(self):
        self.assertEquals(12, self.hangman.tries)

    def test_tries_decreases_when_tap_a_uncontained_char(self):
        self.hangman.tap('N')

        self.assertEquals(11, self.hangman.tries)

    def test_tries_remains_when_tap_a_contained_consonant(self):
        self.hangman.tap('Y')

        self.assertEquals(12, self.hangman.tries)

    def test_tries_decreases_when_tap_a_contained_consonant_again(self):
        self.hangman.tap('Y')

        self.hangman.tap('Y')

        self.assertEquals(11, self.hangman.tries)


class Hangman_DiscoveredTest(unittest.TestCase):

    def setUp(self):
        pass

    def test_discovered_of_one_consonant_when_start(self):
        self.hangman = Hangman('X')

        self.assertEquals('_', self.hangman.discovered())

    def test_discovered_of_one_vowel_when_start(self):
        self.hangman = Hangman('A')

        self.assertEquals('A', self.hangman.discovered())

    def test_discovered_of_consonant_plus_vowel_when_start(self):
        self.hangman = Hangman('AB')

        self.assertEquals('A_', self.hangman.discovered())

    def test_discovered_a_contained_consonant_when_type(self):
        self.hangman = Hangman('ABC')

        self.hangman.tap('B')

        self.assertEquals('AB_', self.hangman.discovered())

    def test_discovered_a_double_contained_consonant_when_type(self):
        self.hangman = Hangman('ABBC')

        self.hangman.tap('B')

        self.assertEquals('ABB_', self.hangman.discovered())


class Hangman_GameOverTest(unittest.TestCase):

    def setUp(self):
        self.hangman = Hangman("XYZ")

    def test_game_is_not_over_when_start(self):
        self.assertFalse(self.hangman.is_winning())
        self.assertFalse(self.hangman.is_losing())

    def test_game_is_losing_when_try_out(self):
        self._try_out_with_uncontained_char()

        self.assertTrue(self.hangman.is_losing())

    def _try_out_with_uncontained_char(self):
        for i in range(12):
            self.hangman.tap('N')

    def test_game_is_winning_when_all_char_discovered(self):
        self.hangman.tap('X')
        self.hangman.tap('Y')

        self.hangman.tap('Z')

        self.assertTrue(self.hangman.is_winning())

if __name__ == '__main__':
    unittest.main()
