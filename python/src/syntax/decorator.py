import unittest

resultStack = []

def anna(fn):
    def new_func(*args):
        resultStack.append('by anna args=%s' % str(args))
        return fn(*args)
    return new_func

def annie(param):
    resultStack.append('by annie1 param=%s' % str(param))
    def _A(fn):
        def new_func(*args):
            resultStack.append('by annie2 args=%s' % str(args))
            return fn(*args)
        return new_func
    return _A
    
class Host():
    def __init__(self):
        global resultStack
        
    @anna
    def tom(self, a):
        resultStack.append('by Host.tom a=%s' % str(a))

    @annie('hello')
    def tonny(self, a):
        resultStack.append('by Host.tonny a=%s' % str(a))
    

@anna
def annaHost(a):
    resultStack.append('by annaHost a=%s' % str(a))

@annie('hello')
def annieHost(a):
    resultStack.append('by annieHost a=%s' % str(a))


class TestAnnotation(unittest.TestCase):
    def setUp(self):
        global resultStack
        resultStack = []
    
    def testAnnotationWithoutParam(self):
        annaHost((1, 2))
        self.assertEqual('by anna args=((1, 2),)', resultStack[0])
        self.assertEqual('by annaHost a=(1, 2)', resultStack[1])

    def testAnnotationWithParam(self):
        annieHost((3, 4))
        self.assertEqual('by annie2 args=((3, 4),)', resultStack[0])
        self.assertEqual('by annieHost a=(3, 4)', resultStack[1])
        
    def testAnnotationOfClassWithoutParam(self):
        host = Host()
        host.tom((5, 6))
        self.assertIn('by anna args=', resultStack[0])
        self.assertIn('<syntax.decorator.Host instance at', resultStack[0])
        self.assertIn('(5, 6)', resultStack[0])
        self.assertEqual('by Host.tom a=(5, 6)', resultStack[1])

    def testAnnotationOfClassWithParam(self):
        host = Host()
        host.tonny((7, 8))
        self.assertIn('by annie2 args=', resultStack[0])
        self.assertIn('<syntax.decorator.Host instance at', resultStack[0])
        self.assertIn('(7, 8)', resultStack[0])
        self.assertEqual('by Host.tonny a=(7, 8)', resultStack[1])
        