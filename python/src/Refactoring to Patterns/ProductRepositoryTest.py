'''
Created on 2013-8-13

@author: mebusw@gmail.com
'''
import unittest
from mock import MagicMock, Mock
import time
from datetime import date
from ProductRepository import *

class ProductRepositoryTest(unittest.TestCase):
    def setUp(self):
        fireTruck = Product('f1234', 'Fire Truck', 'red', 8.95, 'medium')
        barbieClassic = Product('b7654', 'Barbie Classic', 'yellow', 15.95, 'small')
        toyConvertible = Product('p1122', 'Toy Porsche Convertible', 'red', 230, 'na')

        self.repository = ProductRepository()
        self.repository.add(fireTruck)
        self.repository.add(barbieClassic)
        self.repository.add(toyConvertible)

    def testFindByColor(self):
        foundProducts = self.repository.selectByOne(ColorSpec('red'))
        
        self.assertEquals(2, len(foundProducts))

    def testFindByColorSizeAndBelowPrice(self):
        specs = []
        specs.append(ColorSpec('red'))
        specs.append(SizeSpec('small'))
        specs.append(BelowPriceSpec(10))

        foundProducts = self.repository.selectByMany(specs)
        
        self.assertEquals(0, len(foundProducts))

       


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()     