import unittest

from datetime import datetime


def should_charge(park_time, start_hour, end_hour):
    if end_hour < start_hour:
        return park_time.hour < end_hour or park_time.hour >= start_hour

    return start_hour <= park_time.hour < end_hour


class TimePeriodTest(unittest.TestCase):
    def test_park_in_middle_of_period(self):
        park_time = datetime(2016, 4, 24, hour=3)
        self.assertTrue(should_charge(park_time, start_hour=2, end_hour=5))

    def test_park_at_start_of_period(self):
        park_time = datetime(2016, 4, 24, hour=2)
        self.assertTrue(should_charge(park_time, start_hour=2, end_hour=5))

    def test_park_at_end_of_period(self):
        park_time = datetime(2016, 4, 24, hour=5)
        self.assertFalse(should_charge(park_time, start_hour=2, end_hour=5))

    def test_park_before_start_of_period(self):
        park_time = datetime(2016, 4, 24, hour=1)
        self.assertFalse(should_charge(park_time, start_hour=2, end_hour=5))

    def test_park_after_end_of_period(self):
        park_time = datetime(2016, 4, 24, hour=6)
        self.assertFalse(should_charge(park_time, start_hour=2, end_hour=5))

    ### cross-day
    def test_cross_day_park_after_end_of_period(self):
        park_time = datetime(2016, 4, 24, hour=6)
        self.assertFalse(should_charge(park_time, start_hour=20, end_hour=3))

    def test_cross_day_park_at_end_of_period(self):
        park_time = datetime(2016, 4, 24, hour=3)
        self.assertFalse(should_charge(park_time, start_hour=20, end_hour=3))

    def test_cross_day_park_in_period_of_tomorrow(self):
        park_time = datetime(2016, 4, 24, hour=2)
        self.assertTrue(should_charge(park_time, start_hour=20, end_hour=3))

    def test_cross_day_park_in_period_of_yesterday(self):
        park_time = datetime(2016, 4, 24, hour=22)
        self.assertTrue(should_charge(park_time, start_hour=20, end_hour=3))

    def test_cross_day_park_at_start_of_period_of_yesterday(self):
        park_time = datetime(2016, 4, 24, hour=20)
        self.assertTrue(should_charge(park_time, start_hour=20, end_hour=3))


if __name__ == "__main__":
    # import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
