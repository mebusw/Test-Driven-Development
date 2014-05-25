import unittest

class KarateChopTest(unittest.TestCase):

    def generate(self, n):
        result = []
        candidate = 2
        while n > 1:
            while n % candidate == 0:
                result.append(candidate)
                n /= candidate
            candidate += 1
            # print n,candidate,result
        if n > 1:         
            result.append(n)
        return result

    def testGenerate(self):
        self.assertEquals([], self.generate(1))
        self.assertEquals([2], self.generate(2))
        self.assertEquals([3], self.generate(3))
        self.assertEquals([2, 2], self.generate(4))
        self.assertEquals([2, 3], self.generate(6))
        self.assertEquals([2, 3, 5], self.generate(30))
        self.assertEquals([3, 3], self.generate(9))




if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
