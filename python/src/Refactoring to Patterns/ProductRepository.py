'''
Created on 2013-8-13

@author: mebusw@gmail.com
'''

from datetime import date


class ProductRepository():
    def __init__(self):
        self.products = []
        self.foundProducts = []

    def add(self, product):
        self.products.append(product)

    def selectByOne(self, spec):
        for p in self.products:
            if spec.isSatisfiedBy(p):
                self.foundProducts.append(p)
        return self.foundProducts

    def selectByMany(self, specs):
        for p in self.products:
            satisfiesAllSpecs = True
            for s in specs:
                satisfiesAllSpecs &= s.isSatisfiedBy(p)
            if satisfiesAllSpecs:
                self.foundProducts.append(p)
        return self.foundProducts

class Product():
    def __init__(self, sid, name, color, price, size):
        self.sid = sid
        self.name = name
        self.color = color
        self.price = price
        self.size = size

class Spec:
    def __init__(self, name):
        self.name = name

    def isSatisfiedBy(self, product):
        raise Exception

class ColorSpec(Spec):
    def isSatisfiedBy(self, product):
        return product.color == self.name

class SizeSpec(Spec):
    def isSatisfiedBy(self, product):
        return product.size == self.name

class BelowPriceSpec(Spec):
    def isSatisfiedBy(self, product):
        return product.price < self.name
