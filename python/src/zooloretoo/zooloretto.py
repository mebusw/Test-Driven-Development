import unittest
from mock import mock


class Truck(object):
    pass


class Field(object):
    MAX_STALL = 1
    MAX_TILES = 0

    def __init__(self):
        self.tiles = []

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
    def __init__(self):
        self.animals = []

    def put(self, *animals):
        self.animals.extend(animals)

    def score(self):
        return len(set(map(lambda a: type(a), self.animals))) * -3


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

    def put_animal_to_field(self, animal, field_id):
        return self.fields[field_id].put(animal)

    def can_put_animal_to_field(self, animal, field_id):
        return self.fields[field_id].can_put(animal)


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
        score = self.player.put_animal_to_field(Elephant('F'), 0)
        score = self.player.put_animal_to_field(Elephant('F'), 0)

        self.assertEquals(0, score)

    def test_player_get_high_score_when_one_missing(self):
        score = self.player.put_animal_to_field(Elephant('F'), 0)
        score = self.player.put_animal_to_field(Elephant('F'), 0)
        score = self.player.put_animal_to_field(Elephant('F'), 0)

        self.assertEquals(self.player.fields[0].LOW_SCORE, score)

    def test_player_get_high_score_when_more_than_two_missing(self):
        score = self.player.put_animal_to_field(Elephant('F'), 0)
        score = self.player.put_animal_to_field(Elephant('F'), 0)
        score = self.player.put_animal_to_field(Elephant('F'), 0)
        score = self.player.put_animal_to_field(Elephant('F'), 0)

        self.assertEquals(self.player.fields[0].HIGH_SCORE, score)

    def test_can_put_an_animal_when_empty(self):
        self.assertTrue(self.player.can_put_animal_to_field(Elephant('F'), 0))

    def test_can_put_an_animal_when_same_animal(self):
        score = self.player.put_animal_to_field(Elephant('F'), 0)

        self.assertTrue(self.player.can_put_animal_to_field(Elephant('F'), 0))

    def test_can_not_put_an_animal_when_different_animal(self):
        score = self.player.put_animal_to_field(Elephant('F'), 0)

        self.assertFalse(self.player.can_put_animal_to_field(Zebra('F'), 0))

    def test_new_born(self):
        pass


class FinalScoringTest(unittest.TestCase):
    def setUp(self):
        self.barn = Barn()

    def test_no_animal_in_barn(self):
        self.barn.put()

        self.assertEqual(0, self.barn.score())

    def test_one_animal_in_barn(self):
        self.barn.put(Elephant('F'))

        self.assertEqual(-3, self.barn.score())

    def test_two_diff_animal_in_barn(self):
        self.barn.put(Elephant('F'), Zebra('M'))

        self.assertEqual(-6, self.barn.score())

    def test_one_type_of_animal_in_barn(self):
        self.barn.put(Elephant('F'))
        self.barn.put(Elephant('M'))

        self.assertEqual(-3, self.barn.score())


if __name__ == '__main__':
    unittest.main()
