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

    def testParents(self):
        root = TagNode('root')
        self.assertIsNone(root.parent)

        child = TagNode('child')
        root.add(child)
        self.assertEquals(root, child.parent)        
        self.assertEquals('root', child.parent.name)        

class TagBuilderTest(unittest.TestCase):
    def testBuildOneNode(self):
        expected = "<flavors></flavors>"
        builder = TagBuilder("flavors")

        self.assertEquals(expected, str(builder))        

    def testBuildOneChild(self):
        builder = TagBuilder("flavors")
        builder.addChild('flavor')
        expected = "<flavors><flavor></flavor></flavors>"

        self.assertEquals(expected, str(builder))        

    def testBuildChildrenOfChildren(self):
        builder = TagBuilder("flavors")
        builder.addChild('flavor')
        builder.addChild('requirements')
        builder.addChild('requirement')
        expected = "<flavors><flavor><requirements><requirement></requirement></requirements></flavor></flavors>"

        self.assertEquals(expected, str(builder))        

    def testBuildSibling(self):
        builder = TagBuilder("flavors")
        builder.addChild('flavor1')
        builder.addSibling('flavor2')
        expected = "<flavors><flavor1></flavor1><flavor2></flavor2></flavors>"

        self.assertEquals(expected, str(builder))        

    def testRepeatingChildrenOfChildren(self):
        builder = TagBuilder("flavors")
        for i in xrange(2):
            builder.addToParent('flavors', 'flavor')
            builder.addChild('requirements')
            builder.addChild('requirement')
        expected = "<flavors>" \
            + "<flavor><requirements><requirement></requirement></requirements></flavor>" * 2 \
            + "</flavors>"

        self.assertEquals(expected, str(builder))        

    def testParentNameNotFound(self):
        expected = "<flavors>" \
                + "<flavor><requirements><requirement></requirement></requirements></flavor>" * 2 \
                + "</flavors>"
        wrongParentName = 'xyz'
        with self.assertRaises(Exception) as cm:
            builder = TagBuilder("flavors")
            for i in xrange(2):
                builder.addToParent(wrongParentName, 'flavor')
                builder.addChild('requirements')
                builder.addChild('requirement')
        self.assertEqual('Missing parent tag: %s' % wrongParentName, cm.exception.message)

        


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()     