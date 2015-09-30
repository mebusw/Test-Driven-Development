"""
Note: The initial code and test files form a
simple example to start you off.
They are *not* related to the chosen exercise,
whose instructions now follow...
- - - - - - - - - - - - - - - - - - - - - - -

ISBN - International Standard Book Number
-----------------------------------------
There are two ISBN standards: ISBN-10 and ISBN-13.
Support for ISBN-13 is essential, whereas support
for ISBN-10 is optional.
Here are some valid examples of each:

ISBN-10:    0471958697
            0 471 60695 2
            0-470-84525-2
            0-321-14653-0

ISBN-13:    9780470059029
            978 0 471 48648 0
            978-0596809485
            978-0-13-149505-0
            978-0-262-13472-9

ISBN-10 is made up of 9 digits plus a check digit (which
may be 'X') and ISBN-13 is made up of 12 digits plus a
check digit. Spaces and hyphens may be included in a code,
but are not significant. This means that 9780471486480 is
equivalent to 978-0-471-48648-0 and 978 0 471 48648 0.

The check digit for ISBN-10 is calculated by multiplying
each digit by its position (i.e., 1 x 1st digit, 2 x 2nd
digit, etc.), summing these products together and taking
modulo 11 of the result (with 'X' being used if the result
is 10).

The check digit for ISBN-13 is calculated by multiplying
each digit alternately by 1 or 3 (i.e., 1 x 1st digit,
3 x 2nd digit, 1 x 3rd digit, 3 x 4th digit, etc.), summing
these products together, taking modulo 10 of the result
and subtracting this value from 10, and then taking the
modulo 10 of the result again to produce a single digit.


Basic task:
Create a function that takes a string and returns true
if that is a valid ISBN-13 and false otherwise.

Advanced task:
Also return true if the string is a valid ISBN-10.
"""

import unittest

def cs13(m):
    return (10 - m % 10) % 10

def cs10(m):
    c = m % 11
    return 'X' if c == 10 else c
    
def isValid(isbn, LEN, f, g):
    isbn = isbn.replace('-', '').replace(' ', '')
    m = 0
    for i in range(LEN - 1):
        m += int(isbn[i]) * f(i)
    return g(m) == int(isbn[LEN - 1])

def isValid13(isbn, LEN=13, f=lambda i: 1 if i % 2 == 0 else 3, g=cs13):
    return isValid(isbn, LEN, f, g)

def isValid10(isbn, LEN=10, f=lambda i: i + 1, g=cs10):
    return isValid(isbn, LEN, f, g)

class ISBNTest(unittest.TestCase):

    def test_valid_isbn13(self):
        self.assertTrue(isValid13('9780470059029'))
        self.assertTrue(isValid13('978-0-262-13472-9'))
        self.assertTrue(isValid13('978 0 471 48648 0'))

    def test_valid_isbn10(self):
        self.assertTrue(isValid10('0471958697'))
        self.assertTrue(isValid10('0 471 60695 2'))
        self.assertTrue(isValid10('0-470-84525-2'))


if __name__ == '__main__':
    unittest.main()