'''
Created on 2013-7-27

@author: mebusw@gmail.com

@ref "Practical TDD and Acceptance TDD for Java Developers" by Lasse Koskela
'''
import unittest
from mock import MagicMock, Mock

from Template import *

''' TODO
* template variable can be replaced
* error when not variable were set
* ignore non-exist variable
* template support latin characters
* variable support latin characters

'''

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

    def testMultipleVariables(self):
    	template = Template('${one}, ${two}, ${three}')
    	template.set('one', '1')
    	template.set('two', '2')
    	template.set('three', '3')
    	self.assertEqual("1, 2, 3", template.evaluate())

    def testUnknownVariableAreIgnored(self):
    	template = Template('Hello, ${name}')
    	template.set('name', 'Reader')
    	template.set('doesnotexitst', 'Hi')
    	self.assertEqual("Hello, Reader", template.evaluate())


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()   