'''
Created on 2013-8-13

@author: mebusw@gmail.com
'''

from datetime import date


class CatalogApp():
    def __init__(self):
        pass

    def executeActionAndGetResponse(self, actionName, *parameters):
        if actionName == 'new_workshop':
            ### do sth.
            self.executeActionAndGetResponse('all_workshops', parameters)
            return 1
        elif actionName == 'all_workshops':
            ### do sth. else
            return 2
        else:
            return 3
