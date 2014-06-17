'''
Created on 2014-6-17

@author: mebusw@gmail.com
'''
import unittest
from Permission import *

class PermissionTest(unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testGrantedBy(self):
        permission = SystemPermission()
        self.assertEqual(REQUESTED, permission.state)
        self.assertFalse(permission.isGranted)

        permission.claimedBy()
        permission.grantedBy()

        self.assertEqual(GRANTED, permission.state)
        self.assertTrue(permission.isGranted)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()    
