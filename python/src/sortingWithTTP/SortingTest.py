'''
Created on 2014-1-12

@author: Jacky Shen
'''
import unittest

class SortingTest(unittest.TestCase):


    def setUp(self):
        self.sorter = Sorter()


    def tearDown(self):
        pass


    def testSorting(self):
        self.assertEqual([],self.sorter.sort([]))
        self.assertEqual([1],self.sorter.sort([1]))
        self.assertEqual([1,2],self.sorter.sort([1,2]))
        self.assertEqual([1,2],self.sorter.sort([2,1]))
        self.assertEqual([1,2,3],self.sorter.sort([1,2,3]))
        self.assertEqual([1,2,3],self.sorter.sort([2,1,3]))
        self.assertEqual([1,2,3],self.sorter.sort([1,3,2]))
        self.assertEqual([1,2,3],self.sorter.sort([3,2,1]))
        
class Sorter:
    def sort(self, numbers):
        return self.st(numbers)

    def st(self, l):
        if len(l) < 2:
            return l
        eq = l[0]
        lt = []
        gt = []
        for e in l:
            if e < eq:
                lt.append(e)
            if e > eq:
                gt.append(e)
        result = []
        result.extend(self.st(lt))
        result.append(eq)
        result.extend(self.st(gt))
        return result

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
