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
        self.template = Template('${one}, ${two}, ${three}')
    	self.template.set('one', '1')
    	self.template.set('two', '2')
    	self.template.set('three', '3')

    def tearDown(self):
        pass

    def testMultipleVariables(self):
    	self.assertTemplateEnaluatesTo("1, 2, 3")

    def testUnknownVariableAreIgnored(self):
    	self.template.set('doesnotexitst', 'Hi')
    	self.assertTemplateEnaluatesTo("1, 2, 3")

    def assertTemplateEnaluatesTo(self, expected):
		self.assertEqual(expected, self.template.evaluate())    	

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()   