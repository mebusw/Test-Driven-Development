#!encoding=utf8
"""
https://jinshuju.net/f/n0ddSe

TODO

generate object
calc
	- //only one product with 1 amount
	- //only one product with more amount
	- //sum of more
	- //has 2-1 product, discount_list
	- //has 95% product
	- has 2-1 and 95% product
	- check null
print receipt
parse input
	- simple
	- with amount
"""
import unittest
from Cashier import *
from itertools import groupby


def parse(items):
    def conv(item):
        seq = item.split('-')
        code = seq[0]
        qty = seq[1] if len(seq) > 1 else 1
        return code, int(qty)

    bulks = {}
    for item in items:
        code, qty = conv(item)

        if bulks.has_key(code):
            bulks[code] += qty
        else:
            bulks[code] = qty

    # output = groupby(items, key=conv)
    # for m, n in output:
    #     print(m)
    #     print len(list(n))
    return bulks


"""
***<没钱赚商店>购物清单***
名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)
名称：羽毛球，数量：5个，单价：1.00(元)，小计：4.00(元)
名称：苹果，数量：2斤，单价：5.50(元)，小计：11.00(元)
----------------------
买二赠一商品：
名称：可口可乐，数量：1瓶
名称：羽毛球，数量：1个
----------------------
总计：21.00(元)
节省：4.00(元)
**********************
"""


class CashierTest(unittest.TestCase):
    def setUp(self):
        self.receipt = Receipt()
        self.inventory = {'ITEM000001': Product('可口可乐', 3.00), 'ITEM000003': Product('苹果', 5.50),
                          'ITEM000005': Product('羽毛球', 1.00)}

    def test_more_products_in_inventory(self):
        self.receipt.add(Item(self.inventory['ITEM000001'], 1), Item(self.inventory['ITEM000003'], 1),
                         Item(self.inventory['ITEM000005'], 1))

        self.assertEquals(3, self.receipt.size())

    def test_sum_of_products_in_inventory(self):
        self.receipt.add(Item(self.inventory['ITEM000001'], 2), Item(self.inventory['ITEM000003'], 1),
                         Item(self.inventory['ITEM000005'], 1))

        self.assertEquals(12.50, self.receipt.total())

    def test_sum_of_products_with_2_1_discount(self):
        self.receipt.add(Item(self.inventory['ITEM000001'], 3, func=lambda p, a: a / 3 * 2 * p))

        self.assertEquals(3 * 2, self.receipt.total())

    def test_sum_of_products_with_95_discount(self):
        self.receipt.add(Item(self.inventory['ITEM000001'], 1, func=lambda p, a: a * p * .95))

        self.assertEquals(3 * .95, self.receipt.total())

    def test_parsing_code(self):
        scan_list = [
            'ITEM000001',
            'ITEM000001',
            'ITEM000001',
            'ITEM000001',
            'ITEM000001',
            'ITEM000003-2',
            'ITEM000005',
            'ITEM000005',
            'ITEM000005'
        ]
        output = parse(scan_list)
        self.assertEqual(
            {'ITEM000005': 3, 'ITEM000003': 2, 'ITEM000001': 5}, output)


if __name__ == '__main__':
    unittest.main()
