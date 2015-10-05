"""There are four types of common coins in US currency:
  quarters (25 cents)
  dimes (10 cents)
  nickels (5 cents) 
  pennies (1 cent)
  
There are 6 ways to make change for 15 cents:
  A dime and a nickel;
  A dime and 5 pennies;
  3 nickels;
  2 nickels and 5 pennies;
  A nickel and 10 pennies;
  15 pennies.
  
How many ways are there to make change for a dollar
using these common coins? (1 dollar = 100 cents).

[Source http://rosettacode.org]"""


import unittest

QT, TEN, FIVE, ONE = 25, 10, 5, 1

def calc(total):
    result = []

    for q in range(0, total / QT + 1):
      for t in range(0, (total - QT * q) / TEN + 1):
        for f in xrange((total - QT * q - TEN * t) / FIVE + 1):
          result.append([t, f, total - QT * q - TEN * t - FIVE * f])

    return result

class CountCoinTest(unittest.TestCase):

    def test_calc_penny(self):
        self.assertEquals([[0, 0, 1]], calc(1))
        self.assertEquals([[0, 0, 4]], calc(4))

    def test_calc_nickel(self):
        self.assertEquals([[0, 0, 5], [0, 1, 0]], calc(5))
        self.assertEquals([[0, 0, 6], [0, 1, 1]], calc(6))

    def test_calc_dime(self):
        self.assertEquals([[0, 0, 10], [0, 1, 5], [0, 2, 0], [1, 0, 0], ], calc(10))
        self.assertEquals([[0, 0, 11], [0, 1, 6], [0, 2, 1], [1, 0, 1], ], calc(11))
        self.assertEquals([[0, 0, 15], [0, 1, 10], [0, 2, 5], [0, 3, 0], [1, 0, 5], [1, 1, 0], ], calc(15))
        print calc(100)
        print len(calc(100))



def cc(amount, factors, result, a_sln):
    print result, amount, factors, a_sln
    factor = factors.pop(0)

    if len(factors) == 0:
        a_sln = a_sln or []
        a_sln.extend([amount / factor])
        result.append(a_sln)
    else:
        for i in xrange(amount / factor + 1):
            a_sln_copy = list(a_sln)
            a_sln_copy.extend([i])
            cc(amount - i * factor, list(factors), result, a_sln_copy)
    return result

class CountCoinRecursiveTest(unittest.TestCase):

    def test_calc_penny(self):
        self.assertEquals([[4]], cc(4, [ONE], [], []))

    def test_calc_nickel(self):
        self.assertEquals([[0, 5], [1, 0]], cc(5, [FIVE, ONE], [], []))
        self.assertEquals([[0, 6], [1, 1]], cc(6, [FIVE, ONE], [], []))

    def test_calc_dime(self):
        self.assertEquals([[0, 0, 10], [0, 1, 5], [0, 2, 0], [1, 0, 0], ], cc(10, [TEN, FIVE, ONE], [], []))
        self.assertEquals([[0, 0, 11], [0, 1, 6], [0, 2, 1], [1, 0, 1], ], cc(11, [TEN, FIVE, ONE], [], []))
        self.assertEquals([[0, 0, 15], [0, 1, 10], [0, 2, 5], [0, 3, 0], [1, 0, 5], [1, 1, 0], ], cc(15, [TEN, FIVE, ONE], [], []))
        # print calc(100)
        # print len(calc(100))

if __name__ == '__main__':
    unittest.main()