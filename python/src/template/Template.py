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

class TemplateParser(object):
	def parse(self, templateText):
		segments = []
		index = self._collectSegments(segments, templateText)
		self._addTail(segments, templateText, index)
		self._addEmptyStringIfTemplateWasEmpty(segments)
		return tuple(segments)

	def _collectSegments(self, segments, src):
		index = 0
		for matchObject in re.finditer('\$\{[^}]*\}', src):
			self._addPrecedingPlainText(segments, src, matchObject, index)
			self._addVariable(segments, src, matchObject)
			index = matchObject.end()
		return index

	def _addPrecedingPlainText(self, segments, src, matchObject, index):
		if index != matchObject.start():
			segments.append(src[index:matchObject.start()])

	def _addVariable(self, segments, src, matchObject):
		segments.append(src[matchObject.start():matchObject.end()])

	def _addTail(self, segments, src, index):
		if index < len(src):
			segments.append(src[index:])

	def _addEmptyStringIfTemplateWasEmpty(self, segments):
		if len(segments) == 0:
			segments.append('')

#r = re.finditer('\$\{[^}]*\}', '${a}:${b}:${c}')
#print rr.group(), rr.start(), rr.end()