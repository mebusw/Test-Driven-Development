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
        compositeSpec = CompositeSpec(specs)

        for product in self.products:
            if compositeSpec.isSatisfiedBy(product):
                self.foundProducts.append(product)                        
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

class CompositeSpec(Spec):
    def __init__(self, specs):
        self.specs = specs

    def isSatisfiedBy(self, product):
        satisfiesAllSpecs = True
        for s in self.specs:
            satisfiesAllSpecs &= s.isSatisfiedBy(product)
        return satisfiesAllSpecs