'''
Created on 2014-6-17

@author: mebusw@gmail.com
'''

class AbstractNode:
	pass


class StringNode(AbstractNode):
	@staticmethod
	def create():
		pass

	def __init__(self, textBuffer, shouldDecode):
		self.textBuffer = textBuffer
		self.shouldDecode = shouldDecode

	def toPlainTextString(self):
		result = str(self.textBuffer)
		if self.shouldDecode:
			result = Translate.decode(result)
		return result


class Translate:
	''' This is just a mock'''

	@staticmethod
	def decode(text):
		return text.replace('&amp;', '&')