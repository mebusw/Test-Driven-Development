# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose

class GildedRoseTest(unittest.TestCase):
    def assert_match(self, expected, item):
    	return self.assertEquals(expected, str(item))

    def update_for(self, items, day):
        gilded_rose = GildedRose(items)
    	for x in xrange(day):
    		gilded_rose.update_quality()
    	return gilded_rose

    def test_normal(self):
        items = [Item(name="+5 Dexterity Vest", sell_in=10, quality=20)]
        gilded_rose = self.update_for(items, day=1)
        self.assert_match("+5 Dexterity Vest, 9, 19", items[0])

    def test_Once_the_sell_by_date_has_passed_Quality_degrades_twice_as_fast(self):
        items = [Item(name="+5 Dexterity Vest", sell_in=10, quality=20)]
        gilded_rose = self.update_for(items, day=11)
        self.assert_match("+5 Dexterity Vest, -1, 8", items[0])

    def test_The_Quality_of_an_item_is_never_negative(self):
        items = [Item(name="+5 Dexterity Vest", sell_in=10, quality=20)]
        gilded_rose = self.update_for(items, day=19)
        self.assert_match("+5 Dexterity Vest, -9, 0", items[0])


    def test_Aged_Brie_actually_increases_in_Quality_the_older_it_gets(self):
        items = [Item(name="Aged Brie", sell_in=2, quality=0)]
        gilded_rose = self.update_for(items, day=1)
        self.assert_match("Aged Brie, 1, 1", items[0])

    #TODO
    def test_Aged_Brie_actually_increases_2_in_Quality_when_sell_in_is_negtive(self):
        items = [Item(name="Aged Brie", sell_in=2, quality=0)]
        gilded_rose = self.update_for(items, day=3)
        self.assert_match("Aged Brie, -1, 4", items[0])

    def test_The_Quality_of_an_item_is_never_more_than_50(self):
        items = [
        	Item(name="Aged Brie", sell_in=2, quality=49), 
        	Item(name="Backstage passes to a TAFKAL80ETC concert", sell_in=2, quality=48),
        ]
        gilded_rose = self.update_for(items, day=2)
        self.assert_match("Aged Brie, 0, 50", items[0])
        self.assert_match("Backstage passes to a TAFKAL80ETC concert, 0, 50", items[1])

    def test_Sulfuras_being_a_legendary_item_never_has_to_be_sold_or_decreases_in_Quality(self):
        items = [             
        	Item(name="Sulfuras, Hand of Ragnaros", sell_in=0, quality=80),
            Item(name="Sulfuras, Hand of Ragnaros", sell_in=-1, quality=80),
		]
        gilded_rose = self.update_for(items, day=1)
        self.assert_match("Sulfuras, Hand of Ragnaros, 0, 80", items[0])
        self.assert_match("Sulfuras, Hand of Ragnaros, -1, 80", items[1])


    def test_Backstage_passes_increases_in_Quality_as_its_SellIn_value_approaches(self):
        items = [Item(name="Backstage passes to a TAFKAL80ETC concert", sell_in=15, quality=20)]
        gilded_rose = self.update_for(items, day=1)
        self.assert_match("Backstage passes to a TAFKAL80ETC concert, 14, 21", items[0])

    def test_Backstage_passes_increases_in_Quality_by_2_when_there_are_10_days_or_less(self):
        items = [Item(name="Backstage passes to a TAFKAL80ETC concert", sell_in=8, quality=20)]
        gilded_rose = self.update_for(items, day=1)
        self.assert_match("Backstage passes to a TAFKAL80ETC concert, 7, 22", items[0])

    def test_Backstage_passes_increases_in_Quality_by_3_when_there_are_5_days_or_less(self):
        items = [Item(name="Backstage passes to a TAFKAL80ETC concert", sell_in=5, quality=20)]
        gilded_rose = self.update_for(items, day=1)
        self.assert_match("Backstage passes to a TAFKAL80ETC concert, 4, 23", items[0])

    def test_Backstage_passes_increases_in_Quality_drops_to_0_after_the_concert(self):
        items = [Item(name="Backstage passes to a TAFKAL80ETC concert", sell_in=5, quality=20)]
        gilded_rose = self.update_for(items, day=6)
        self.assert_match("Backstage passes to a TAFKAL80ETC concert, -1, 0", items[0])

    ###########
    def test_Conjured_items_degrade_in_Quality_twice_as_fast_as_normal_items(self):
        items = [Item(name="Conjured Mana Cake", sell_in=3, quality=6)]
        gilded_rose = self.update_for(items, day=1)
        self.assert_match("Conjured Mana Cake, 2, 4", items[0])




if __name__ == '__main__':
    unittest.main()