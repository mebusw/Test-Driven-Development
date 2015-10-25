# -*- coding: utf-8 -*-
SULFURAS = "Sulfuras, Hand of Ragnaros"
AGED_BRIE = "Aged Brie"
BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert"
CONJURED = "Conjured Mana Cake"


class GildedRose(object):

    def __init__(self, items):
        self.items = items

    def update_quality(self):
        for item in self.items:
            self._decrease_sell_in(item)            
            self._decrease_quality(item)
            self._decrease_faster_after_sell_in(item)
            self._quality_should_between_0_and_50(item)

    def _decrease_sell_in(self, item):
        if item.name != SULFURAS:
            item.sell_in = item.sell_in - 1

    def _decrease_quality(self, item):
        if item.name == AGED_BRIE:
            item.quality += 1
            return

        if item.name == BACKSTAGE:
            item.quality += 1
            if item.sell_in < 11:
                item.quality += 1
            if item.sell_in < 6:
                item.quality += 1
            return

        if item.name == CONJURED:
            item.quality -= 2
            return

        if item.name != SULFURAS:
            item.quality -= 1
            return

        
    def _decrease_faster_after_sell_in(self, item):
        if item.sell_in >= 0:
            return

        if item.name == AGED_BRIE:
            item.quality += 1
            return

        if item.name == BACKSTAGE:
            item.quality = 0
            return

        if item.name == CONJURED:
            item.quality -= 2
            return

        if item.name != SULFURAS:
            item.quality -= 1
            return
        

    def _quality_should_between_0_and_50(self, item):
        item.quality = min(item.quality, 50)
        item.quality = max(item.quality, 0)


class Item:
    def __init__(self, name, sell_in, quality):
        self.name = name
        self.sell_in = sell_in
        self.quality = quality

    def __repr__(self):
        return "%s, %s, %s" % (self.name, self.sell_in, self.quality)