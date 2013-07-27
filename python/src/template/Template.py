'''
Created on 2013-7-27

@author: mebusw@gmail.com

@ref "Practical TDD and Acceptance TDD for Java Developers" by Lasse Koskela
'''

import re

class MissingValueException(Exception):
	pass

class Template(object):
	def __init__(self, templateText):
		self.templateText = templateText
		self.variables = {}

	def set(self, variable, value):
		self.variables[variable] = value

	def evaluate(self):
		result = self._replaceVariables()
		self._checkForMissingValues(result)
		return result

	def _replaceVariables(self):
		result = self.templateText
		for (k, v) in self.variables.iteritems():
			result = result.replace('${%s}' % k, v)
		return result

	def _checkForMissingValues(self, result):
		r = re.search('.*\$\{.+\}.*', result)
		if r:
			raise MissingValueException('No value for %s' % r.group())

