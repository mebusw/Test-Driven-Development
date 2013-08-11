'''
Created on 2013-8-11

@author: mebusw@gmail.com
'''
import unittest
from mock import MagicMock, Mock
import time
from datetime import date
from OrderWriter import *

class OrderWriterTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testGetContents(self):
        orders = [ {'id':'321', 'products':[{ \
                            'id':'f1234', 'color':'red', 'size':'medium', 'currency':'USD', 'price':'8.95', 'name':'Fire Truck' \
                            }] \
                    } \
            ]
        expected = "<orders>" \
            + "<order id='321'>" \
            + "<product id='f1234' color='red' size='medium'>" \
            + "<price currency='USD'>" \
            + "8.95" \
            + "</price>" \
            + "Fire Truck" \
            + "</product>" \
            + "</order>" \
            + "</orders>"

        ordersWriter = OrderWriter(orders)

        self.assertEquals(expected, ordersWriter.getContents())

class TagNodeTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testSimpleTagWIthOneAttributeAndValue(self):
        priceTag = TagNode('price')
        priceTag.addAttribute('currency', 'USD')
        priceTag.addValue('8.95')
        expected = "<price currency='USD'>8.95</price>"

        self.assertEquals(expected, str(priceTag))

    def testCompositeTagOneChild(self):
        productTag = TagNode('product')
        productTag.add(TagNode('price'))
        expected = "<product><price></price></product>"

        self.assertEquals(expected, str(productTag))        

    def testAddingChildrenAndGrandchildren(self):
        ordersTag = TagNode('orders')
        orderTag = TagNode('order')
        productTag = TagNode('product')
        ordersTag.add(orderTag)
        orderTag.add(productTag)
        expected = "<orders><order><product></product></order></orders>"

        self.assertEquals(expected, str(ordersTag))        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()     