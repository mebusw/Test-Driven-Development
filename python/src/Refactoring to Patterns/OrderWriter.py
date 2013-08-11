'''
Created on 2013-8-11

@author: mebusw@gmail.com
'''

from datetime import date


class OrderWriter():
    def __init__(self, orders):
        self.orders = orders

    def getContents(self):
        return self.writeOrdersTo()

    def writeOrdersTo(self):
        ordersTag = TagNode('orders')
        for order in self.orders:
            orderTag = TagNode('order')
            orderTag.addAttribute('id', order['id'])
            self.writeProductsTo(orderTag, order)
            ordersTag.add(orderTag)
        return str(ordersTag)

    def writeProductsTo(self, orderTag, order):
        for product in order['products']:
            productTag = TagNode('product')
            productTag.addAttribute('id', product['id'])
            productTag.addAttribute('color', product['color'])
            if 'size' in product:
                productTag.addAttribute('size', product['size'])
            self.writePriceTo(productTag, product)
            productTag.addValue(product['name'])
            orderTag.add(productTag)
        return str(orderTag)

    def writePriceTo(self, productTag, product):
        priceTag = TagNode('price')
        priceTag.addAttribute('currency', product['currency'])
        priceTag.addValue(product['price'])       
        productTag.add(priceTag)

class TagNode:
    def __init__(self, name):
        self.name = name
        self.value = ""
        self.attribute = ""
        self.children = []

    def addAttribute(self, attribute, value):
        self.attribute += " %s='%s'" % (attribute, value)

    def addValue(self, value):
        self.value = value

    def add(self, child):
        self.children.append(child)

    def __str__(self):
        result = "<%s%s>" % (self.name, self.attribute)
        for child in self.children:
            result += str(child)
        result += "%s</%s>" % (self.value, self.name)
        return result
