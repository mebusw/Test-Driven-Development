import unittest
from datetime import datetime, timedelta


def has_2_wd_between(dt1, dt2):
    it = dt1
    count = 0
    while dt2 > it and count < 2:
        it += timedelta(days=1)
        count += 0 if it.isoweekday() in [6, 7] else 1
    return count >= 2

class Has2WorkingDaysTest(unittest.TestCase):
    def setUp(self):
        pass
        
    def test_too_close_days(self):
        dt1 = datetime(2015, 9, 1)
        dt2 = datetime(2015, 9, 2)
        self.assertFalse(has_2_wd_between(dt1, dt2))

    def test_just_2_working_days(self):
        dt1 = datetime(2015, 9, 1)
        dt2 = datetime(2015, 9, 3)
        self.assertTrue(has_2_wd_between(dt1, dt2))

    def test_more_than_2_working_days(self):
        dt1 = datetime(2015, 9, 1)
        dt2 = datetime(2015, 9, 4)
        self.assertTrue(has_2_wd_between(dt1, dt2))

    def test_weekend_as_2_non_working_days(self):
        dt1 = datetime(2015, 9, 4)
        dt2 = datetime(2015, 9, 6)
        self.assertFalse(has_2_wd_between(dt1, dt2))

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
