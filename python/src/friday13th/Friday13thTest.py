"""Write a program to show that the 13th day of the month
falls more often on a Friday than any other day of the
week. The 1st of January 1973 was a Monday.
You should aim at producing the clearest possible
program, not the fastest.

[source: BCPL the language and its compiler
by Martin Richards and Colin Whitby-Strevens]
"""


import unittest
from datetime import datetime, timedelta
from pipe import *

def days():
    day = datetime(1973, 1, 1)
    for i in xrange(100000):
      day += timedelta(days=1)
      if day.day == 13:
        yield day

class Friday13thTest(unittest.TestCase):

  def test_13th_is_Friday_mostly(self):
    stat = days() | groupby(lambda x: x.isoweekday()) | select(lambda x: (x[0], x[1] | count))
    most = stat | sort(key=lambda x: x[1], reverse=True) | first
    self.assertEqual(5, most[0])


if __name__ == '__main__':
    unittest.main()