'''
Created on 2014-5-22

@author: mebusw@gmail.com
'''
import unittest

class Rover:
    def __init__(self, state):
        self.x, self.y, self.direction = state.split(' ')
        self.x = int(self.x)
        self.y = int(self.y)

    def receive(self, command):
        if command == 'L':
            self.direction = {'W': 'S', 'N': 'W', 'S': 'E', 'E': 'N'}[self.direction]
        elif command == 'R':
            self.direction = {'S': 'W', 'W': 'N', 'N': 'E', 'E': 'S'}[self.direction]
        else:
            vector = {'W': (-1, 0), 'N': (0, 1), 'S': (0, -1), 'E': (1, 0)}[self.direction]
            self.x, self.y = map(lambda x, y: x + y, (self.x, self.y), vector)

class RoverTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testRoverCanRotateLeftFromNorthToWest(self):
        rover = Rover('1 2 N')
        self.assertEqual('N', rover.direction)

        rover.receive('L')

        self.assertEqual('W', rover.direction)

    def testRoverCanRotateFromWestToSouth(self):
        rover = Rover('1 2 W')

        rover.receive('L')

        self.assertEqual('S', rover.direction)

    def testRoverCanRotateFromSouthToEast(self):
        rover = Rover('1 2 S')

        rover.receive('L')

        self.assertEqual('E', rover.direction)

    def testRoverCanRotateFromEastToNorth(self):
        rover = Rover('1 2 E')

        rover.receive('L')

        self.assertEqual('N', rover.direction)


    def testRoverCanRotateRightFromNorthToEast(self):
        rover = Rover('1 2 N')

        rover.receive('R')

        self.assertEqual('E', rover.direction)

    def testRoverCanRotateRightFromEastToSouth(self):
        rover = Rover('1 2 E')

        rover.receive('R')

        self.assertEqual('S', rover.direction)


    def testRoverCanRotateCircally(self):
        rover = Rover('1 2 E')

        rover.receive('L')
        rover.receive('R')

        self.assertEqual('E', rover.direction)


    def testRoverCanMoveToNorth(self):
        rover = Rover('1 2 N')
        self.assertEqual(2, rover.y)
        self.assertEqual(1, rover.x)

        rover.receive('M')

        self.assertEqual(3, rover.y)
        self.assertEqual(1, rover.x)

    def testRoverCanMoveToSouth(self):
        rover = Rover('1 2 S')

        rover.receive('M')

        self.assertEqual(1, rover.y)

    def testRoverCanMoveToEast(self):
        rover = Rover('1 2 E')

        rover.receive('M')

        self.assertEqual(2, rover.x)

    def testRoverCanMoveToWest(self):
        rover = Rover('2 2 W')

        rover.receive('M')

        self.assertEqual(1, rover.x)


    def testRoverCanMoveToNorthTwice(self):
        rover = Rover('1 2 N')

        rover.receive('M')
        rover.receive('M')

        self.assertEqual(4, rover.y)
        self.assertEqual(1, rover.x)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
