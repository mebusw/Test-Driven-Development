"""
To try and encourage more sales of the 5 different Harry
Potter books they sell, a bookshop has decided to offer 
discounts of multiple-book purchases. 

One copy of any of the five books costs 8 EUR. 

If, however, you buy two different books, you get a 5% 
discount on those two books.

If you buy 3 different books, you get a 10% discount. 

If you buy 4 different books, you get a 20% discount.

If you go the whole hog, and buy all 5, you get a huge 25%
discount. 

Note that if you buy, say, four books, of which 3 are 
different titles, you get a 10% discount on the 3 that 
form part of a set, but the fourth book still costs 8 EUR. 

Your mission is to write a piece of code to calculate the 
price of any conceivable shopping basket (containing only 
Harry Potter books), giving as big a discount as possible.

For example, how much does this basket of books cost?

2 copies of the first book
2 copies of the second book
2 copies of the third book
1 copy of the fourth book
1 copy of the fifth book

One way of group these 8 books is:
 1 group of 5 --> 25% discount (1st,2nd,3rd,4th,5th)
+1 group of 3 --> 10% discount (1st,2nd,3rd)
This would give a total of
 5 books at a 25% discount
+3 books at a 10% discount
Giving
 5 x (8 - 2.00) == 5 x 6.00 == 30.00
+3 x (8 - 0.80) == 3 x 7.20 == 21.60
For a total of 51.60

However, a different way to group these 8 books is:
 1 group of 4 books --> 20% discount  (1st,2nd,3rd,4th)
+1 group of 4 books --> 20% discount  (1st,2nd,3rd,5th)
This would give a total of
 4 books at a 20% discount
+4 books at a 20% discount
Giving
 4 x (8-1.60) == 4 x 6.40 == 25.60
+4 x (8-1.60) == 4 x 6.40 == 25.60
For a total of 51.20

And 51.20 is the price with the biggest discount.
"""
import unittest

PRICE = 8


def most_cart_strategy(books):
    cart1 = pick_as_much_as_possible(books)
    cart2 = pick_as_much_as_possible(books)
    return len(cart1) * PRICE * (1 - 0.25) + len(cart2) * PRICE * (1 - 0.1)

def pick_as_much_as_possible(books):
    ret = []
    for book in books:
        if book[1] > 0:
            book[1] -= 1
            ret.append(book[0])
    # print ret
    return ret


def smart_cart_strategy(books):
    sorted_books = sort_books_by_amount(books)
    print sorted_books
    cart1 = pick_least_four(sorted_books)
    cart2 = pick_least_four(sorted_books)
    return len(cart1) * PRICE * (1 - 0.2) + len(cart2) * PRICE * (1 - 0.2)


def pick_least_four(books):
    ret = []
    index = 0
    for i in xrange(4):
        book = books[index]
        while book[1] < 1:
            index += 1
            book = books[index]
        book[1] -= 1
        ret.append(book)
            
    return ret

def sort_books_by_amount(books):
    return sorted(books, key=lambda x: x[1], reverse=True)

class ATest(unittest.TestCase):
    def test_smart_cart_strategy(self):
        BOOKS = (['A', 2], ['B', 2], ['C', 2], ['D', 1], ['E', 1])
        self.assertEquals(51.2, smart_cart_strategy(BOOKS))

    def test_most_cart_strategy(self):
        BOOKS = (['A', 2], ['B', 2], ['C', 2], ['D', 1], ['E', 1])
        self.assertEquals(51.60, most_cart_strategy(BOOKS))

if __name__ == '__main__':
    unittest.main()
