'''
Created on 2013-8-13

@author: mebusw@gmail.com
'''

from datetime import date


class CatalogApp():
    def __init__(self):
        self.handlers = {}
        self.createHandlers()

    def createHandlers(self):
        self.handlers['new_workshop'] = NewWorkshopHandler(self)
        self.handlers['all_workshops'] = AllWorkshopsHandler(self)

    def executeActionAndGetResponse(self, actionName, *parameters):
        if actionName == 'new_workshop':
            return self.handlers['new_workshop'].execute(parameters)
        elif actionName == 'all_workshops':
            return self.handlers['all_workshops'].execute(parameters)
        else:
            return 3


class Handler:
    def __init__(self, catalogApp):
        self.catalogApp = catalogApp

class NewWorkshopHandler(Handler):
    def execute(self, *parameters):
        ### do sth.
        self.catalogApp.executeActionAndGetResponse('all_workshops', parameters)
        return 1

class AllWorkshopsHandler(Handler):
    def execute(self, *parameters):
        ### do sth. else
        return 2
