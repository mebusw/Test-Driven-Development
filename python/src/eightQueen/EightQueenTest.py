"""Place eight chess queens on an 8x8 chessboard so that
no two queens threaten each other. Thus, a solution
requires that no two queens share the same row,
column, or diagonal.

[source: http://en.wikipedia.org/wiki/Eight_queens_puzzle]"""


import unittest

EIGHT = 8
x, a, b, c = [-1]*EIGHT, [True]*EIGHT*3, [True]*EIGHT*3, [True]*EIGHT*3


def queen(i, done):
  j = 0
  while not done and j < EIGHT:
    # print i, j, x, done
    done = False
    if a[j] and b[i+j] and c[i-j]:
      x[i] = j
      # print '*', x
      a[j] = False
      b[i+j] = False
      c[i-j] = False
      if i < EIGHT - 1:
        done = queen(i+1, done)
        if not done:
          # print '$'
          a[j] = True
          b[i+j] = True
          c[i-j] = True
      else:
        done = True
    j += 1
  return done


class EightQueenTest(unittest.TestCase):

  def test_queen(self):
    done = False
    done = queen(0, done)
    if done:
      print '>>>>>>', done, x

if __name__ == '__main__':
    unittest.main()