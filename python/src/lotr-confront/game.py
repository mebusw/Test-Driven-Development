'''
Created on May 20, 2013

@author: 
'''

class Game(object):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''
        pass
    
    def player1_send_spawn(self, spawn):
        self.player1 = spawn
        
    def player2_send_spawn(self, spawn):
        self.player2 = spawn
        
    def resolve(self):       
        if self.player1.preResolve(self.player2):
            return

        if self.player2.preResolve(self.player1):
            return
        
        if self.compareStrenghToResolve():
            return

        return
    
    def compareStrenghToResolve(self):
        if self.player1.adjustStrengh == self.player2.adjustStrengh:
            self.player1.alive = False
            self.player2.alive = False
        elif self.player1.adjustStrengh > self.player2.adjustStrengh:
            self.player2.alive = False
        else:
            self.player1.alive = False
            
        return True
        
class Spawn(object):
    def __init__(self):
        self.name = 'unnamed'   
        self.strengh = 0
        self.adjustStrengh = 0        
        self.alive = True
        
        
    def preResolve(self, opponent):
        ''' @return: True if this resolving is enough to complete.
        '''
        return False
        
class Gandalf(Spawn):
    def __init__(self):
        super(Gandalf, self).__init__()
        self.name = 'Gandalf'
        self.strengh = 5
        self.adjustStrengh = self.strengh  
        
class Golum(Spawn):
    def __init__(self):
        super(Golum, self).__init__()
        self.name = 'Golum'
        self.strengh = 1
        self.adjustStrengh = self.strengh
        
class Aragon(Spawn):
    def __init__(self):
        super(Aragon, self).__init__()
        self.name = 'Aragon'
        self.strengh = 6
        self.adjustStrengh = self.strengh  
        
class Saruman(Spawn):
    def __init__(self):
        super(Saruman, self).__init__()
        self.name = 'Saruman'
        self.strengh = 5
        self.adjustStrengh = self.strengh  

class Lagolas(Spawn):
    def __init__(self):
        super(Lagolas, self).__init__()
        self.name = 'Lagolas'
        self.strengh = 3
        self.adjustStrengh = self.strengh  
        
    def preResolve(self, opponent):
        if isinstance(opponent, Dragon):
            opponent.alive = False
        return True


class Dragon(Spawn):
    def __init__(self):
        super(Dragon, self).__init__()
        self.name = 'Dragon'
        self.strengh = 4
        self.adjustStrengh = self.strengh  

                