'''
Created on 2013-7-27

@author: mebusw@gmail.com
'''

class CarController(object):
    def getReadyToGo(self, engine, gearbox, electronics, lights):
        # engine.start()
        # gearbox.shift('N')
        # electronics.turnOn(lights)
        return True

    def goForward(self, electronics, statusPanel):
        if statusPanel.thereIsEnoughFuel() and statusPanel.engineIsRunning():
            electronics.accelerate()

    def stop(self, brakingPower, electronics, statusPanel):
        electronics.pushBrakes(brakingPower)
        if statusPanel.getSpeed() > 0:
            self.stop(brakingPower, electronics, statusPanel)
