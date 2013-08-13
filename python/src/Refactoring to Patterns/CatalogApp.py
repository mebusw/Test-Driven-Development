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
            return NewWorkshopHandler(self).getNewWorkshopResponse(parameters)
        elif actionName == 'all_workshops':
            return AllWorkshopsHandler(self).getAllWorkshopsResponse(parameters)
        else:
            return 3



class NewWorkshopHandler:
    def __init__(self, catalogApp):
        self.catalogApp = catalogApp

    def getNewWorkshopResponse(self, *parameters):
        ### do sth.
        self.catalogApp.executeActionAndGetResponse('all_workshops', parameters)
        return 1

class AllWorkshopsHandler:
    def __init__(self, catalogApp):
        self.catalogApp = catalogApp

    def getAllWorkshopsResponse(self, *parameters):
        ### do sth. else
        return 2
