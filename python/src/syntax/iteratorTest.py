import unittest

class IteratorTest(unittest.TestCase):
	def testFib1(self):
		self.assertEqual(1, fib1(1))
		self.assertEqual(1, fib1(2))
		self.assertEqual(2, fib1(3))
		self.assertEqual(3, fib1(4))
		self.assertEqual(5, fib1(5))

	def testFib2(self):
		f = fib2()
		self.assertEqual(1, f.next())
		self.assertEqual(1, f.next())
		self.assertEqual(2, f.next())
		self.assertEqual(3, f.next())
		self.assertEqual(5, f.next())

def fib1(n):
	if n <=2 :
		return 1
	return fib1(n-1)+fib1(n-2)

def fib2():
	a = b = 1
	yield a
	yield b
	while True:
		a, b = b, a+b
		yield b


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
