'''
Created on 2013-10-8


'''
import unittest

## TODO list
## How to handle degenerate uses of the equals and ampersand signs?
## What if semicolon works like the ampersand?
class QueryStringTest(unittest.TestCase):

    def testOneNameValuePair(self):
        query = QueryString('name=value')
        self.assertEqual(1, query.count())
        self.assertEqual('value', query.valueFor('name'))

    def testNoNameValuePairs(self):
        query = QueryString('')
        self.assertEqual(0, query.count())

    ## three strikes and you refactor
    ## is there enough common?
    def testEmpty(self):
        with self.assertRaises(ValueError):
            query = QueryString(None)

    ## 0-1-N
    def testMultiNameValuePairs(self):
        query = QueryString('name1=value1&name2=value2&name3=value3')
        self.assertEqual('value1', query.valueFor('name1'))
        self.assertEqual('value2', query.valueFor('name2'))
        self.assertEqual('value3', query.valueFor('name3'))
        self.assertEqual(3, query.count())


class QueryString:
    def __init__(self, string):
        self.mapping = {}
        if string == None:
            raise ValueError
        self.string = string
        self._parse()

    def count(self):
        return len(self.mapping)
        
    def valueFor(self, name):
        return self.mapping[name]

    def _parse(self):
        if self.string == '':
            return 0

        pairs = self.string.split('&')
        for pair in pairs:
            nameAndValue = pair.split('=')
            self.mapping[nameAndValue[0]] = nameAndValue[1]

    # def count(self):
    #     if self.string == '':
    #         return 0
    #     pairs = self.string.split('&')
    #     return len(pairs)

    # def valueFor(self, name):
    #     pairs = self.string.split('&')
    #     for pair in pairs:
    #         nameAndValue = pair.split('=')
    #         if nameAndValue[0] == name:
    #             return nameAndValue[1]
        
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
