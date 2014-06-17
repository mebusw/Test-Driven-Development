'''
Created on 2014-6-17

@author: mebusw@gmail.com
'''

class AbstractNode:
	def __init__(self):
		pass


		
class StringNode(AbstractNode):
	def __init__(self, textBuffer):
		AbstractNode.__init__(self)
		self.textBuffer = textBuffer
		# self.shouldDecode = shouldDecode

	def toPlainTextString(self):
		result = str(self.textBuffer)
		return result


class DecodingNode(AbstractNode):
	"""docstring for DecodingNode"""
	def __init__(self, delegate):
		self.delegate = delegate

	def toPlainTextString(self):
		return Translate.decode(self.delegate.toPlainTextString())

class Translate:
	''' This is just a mock'''

	@staticmethod
	def decode(text):
		return text.replace('&amp;', '&')