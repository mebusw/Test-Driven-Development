'''
Created on 2013-8-13

@author: mebusw@gmail.com
'''
import unittest
from mock import MagicMock, Mock
import time
from datetime import date
from CatalogApp import *

class CatalogAppTest(unittest.TestCase):
    def setUp(self):
        pass

    def testExecuteActionAndGetResponse_new_workshop(self):
        self.assertEquals(1, CatalogApp().executeActionAndGetResponse('new_workshop'))

    def testExecuteActionAndGetResponse_all_workshops(self):
        self.assertEquals(2, CatalogApp().executeActionAndGetResponse('all_workshops'))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()     