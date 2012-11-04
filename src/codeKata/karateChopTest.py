'''
Created on 2012-10-16


'''
import unittest


def chop(target, lst):
    if len(lst) == 0:
        return -1
    left = 0
    right = len(lst)
    #print '-------', target, lst, left, right
    while(left <= right and left < len(lst)):
        mid = (right + left) / 2

        #print [left, mid, right]
        if(target == lst[mid]):
            return mid
        elif(target < lst[mid]):
            right = mid - 1
        elif(target > lst[mid]):
            left = mid + 1
          
    return -1

class KarateChopTest(unittest.TestCase):


    def testBinaryChop(self):
        self.assertEquals(-1, chop(3, []))
        self.assertEquals(-1, chop(3, [1]))
        self.assertEquals(0, chop(1, [1]))
        
        self.assertEquals(0, chop(1, [1, 3, 5]))
        self.assertEquals(1, chop(3, [1, 3, 5]))
        self.assertEquals(2, chop(5, [1, 3, 5]))
        self.assertEquals(-1, chop(0, [1, 3, 5]))
        self.assertEquals(-1, chop(2, [1, 3, 5]))
        self.assertEquals(-1, chop(4, [1, 3, 5]))
        self.assertEquals(-1, chop(6, [1, 3, 5]))
        
        self.assertEquals(0, chop(1, [1, 3, 5, 7]))
        self.assertEquals(1, chop(3, [1, 3, 5, 7]))
        self.assertEquals(2, chop(5, [1, 3, 5, 7]))
        self.assertEquals(3, chop(7, [1, 3, 5, 7]))
        self.assertEquals(-1, chop(0, [1, 3, 5, 7]))
        self.assertEquals(-1, chop(2, [1, 3, 5, 7]))
        self.assertEquals(-1, chop(4, [1, 3, 5, 7]))
        self.assertEquals(-1, chop(6, [1, 3, 5, 7]))
        self.assertEquals(-1, chop(8, [1, 3, 5, 7]))
        
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
