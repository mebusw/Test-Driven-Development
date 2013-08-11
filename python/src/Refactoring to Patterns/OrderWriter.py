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
        xml = ""
        xml += "<orders>"
        for order in self.orders:
            xml += "<order"
            xml += " id='"
            xml += order['id']
            xml += "'>"
            xml += self.writeProductsTo(order)
            xml += "</order>"
        xml += "</orders>"
        return xml

    def writeProductsTo(self, order):
        xml = ""
        for product in order['products']:
            xml += "<product"
            xml += " id='"
            xml += product['id']
            xml += "'"
            xml += " color='"
            xml += product['color']
            xml += "'"
            if 'size' in product:
                xml += " size='"
                xml += product['size']
                xml += "'"
            xml += ">"
            xml += self.writePriceTo(product)
            xml += product['name']
            xml += "</product>"
        return xml

    def writePriceTo(self, product):
        priceTag = TagNode('price')
        priceTag.addAttribute('currency', 'USD')
        priceTag.addValue('8.95')       
        return str(priceTag)

class TagNode:
    def __init__(self, name):
        self.name = name
        self.value = ""
        self.attribute = ""

    def addAttribute(self, attribute, value):
        self.attribute = " %s='%s'" % (attribute, value)

    def addValue(self, value):
        self.value = value

    def __str__(self):
        return "<%s%s>%s</%s>" % (self.name, self.attribute, self.value, self.name)

