'''
Created on 2014-6-17

@author: mebusw@gmail.com
'''
import unittest
from StringNode import *

class StringNodeTest(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testGrantedBy(self):
        stringNode = StringNode("abc&amp;def", True)

        self.assertEqual("abc&def", stringNode.toPlainTextString())

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()    
