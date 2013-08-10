'''
Created on 2013-8-10

@author: mebusw@gmail.com
'''
import unittest
from mock import MagicMock, Mock
import time
from datetime import date
from Loan import Loan, CapitalStrategy

class LoanTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testItCanGetReadyTheCar(self):
        stubStatusPanel = Mock('StatusPanel')
        self.assertEquals(2, 2)
        d = date.today()
        print date.fromtimestamp(time.time())
        print date(2013, 8, 15)
        print d.timetuple()
        print d.strftime("%A %d. %B %Y")
        print (d - date(2013, 8, 15)).days

    def testNewLoan(self):
        termLoan = Loan.createTermLoan(300, 0.5, 0.8, CapitalStrategy())
        self.assertEquals(300, termLoan.commitment)
        self.assertEquals(120, termLoan.capital())

        revolver = Loan.createRevolver(300, 0.5, 0.8, date(2013, 8, 15))
        self.assertTrue(None != revolver.expiry)
        
        RCTL = Loan.createRCTL(300, 0.5, 0.8, 200)
        self.assertEquals(200, RCTL.outstanding)

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()     