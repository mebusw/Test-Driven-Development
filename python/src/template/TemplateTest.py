'''
Created on 2013-7-27

@author: mebusw@gmail.com

@ref "Practical TDD and Acceptance TDD for Java Developers" by Lasse Koskela
'''
import unittest
from mock import MagicMock, Mock
import time

from Template import *


''' TODO
* template variable can be replaced
* error when not variable were set
* ignore non-exist variable
* template support latin characters
* variable support latin characters
* able to handle '${foo}" as value of variable '${bar}'
* evaluate should complete within 200ms for 100 words + 5 variables of 15+ chars
* handle when set variable to variable
'''

class TemplateTest(unittest.TestCase):
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

    def testVariableGetProcessedJustOnce(self):
    	try:
	    	self.template.set('one', '${one}')
	    	self.template.set('two', '${three}')
	    	self.template.set('three', '${two}')
	    	self.assertTemplateEnaluatesTo("${one}, ${two}, ${three}")
    	except MissingValueException as e:
    		pass
    		#TODO

    def testMissingValueRaisesException(self):
    	with self.assertRaises(MissingValueException) as cm:
    		Template('${foo}').evaluate()
    	self.assertEqual('No value for ${foo}', cm.exception.message)


    def assertTemplateEnaluatesTo(self, expected):
		self.assertEqual(expected, self.template.evaluate())    	

class TemplatePerformanceTest(unittest.TestCase):
    def setUp(self):
        self.template = Template('''Returns one or more subgroups of the match. 
        	If there is a single argument, the result is a single string; 
        	if there are multiple arguments, the result is a tuple with one item per argument. 
        	Without arguments, group1 defaults to zero (the whole match is returned).
        	${onezzzzzzzzzzz}, ${twozzzzzzzzzzz}, ${threezzzzzzzzz}, ${fourzzzzzzzzzz}, ${fivezzzzzzzzzz}''')
    	self.template.set('onezzzzzzzzzzz', '1')
    	self.template.set('twozzzzzzzzzzz', '2')
    	self.template.set('threezzzzzzzzz', '3')
    	self.template.set('fourzzzzzzzzzz', '4')
    	self.template.set('fivezzzzzzzzzz', '5')

    def testTemplateWith100WordsAnd5Variables(self):
    	self.template.evaluate()
    	expectedTimeGap = 0.2
    	actualTimeGap = time.clock()
    	self.assertLess(actualTimeGap, 0.2)

class TemplateParserTest(unittest.TestCase):
    def setUp(self):
    	self.parser = TemplateParser()

    def testEmptyTemplateRendersAsEmptyString(self):   	
    	segments = self.parser.parse('')
    	self.assertEqual(1, len(segments))
    	self.assertEqual('', segments[0])

    def testWithOnlyPlainText(self):
    	segments = self.parser.parse('plain text only')
    	self.assertSegments(segments, 'plain text only')

    def testParsingMultipleVariables(self):
    	segments = self.parser.parse('${a}:${b}:${c}')
    	self.assertSegments(segments, '${a}', ':', '${b}', ':', '${c}')


    def assertSegments(self, actual, *expected):
    	self.assertEqual(len(expected), len(actual))
    	self.assertEqual(expected, actual)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()   