'''
Created on 2013-7-27

@author: mebusw@gmail.com

@ref "Practical TDD and Acceptance TDD for Java Developers" by Lasse Koskela
'''
import unittest
from mock import MagicMock, Mock

from Template import *


class CarControllerTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testOneVariable(self):
    	template = Template('Hello, ${name}')
    	template.set('name', 'Reader')
    	self.assertEqual("Hello, Reader", template.evaluate())

    def testDifferentTemplate(self):
    	template = Template('Hi, ${name}')
    	template.set('name', 'someone else')
    	self.assertEqual("Hi, someone else", template.evaluate())



if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()   