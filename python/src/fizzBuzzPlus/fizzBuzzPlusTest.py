"""
Write a program that prints the numbers from 1 to 100, but...

numbers that are exact multiples of 3, 
or that contain 3, must print a string containing "Fizz"
   For example 9 -> "...Fizz..."
   For example 31 -> "...Fizz..."

numbers that are exact multiples of 5, 
or that contain 5, must print a string containing "Buzz"
   For example 10 -> "...Buzz..."
   For example 51 -> "...Buzz..."
"""

import unittest

def fb(num):
	result = ''
	s = str(num)
	if num % 3 == 0 or '3' in s:
		result += 'Fizz'
	if num % 5 == 0 or '5' in s:
		result += 'Buzz'
	return result or s

class FizzBuzzPlusTest(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_original(self):
        self.assertEqual('1', fb(1))
        self.assertEqual('2', fb(2))
        self.assertEqual('Fizz', fb(3))
        self.assertEqual('4', fb(4))
        self.assertEqual('Buzz', fb(5))
        self.assertEqual('Fizz', fb(9))
        self.assertEqual('FizzBuzz', fb(15))
        self.assertEqual('Buzz', fb(25))
        self.assertEqual('Fizz', fb(31))
        self.assertEqual('FizzBuzz', fb(51))

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
