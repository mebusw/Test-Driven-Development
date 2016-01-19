"""
Write a function that returns true or false depending on 
whether its input integer is a leap year or not.

A leap year is defined as one that is divisible by 4,
but is not otherwise divisible by 100 unless it is
also divisible by 400.

For example, 2001 is a typical common year and 1996
is a typical leap year, whereas 1900 is an atypical
common year and 2000 is an atypical leap year.
"""

import unittest


def is_leap_year(year):
    return not ((year % 100 == 0 and year % 400 != 0) or (year % 4 != 0))

class LeapYearTest(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_(self):
        self.assertFalse(is_leap_year(1999))
        self.assertTrue(is_leap_year(1996))
        self.assertFalse(is_leap_year(1900))
        self.assertTrue(is_leap_year(2000))

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
