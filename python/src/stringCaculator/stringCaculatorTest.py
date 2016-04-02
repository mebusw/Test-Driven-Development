import unittest
import re


class StringCaculator(object):

    def __init__(self, delimiters, src):
        self.delimiters = delimiters
        self.src = src

    def get_numbers(self):
        if self.src == '':
            return [0]

        return map(lambda x: int(x), re.split(self.delimiters, self.src))

    def total(self):
        if any(map(lambda x: x < 0, self.get_numbers())):
            raise TypeError('')

        return sum(map(lambda x: x % 1000, self.get_numbers()))

    @staticmethod
    def add(s):
        return StringCaculator.parse(s).total()

    @staticmethod
    def parse(s):
        if s.startswith('//'):
            delimiters, s = s[2:].split('\n')
            calc = StringCaculator(delimiters, s)
        else:
            calc = StringCaculator(',|\n', s)
        return calc


class StringCaculatorTest(unittest.TestCase):

    def test_up_to_2_numbers(self):
        self.assertEquals(0, StringCaculator.add(''))
        self.assertEquals(1, StringCaculator.add('1'))
        self.assertEquals(3, StringCaculator.add('1,2'))

    def test_unknown_amount_of_numbers(self):
        self.assertEquals(6, StringCaculator.add('1,2,3'))

    def test_newlines_between_numbers_instead_of_commas(self):
        self.assertEquals(6, StringCaculator.add('1\n2,3'))

    def test_different_delimiters(self):
        self.assertEquals(3, StringCaculator.add('//;\n1;2'))

    def test_exception_for_negtives(self):
        with self.assertRaisesRegexp(TypeError, ''):
            self.assertEquals(-1, StringCaculator.add('//;\n-1'))
        with self.assertRaises(TypeError):
            self.assertEquals(-3, StringCaculator.add('//;\n-1;-2'))

    def test_ignore_number_bigger_than_1000(self):
        self.assertEquals(3, StringCaculator.add('2,1001'))

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
