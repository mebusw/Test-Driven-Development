import unittest
from pipe import * 

class PipeTest(unittest.TestCase):

	def testPipe(self):
		self.assertEqual(10, range(5) | add)
		self.assertEqual([0, 2, 4, 6, 8], range(10) | where(lambda x: x % 2 == 0) | as_list)
		self.assertEqual(90, range(100) | where(lambda x: x%2==0)\
			 | take_while(lambda x: x < 20) | add)
		self.assertEqual([0, 2, 4], range(100) | where(lambda x: x%2==0)\
			 | take_while_idx(lambda x: x < 3) | as_list)


@Pipe
def take_while_idx(iterable, predicate): 
	for idx, x in enumerate(iterable):
		if predicate(idx): 
			yield x
		else: 
			return

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
