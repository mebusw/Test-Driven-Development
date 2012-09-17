'''
Created on 2012-9-17

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
    
    def testEnum(self):
        class Enum(object):
            def __init__(self, *keys):
                self.__dict__.update(zip(keys, range(len(keys))))
            
        x = Enum('foo', 'bar', 'baz', 'bat')
        self.assertEqual(2, x.baz)
        self.assertEqual(3, x.bat)
        
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
