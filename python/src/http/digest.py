'''
Created on 2012-9-24

'''
import unittest
import hashlib
import base64

class Digest():
    def digest(self, text):
        sh = hashlib.sha1(text).digest()
        ba = base64.b64encode(sh)
        return ba
    
class Test(unittest.TestCase):


    def testDigest(self):
        text = 'IBKWL' + 'SG' + '' + 'en' + 'sitcb024Q' + '100' + 'SGD' + 'makeCashin' + ''
        self.assertEqual('NkSY+yvIRybyIxemitWxvfSG9hc=', Digest().digest(text))


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testDigest']
    unittest.main()
