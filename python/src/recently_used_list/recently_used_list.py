"""
Develop a recently-used-list class to hold strings
uniquely in Last-In-First-Out order.

o) A recently-used-list is initially empty.

o) The most recently added item is first, the least
   recently added item is last.

o) Items can be looked up by index, which counts from zero.

o) Items in the list are unique, so duplicate insertions
   are moved rather than added.

Optional extras

o) Null insertions (empty strings) are not allowed.

o) A bounded capacity can be specified, so there is an upper
   limit to the number of items contained, with the least
   recently added items dropped on overflow.
"""
import unittest


class RecentlyUsedList(object):
    def __init__(self, capacity):
        self.capacity = capacity
        self._m = []

    @property
    def size(self):
        return len(self._m)

    def show(self):
        return self._m

    def add(self, item):
        if item and item not in self._m:
            if len(self._m) == self.capacity:
                self._m.pop()
            self._m.insert(0, item)

    def __getitem__(self, index):
        return self._m[index]


class RecentlyUsedListTest(unittest.TestCase):
    def setUp(self):
        self.lst = RecentlyUsedList(10)

    def test_initial_an_empty_list(self):
        self.assertEquals(0, self.lst.size)

    def test_list_is_in_LIFO_order(self):
        self.lst.add('1st')
        self.lst.add('2nd')

        self.assertEquals(2, self.lst.size)
        self.assertEquals(['2nd', '1st'], self.lst.show())

    def test_list_can_be_looked_up(self):
        self.lst.add('1st')
        self.lst.add('2nd')

        self.assertEquals('2nd', self.lst[0])
        self.assertEquals('1st', self.lst[1])

    def test_item_is_unique(self):
        self.lst.add('1st')
        self.lst.add('1st')
        self.lst.add('2nd')

        self.assertEquals(2, self.lst.size)
        self.assertEquals('2nd', self.lst[0])
        self.assertEquals('1st', self.lst[1])

    def test_null_insertion_is_not_allowed(self):
        self.lst.add('')

        self.assertEquals(0, self.lst.size)

    def test_oldest_item_is_dropped_when_over_capacity(self):
        self.lst = RecentlyUsedList(2)

        self.lst.add('1st')
        self.lst.add('2nd')
        self.lst.add('3rd')

        self.assertEquals(2, self.lst.size)
        self.assertEquals('3rd', self.lst[0])
        self.assertEquals('2nd', self.lst[1])


if __name__ == "__main__":
    # import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
