"""
Your task is to write a function that takes two arguments,
a string and an integer width.

The function returns the string, but with line breaks
inserted at just the right places to make sure that no line
is longer than the column number.
You try to break lines at word boundaries.

Like a word processor, break the line by replacing
the last space in a line with a newline.
"""
import unittest


def wrap(s, width):
    if not s:
        return ''
    if len(s) <= width:
        return s

    try:
        space = s[:width + 1].rindex(' ')
        return _break_between(s, space, space + 1, width)
    except ValueError, e:
        return _break_between(s, width, width, width)


def _break_between(s, start, end, width):
    return s[:start] + '\n' + wrap(s[end:], width)


class WordWrapTest(unittest.TestCase):
    def setUp(self):
        pass

    def test_empty(self):
        self.assertEquals('', wrap(None, 10))
        self.assertEquals('', wrap('', 10))

    def test_one_short_word_doesnot_wrap(self):
        self.assertEquals('word', wrap('word', 10))

    def test_one_word_longer_than_limits_should_break(self):
        self.assertEquals('long\nword', wrap('longword', 4))
        self.assertEquals('longer\nword', wrap('longerword', 6))

    def test_one_word_longer_than_twice_limits_should_break(self):
        self.assertEquals('very\nlong\nword', wrap('verylongword', 4))

    def test_two_words_longer_than_limits_should_wrap(self):
        self.assertEquals('word\nword', wrap('word word', 6))
        self.assertEquals('wrap\nhere', wrap('wrap here', 6))

    def test_three_words_longer_than_limits_should_wrap(self):
        self.assertEquals('word\nword\nword', wrap('word word word', 6))

    def test_three_words_just_over_limits_should_break_at_second(self):
        self.assertEquals('word word\nword', wrap('word word word', 11))


if __name__ == "__main__":
    # import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
