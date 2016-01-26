"""
A field of N x M squares is represented by N lines
of exactly M characters each.
The character '*' represents a mine.
The character '.' represents no-mine.

Example input (a 3 x 4 mine-field of 12 squares,
2 of which are mines)

3 4
*...
..*.
....

Your task is to write a program to accept this input
and produce as output a hint-field of identical
dimensions where each square is a * for a mine or
the number of adjacent mine-squares if the square
does not contain a mine.

Example output (for the above input)
*211
12*1
0111
"""

import unittest


def tip(h, w, ori):
    ret = [[0] * w for _ in xrange(h)]

    left_inside = lambda x: x > 0
    right_inside = lambda x: x < w - 1
    up_inside = lambda x: x > 0
    down_inside = lambda x: x < h - 1
    is_mine = lambda j, i: ori[j][i] == '*'

    def rule(action):
        def inc(dj, di, *matchers):
            if all(matchers):
                action(j, i, dj, di)
        return inc

    def action_around(j, i, dj, di):
        ret[j][i] += 1 if ori[j + dj][i + di] == '*' else 0

    def action_center(j, i, dj, di):
        ret[j][i] = '*'

    around = rule(action_around)
    center = rule(action_center)

    for j in xrange(h):
        for i in xrange(w):
            around(0, -1, left_inside(i))
            around(0, 1, right_inside(i))
            around(-1, 0, up_inside(j))
            around(1, 0, down_inside(j))

            around(-1, -1, left_inside(i), up_inside(j))
            around(1, -1, left_inside(i), down_inside(j))
            around(-1, 1, right_inside(i), up_inside(j))
            around(1, 1, right_inside(i), down_inside(j))

            center(0, 0, is_mine(j, i))

    return ret


class ATest(unittest.TestCase):

    def test_1(self):
        self.assertEquals([['*', 1, 0, 0]], tip(1, 4, ['*...']))

    def test_2(self):
        self.assertEquals([['*', 2, 1, 1], [1, 2, '*', 1],
                           [0, 1, 1, 1]], tip(3, 4, ['*...', '..*.', '....']))

if __name__ == '__main__':
    unittest.main()
