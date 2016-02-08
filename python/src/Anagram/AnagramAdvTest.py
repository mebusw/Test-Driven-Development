"""
Write a program to generate all potential 
anagrams of an input string.

For example, the potential anagrams of "biro" are

biro bior brio broi boir bori
ibro ibor irbo irob iobr iorb
rbio rboi ribo riob roib robi
obir obri oibr oirb orbi orib
"""
import unittest


def anagram(chars):
    if len(chars) == 1:
        yield chars[0]
        return

    for c in chars:
        others = list(chars)
        others.remove(c)
        for a in anagram(others):
            yield c + a


class AnagramAdvTest(unittest.TestCase):
    def setUp(self):
        pass

    def test(self):
        self.assertEquals(['b'], list(anagram('b')))
        self.assertEquals(['i'], list(anagram('i')))
        self.assertEquals(['bi', 'ib'], list(anagram('bi')))
        self.assertEquals(['bir', 'bri', 'ibr', 'irb', 'rbi', 'rib'], list(anagram('bir')))

        FOUR = """biro bior brio broi boir bori
        ibro ibor irbo irob iobr iorb
        rbio rboi ribo riob robi roib
        obir obri oibr oirb orbi orib
        """
        self.assertEquals(FOUR.split(), list(anagram('biro')))

if __name__ == "__main__":
    # import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
