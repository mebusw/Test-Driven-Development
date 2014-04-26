#!/usr/bin/python
# -*- coding: utf-8 -*-

'''
Created on 2014-4-20

@author: mebusw@gmail.com
'''


class Spot(object):
    """Spot represent a step on the map"""
    def __init__(self, name):
        super(Spot, self).__init__()
        self.name = name
        
class ChanceSpot(Spot):
    def __init__(self):
        Spot.__init__(self, '机会')

class WelfareSpot(Spot):
    def __init__(self):
        Spot.__init__(self, '公益福利')

class StartingSpot(Spot):
    def __init__(self):
        Spot.__init__(self, '起点')

class PrisonSpot(Spot):
    def __init__(self):
        Spot.__init__(self, '监狱')

class ParkingSpot(Spot):
    def __init__(self):
        Spot.__init__(self, '泊车')

class ArrestingSpot(Spot):
    def __init__(self):
        Spot.__init__(self, '入狱')

class InterestTaxSpot(Spot):
    def __init__(self):
        Spot.__init__(self, '利息税')

class IncomeTaxSpot(Spot):
    def __init__(self):
        Spot.__init__(self, '所得税')

'''
22土地、4车站、2公共事业
'''

class RealEstateSpot(Spot):
    def __init__(self, name, price, section):
        Spot.__init__(self, name)
        self.price = price
        self.section = section
        section.add(self)
        self.owner = None
        self.buildingLevel = 0
        self.canBuild = True

    def purchasePrice(self):
        return self.price

    def rentPrice(self):
        return self.purchasePrice() * 0.15 * (self.buildingLevel + 1)

class RealEstateSection:
    def __init__(self, name):
        self.name = name
        self.landProperties = []

    def add(self, landProperty):
        self.landProperties.append(landProperty)

