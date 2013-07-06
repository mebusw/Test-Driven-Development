'''
Created on 2013-7-6

@author: mebusw@gmail.com
'''
import unittest
from bookDiscount import *

'''
this is not suitable for greedy algorithm but dynamic programming.

Harry Potter series has 5 volumes, each cost $8. 
If you buy two volumes, you get 5% discount, 10% for 3 volumes, 20% for 4 volumes, 25% for 5 volumes.
How should you buy to save money?

Given F(Y1, Y2, Y3, Y4, Y5) as the min cost. 
Because the volume number doesn't matters, so the sequence of volumes is nothing, 
i.e. F(2,1,1,1,1)==F(1,2,1,1,1)==F(1,1,2,1,1)=...  (Y1>=Y2>=Y3>=Y4>=Y5)

F(Y1,Y2,Y3,Y4,Y5) 
=0                                                              if(Y1=Y2=Y3=Y4=Y5=0) 
=min{ 
        40*0.75+F(Y1-1,Y2-1,Y3-1,Y4-1,Y5-1) ,                   if(Y5>=1) 
        32*0.8+F(Y1-1,Y2-1,Y3-1,Y4-1,Y5)  ,                     if(Y4>=1) 
        24*0.9+F(Y1-1,Y2-1,Y3-1,Y4,Y5) ,                        if(Y3>=1) 
        16*0.95+F(Y1-1,Y2-1,Y3,Y4,Y5) ,                         if(Y2>=1) 
        8+F(Y1-1,Y2,Y3,Y4,Y5) ,                                 if(Y1>=1) 
} 

'''	

class BookDiscountTest(unittest.TestCase):
    def setUp(self):
    	pass

    def tearDown(self):
        pass

    def testMinCost(self):
        self.assertEqual(51.2, BookDiscount().findMinCost([2, 2, 2, 1, 1]))
        self.assertEqual(70.4, BookDiscount().findMinCost([4, 3, 2, 1, 0]))
        ### TODO to be TDD

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
