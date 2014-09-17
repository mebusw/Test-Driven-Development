'''
@author: mebusw@gmail.com
'''
import unittest


class AnagramTest(unittest.TestCase):
    def setUp(self):
        pass

    def testSimple(self):
        anagram = Anagram()

        self.assertEqual([], anagram.find('', ['']))
        self.assertEqual([], anagram.find('a', ['a']))
        self.assertEqual(['a a'], anagram.find('aa', ['a', 'a']))
        self.assertEqual(['b b'], anagram.find('bb', ['b', 'b']))
        self.assertEqual(['a b'], anagram.find('ab', ['a', 'b']))
        self.assertEqual([], anagram.find('cd', ['x', 'c']))
        self.assertEqual(['de c'], anagram.find('cde', ['de', 'c']))
        self.assertEqual(['ji kh'], anagram.find('hijk', ['ji', 'kh']))

        self.assertEqual(['ji kh'], anagram.find('hijk', ['ji', '', 'kh']))
        self.assertEqual(['ji kh', 'k hji'], anagram.find('hijk', ['ji', 'k', 'kh', 'hji']))

###############
class Anagram:
    def find(self, string, patterns):
        if len(string) <= 1:
            return []

        result = []
        for i in xrange(len(patterns)):
            for j in xrange(i + 1, len(patterns)):
                if sorted(patterns[i] + patterns[j]) == sorted(string):
                    result.append(patterns[i] + ' ' + patterns[j])

        return result
        



if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
