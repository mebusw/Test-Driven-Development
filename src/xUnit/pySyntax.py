'''
Created on 2012-9-17

@author: 1411494
'''
import unittest


class Test(unittest.TestCase):


    def setUp(self):
        pass


    def tearDown(self):
        pass


    def testReduceAndMap(self):
        assert 15 == reduce(lambda x, y: x + y, [1, 2, 3, 4, 5])
        assert [2, 4, 6] == map(lambda x: x * 2, [1, 2, 3]) 


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
