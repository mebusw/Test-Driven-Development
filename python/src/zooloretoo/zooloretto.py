import unittest
from mock import mock


class Truck(object):
    pass


class Field(object):
    tiles = []
    MAX_STALL = 1
    MAX_TILES = 0

    def put(self, tile):
        self.tiles.append(tile)
        return self.score()

    def score(self):
        if len(self.tiles) == self.MAX_TILES:
            return self.HIGH_SCORE
        elif len(self.tiles) == self.MAX_TILES - 1:
            return self.LOW_SCORE
        else:
            return 0

    def can_put(self, animal):
        # print type(animal)
        return len(self.tiles) == 0 or type(animal) == type(self.tiles[0])


class FieldFour(Field):
    MAX_TILES = 4
    HIGH_SCORE = 5
    LOW_SCORE = 4


class FieldFive(Field):
    MAX_TILES = 5
    HIGH_SCORE = 8
    LOW_SCORE = 5


class FieldSix(Field):
    MAX_TILES = 6
    HIGH_SCORE = 10
    LOW_SCORE = 6


class Barn(object):
    animals = []


class Animal(object):
    gender = ''


class Elephant(Animal):
    def __init__(self, gender='N'):
        self.gender = gender


class Zebra(Animal):
    def __init__(self, gender='N'):
        self.gender = gender


class Player(object):
    def __init__(self, name):
        self.name = name
        self.fields = [FieldFour(), FieldFive(), FieldSix()]
        self.barn = Barn()


class Test(unittest.TestCase):
    def setUp(self):
        self.player = Player('Tom')
        self.player.fields[0].tiles = []

    def tearDown(self):
        pass

    def test_init(self):
        self.assertEquals(3, len(self.player.fields))
        # print dir(self)
        self.assertIsNotNone(self.player.barn)

    def test_player_get_high_score_when_full(self):
        score = self.player.fields[0].put(Elephant('F'))
        score = self.player.fields[0].put(Elephant('F'))

        self.assertEquals(0, score)

    def test_player_get_high_score_when_one_missing(self):
        score = self.player.fields[0].put(Elephant('F'))
        score = self.player.fields[0].put(Elephant('F'))
        score = self.player.fields[0].put(Elephant('F'))

        self.assertEquals(self.player.fields[0].LOW_SCORE, score)

    def test_player_get_high_score_when_more_than_two_missing(self):
        score = self.player.fields[0].put(Elephant('F'))
        score = self.player.fields[0].put(Elephant('F'))
        score = self.player.fields[0].put(Elephant('F'))
        score = self.player.fields[0].put(Elephant('F'))

        self.assertEquals(self.player.fields[0].HIGH_SCORE, score)

    def test_can_put_an_animal_when_empty(self):
        self.assertTrue(self.player.fields[0].can_put(Elephant('F')))

    def test_can_put_an_animal_when_same_animal(self):
        self.player.fields[0].put(Elephant('F'))

        self.assertTrue(self.player.fields[0].can_put(Elephant('F')))

    def test_can_not_put_an_animal_when_different_animal(self):
        self.player.fields[0].put(Elephant('F'))

        self.assertFalse(self.player.fields[0].can_put(Zebra('F')))

    def test_new_born(self):
        pass


if __name__ == '__main__':
    unittest.main()
