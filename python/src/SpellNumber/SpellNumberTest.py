"""
Spell out a number. For example

      99 --> ninety nine
     300 --> three hundred
     310 --> three hundred and ten
    1501 --> one thousand, five hundred and one
   12609 --> twelve thousand, six hundred and nine
  512607 --> five hundred and twelve thousand,
             six hundred and seven
43112603 --> forty three million, one hundred and
             twelve thousand,
             six hundred and three
"""

import unittest

DIGIT_ONE = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine',
             'ten', 'eleven', 'twelve', 'thirteen', 'forteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
DIGIT_TWO = ['ten', 'XXX', 'twenty', 'thirty', 'forty',
             'fifty', 'sixty', 'seventy', 'eighty', 'ninety', 'hundred']

TEN = 10
HUNDRED = 100
THOUSAND = 1000
MILLION = 1000000


def postfix(unit, seperator):
    return lambda num: '' if num % unit == 0 else seperator + spell(num % unit)


def spell(num):
    if num < 20:
        return DIGIT_ONE[num]

    if num < HUNDRED:
        return DIGIT_TWO[num / TEN] + postfix(TEN, ' ')(num)

    if num < THOUSAND:
        return DIGIT_ONE[num / HUNDRED] + ' hundred' + postfix(HUNDRED, ' and ')(num)

    if num < MILLION:
        return spell(num / THOUSAND) + ' thousand' + postfix(THOUSAND, ', ')(num)

    return spell(num / MILLION) + ' million' + postfix(MILLION, ', ')(num)


class SpellNumberTest(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_one_digit(self):
        self.assertEqual('nine', spell(9))
        self.assertEqual('one', spell(1))
        self.assertEqual('ten', spell(10))
        self.assertEqual('zero', spell(0))

    def test_two_digits(self):
        self.assertEqual('ninety nine', spell(99))
        self.assertEqual('twenty three', spell(23))
        self.assertEqual('forty', spell(40))
        self.assertEqual('twelve', spell(12))

    def test_hundred(self):
        self.assertEqual('three hundred', spell(300))
        self.assertEqual('three hundred and ten', spell(310))
        self.assertEqual('three hundred and twenty three', spell(323))
        self.assertEqual('three hundred and three', spell(303))

    def test_thousand(self):
        self.assertEqual('one thousand', spell(1000))
        self.assertEqual('one thousand, five hundred and one', spell(1501))
        self.assertEqual(
            'forty five thousand, six hundred and nine', spell(45609))
        self.assertEqual(
            'five hundred and twelve thousand, six hundred and seven', spell(512607))

    def test_million(self):
        self.assertEqual(
            'forty three million, one hundred and twelve thousand, six hundred and three', spell(43112603))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
