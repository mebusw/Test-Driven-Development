'''
Created on 2012-9-17

@author: 1411494
'''
import unittest


class Test(unittest.TestCase):


    def setUp(self):
        pass
        # import this


    def tearDown(self):
        pass


    def testReduceAndMap(self):
        assert 15 == reduce(lambda x, y: x + y, [1, 2, 3, 4, 5])
        assert [2, 4, 6] == map(lambda x: x * 2, [1, 2, 3]) 

    def testReturnMultiObj(self):
        def foo():
            return 3, 5.5
        
        alpha, beta = foo()
        assert alpha == 3 and beta == 5.5

    def testParsingXML(self):
        pass

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
