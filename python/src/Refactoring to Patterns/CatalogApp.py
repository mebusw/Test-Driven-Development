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
        return self.lookupHandlerBy(actionName).execute(parameters)
        
    def lookupHandlerBy(self, handlerName):
        return self.handlers[handlerName]

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
