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

DIGIT_ONE = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', \
             'ten', 'eleven', 'twelve', 'thirteen', 'forteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
DIGIT_TWO = ['ten', 'XXX', 'twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety', 'hundred']

THOUSAND = 1000
MILLION = 1000000

def spell(num):
    if num < 20:
        return DIGIT_ONE[num]

    if num < 100:
        return DIGIT_TWO[num / 10] + ('' if num % 10 == 0 else (' ' + DIGIT_ONE[num % 10]))

    if num < THOUSAND:
        return DIGIT_ONE[num / 100] + ' hundred' + ('' if num % 100 == 0 else ' and ' + spell(num % 100))


    if num < MILLION:
        return spell(num / THOUSAND) + ' thousand'  + ('' if num % THOUSAND == 0 else ', ' + spell(num % THOUSAND))

    return spell(num / MILLION) + ' million'  + ('' if num % MILLION == 0 else ', ' + spell(num % MILLION))


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
        self.assertEqual('forty five thousand, six hundred and nine', spell(45609))
        self.assertEqual('five hundred and twelve thousand, six hundred and seven', spell(512607))

    def test_million(self):
        self.assertEqual('forty three million, one hundred and twelve thousand, six hundred and three', spell(43112603))

        

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
