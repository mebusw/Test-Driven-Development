'''
Created on 2013-8-11

@author: mebusw@gmail.com
'''

from datetime import date


class OrderWriter():
    def __init__(self, orders):
        self.orders = orders

    def getContents(self):
        xml = ""
        xml += "<orders>"
        for order in self.orders:
            xml += "<order"
            xml += " id='"
            xml += order['id']
            xml += "'>"
            #
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
                #
                xml += "<price"
                xml += " currency='"
                xml += product['currency']
                xml += "'>"
                xml += product['price']
                xml += "</price>"
                #
                xml += product['name']
                xml += "</product>"
            #
            xml += "</order>"
        xml += "</orders>"
        return xml
