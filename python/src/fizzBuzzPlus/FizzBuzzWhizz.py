import unittest

def times(n, s):
    return lambda x: s if x % n == 0 else ''
times3 = times(3, 'Fizz')
times5 = times(5, 'Buzz')
times7 = times(7, 'Whizz')

def allof(*rules):
    return lambda x: reduce(lambda s, rule: s + rule(x), rules, '')


class FizzBuzzWhizzTest(unittest.TestCase):

    def test_rule_1(self):
        self.assertEqual('Fizz', times3(3))
        self.assertEqual('Fizz', allof(times3)(3))
        self.assertEqual('Buzz', allof(times5)(5))

    def test_rule_2(self):
        self.assertEqual('FizzBuzz', allof(times3, times5)(15))

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()


