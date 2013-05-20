'''
Created on 2012-5-29

'''
import unittest
from xUnit import WasRun, TestResult, TestSuite 

class TestCaseTest(unittest.TestCase):
    def setUp(self):
        self.result = TestResult()
        
    def testTemplateMethod(self):
        test = WasRun('testMethod')
        test.run(self.result)
        self.assertTrue(test.wasRun)
        self.assertEquals('setUp testMethod tearDown ', test.log)

    def testResult(self):
        test = WasRun('testMethod')
        result = test.run(self.result)
        self.assertEquals(result.summary(), '1 run, 0 failed')

    def testFailedResult(self):
        test = WasRun('testBrokenMethod')
        result = test.run(self.result)
        self.assertEquals(result.summary(), '1 run, 1 failed')

    def testSuite(self):
        suite = TestSuite()
        suite.add(WasRun('testMethod'))
        suite.add(WasRun('testBrokenMethod'))
        result = suite.run(self.result)
        self.assertEquals(result.summary(), '2 run, 1 failed')
                  
    def testFailedResultWithTearDown(self):
        test = WasRun('testBrokenMethod')
        result = test.run(self.result)
        self.assertEquals(result.summary(), '1 run, 1 failed')
        self.assertEquals('setUp testBrokenMethod tearDown ', test.log)

    #===========================================================================
    # def testFailedSetUp(self):
    #    test = WasRun('testMethod')
    #    test.setUp = self._failedSetUp()
    #    result = test.run(self.result)
    #    self.assertEquals(result.summary(), '1 run, 1 failed')
    #    self.assertEquals('tearDown ', test.log)
    #===========================================================================


                
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
