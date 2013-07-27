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
		parser = TemplateParser()
		segments = parser.parse(self.templateText)
		return self._concatenate(segments)

	def _concatenate(self, segments):
		self.result = ''
		for seg in segments:
			self._append(seg)
		return self.result

	def _append(self, seg):
		if self._isVariable(seg):
			self._evaluateVariable(seg)
		else:
			self.result += seg

	def _isVariable(self, seg):
		return seg.startswith('${') and seg.endswith('}')

	def _evaluateVariable(self, seg):
		k = seg[2:-1]
		if not k in self.variables:
			raise MissingValueException('No value for %s' % seg)
		self.result += self.variables[k]
	

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

