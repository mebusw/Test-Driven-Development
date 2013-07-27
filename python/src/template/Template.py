'''
Created on 2013-7-27

@author: mebusw@gmail.com

@ref "Practical TDD and Acceptance TDD for Java Developers" by Lasse Koskela
'''


class Template(object):
	def __init__(self, templateText):
		self.templateText = templateText

	def set(self, variable, value):
		self.variableValue = value

	def evaluate(self):
		return self.templateText.replace('${name}', self.variableValue)
		# return 'Hello, %s' % self.variableValue
