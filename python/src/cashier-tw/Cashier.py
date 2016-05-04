class Product(object):
    def __init__(self, name, price):
        self.name = name
        self.price = price


class Receipt(object):
    def __init__(self):
        self.items = []

    def add(self, *items):
        self.items.extend(items)

    def size(self):
        return len(self.items)

    def total(self):
        return sum(map(lambda item: item.total(), self.items))


class Item(object):
    def __init__(self, product, amount, func=lambda p, a: p * a):
        self.product = product
        self.amount = amount
        self.func = func

    def total(self):
        return self.func(self.product.price, self.amount)
