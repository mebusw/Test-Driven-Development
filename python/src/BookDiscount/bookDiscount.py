'''
Created on 2013-7-6

@author: mebusw@gmail.com
'''

PRICE = 8
class BookDiscount(object):


    def __init__(self):
        pass

    def findMinCost(self, books):
        books = list(books)
        books.sort(reverse=True)

        if books[0] < 0:
            raise Exception("can't be negetive")

        cost = 0
        if books[4] >= 1:
            cost = min(
                PRICE*5*(1-0.25)+self.findMinCost([books[0]-1,books[1]-1,books[2]-1,books[3]-1,books[4]-1]),
                PRICE*4*(1-0.20)+self.findMinCost([books[0]-1,books[1]-1,books[2]-1,books[3]-1,books[4]]),
                PRICE*3*(1-0.10)+self.findMinCost([books[0]-1,books[1]-1,books[2]-1,books[3],books[4]]),
                PRICE*2*(1-0.05)+self.findMinCost([books[0]-1,books[1]-1,books[2],books[3],books[4]]),
                )
        elif books[3] >= 1:
            cost = min(
                PRICE*4*(1-0.20)+self.findMinCost([books[0]-1,books[1]-1,books[2]-1,books[3]-1,books[4]]),
                PRICE*3*(1-0.10)+self.findMinCost([books[0]-1,books[1]-1,books[2]-1,books[3],books[4]]),
                PRICE*2*(1-0.05)+self.findMinCost([books[0]-1,books[1]-1,books[2],books[3],books[4]]),
                )
        elif books[2] >= 1:
            cost = min(
                PRICE*3*(1-0.10)+self.findMinCost([books[0]-1,books[1]-1,books[2]-1,books[3],books[4]]),
                PRICE*2*(1-0.05)+self.findMinCost([books[0]-1,books[1]-1,books[2],books[3],books[4]]),
                )
        elif books[1] >= 1:
            cost = PRICE*2*(1-0.05)+self.findMinCost([books[0]-1,books[1]-1,books[2],books[3],books[4]])
        elif books[0] >= 1:
            cost = PRICE*books[0]
        else:
            cost = 0

        return cost