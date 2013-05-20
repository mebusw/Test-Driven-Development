from bowlingGame import Bowling
import unittest

class TestUntitled(unittest.TestCase):
       
    def testMissAllSecond(self):
        b = Bowling()
        self.assertEqual(90, 
            b.calc('9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||'))

    def testFirstAndSecond(self):
        b = Bowling()
        self.assertEqual(90, 
            b.calc('81|81|81|81|81|81|81|81|81|81||'))

    def testMissAndSecond(self):
        b = Bowling()
        self.assertEqual(40, 
            b.calc('-4|-4|-4|-4|-4|-4|-4|-4|-4|-4||'))

    def testAllStrikes(self):
        b = Bowling()
        self.assertEqual(300, 
            b.calc('X|X|X|X|X|X|X|X|X|X||XX'))
        
    def testFirstAndSpare(self):
        b = Bowling()
        self.assertEqual(150, 
            b.calc('5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5'))
    
    def testNext1BallsDigit(self):
        b = Bowling()
        self.assertEqual(3, b.next1Balls(['12','34','56'], 0))

    def testNext1BallsMiss(self):
        b = Bowling()
        self.assertEqual(0, b.next1Balls(['12','-4','56'], 0))

    def testNext1BallsStrike(self):
        b = Bowling()
        self.assertEqual(10, b.next1Balls(['12','X','56'], 0))

    def testNext2BallsDigit(self):
        b = Bowling()
        self.assertEqual(7, b.next2Balls(['12','34','56'], 0))

    def testNext2BallsStrikeStrike(self):
        b = Bowling()
        self.assertEqual(20, b.next2Balls(['12','X','X'], 0))

    def testNext2BallsStrikeDigit(self):
        b = Bowling()
        self.assertEqual(11, b.next2Balls(['12','X','1'], 0))

    def testNext2BallsDigitMiss(self):
        b = Bowling()
        self.assertEqual(3, b.next2Balls(['12','3-','1'], 0))

    def testNext2BallsStrikeMiss(self):
        b = Bowling()
        self.assertEqual(10, b.next2Balls(['12','X','-6'], 0))

    def testNext2BallsMissMiss(self):
        b = Bowling()
        self.assertEqual(0, b.next2Balls(['12','--','56'], 0))

    def testNext2BallsMissSpare(self):
        b = Bowling()
        self.assertEqual(10, b.next2Balls(['12','-/','56'], 0))

    def testNext2BallsDigitSpare(self):
        b = Bowling()
        self.assertEqual(10, b.next2Balls(['12','3/','56'], 0))


if __name__ == '__main__':
    unittest.main()