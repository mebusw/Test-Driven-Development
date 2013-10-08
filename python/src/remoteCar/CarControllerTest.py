'''
Created on 2013-7-27

@author: mebusw@gmail.com
'''
import unittest
from mock import MagicMock, Mock

from CarController import *
from CarInterface import *


class CarControllerTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testItCanGetReadyTheCar(self):
        '''Dummy
        '''
        carController = CarController()

        engine = Engine()
        gearBox = GearBox()
        electronics = Electronics()

        dummyLights = MagicMock('Lights')

        self.assertTrue(carController.getReadyToGo(engine, gearBox, electronics, dummyLights))

    def testItCanAccelerate(self):
        '''Stub with Mock
        '''
        carController = CarController()     
        mockElectronics = Mock('Electronics')
        mockElectronics.accelerate = Mock()
        stubStatusPanel = Mock('StatusPanel')
        stubStatusPanel.thereIsEnoughFuel = Mock(return_value=True)
        stubStatusPanel.engineIsRunning = Mock(return_value=True)

        carController.goForward(mockElectronics, stubStatusPanel)

        stubStatusPanel.thereIsEnoughFuel.assert_called_with()
        stubStatusPanel.engineIsRunning.assert_called_with()
        mockElectronics.accelerate.assert_called_once_with()

    def testItCanStop(self):
        '''Spy
        '''
        class SpyingElectronics(Electronics):
            def pushBrakes(self, brakingPower):
                self.brakingPower = brakingPower
            def getBrakingPower(self):
                return self.brakingPower

        class SpyingStatusPanel(StatusPanel):
            def __init__(self):
                self.speedWasRequested = False
                self.currentSpeed = 1             
            def getSpeed(self):
                if self.speedWasRequested:
                    self.currentSpeed = 0
                self.speedWasRequested = True
                return self.currentSpeed
            def hasSpeedRequested(self):
                return self.speedWasRequested
            def spyOnSpeed(self):
                return self.currentSpeed

        halfBrakingPower = 50
        spyingElectronics = SpyingElectronics()
        spyingStatusPanel = SpyingStatusPanel()
        carController = CarController()
        
        carController.stop(halfBrakingPower, spyingElectronics, spyingStatusPanel)

        self.assertEquals(halfBrakingPower, spyingElectronics.getBrakingPower())
        self.assertTrue(spyingStatusPanel.hasSpeedRequested())
        self.assertEquals(0, spyingStatusPanel.spyOnSpeed(), 
            'Expected speed to be zero after stopping but it actually was %d' % spyingStatusPanel.spyOnSpeed())

    def testItCanStop_2(self):
        '''Spy with Mock
        '''
        halfBrakingPower = 50
        carController = CarController()
        mockElectronics = Mock('Electronics')
        mockElectronics.pushBrakes = Mock()
        mockStatusPanel = Mock('StatusPanel')
        returns = [1, 0, Exception()]
        def side_effect(*args):
            result = returns.pop(0)
            if isinstance(result, Exception):
                raise result
            return result
        mockStatusPanel.getSpeed = Mock(side_effect=side_effect)
        
        carController.stop(halfBrakingPower, mockElectronics, mockStatusPanel)

        mockElectronics.pushBrakes.assert_called_with(halfBrakingPower)
        self.assertEquals(2, mockStatusPanel.getSpeed.call_count)


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()     