class TestCase:
    def __init__(self, name):
        self.name = name
        self.log = ''
        
    def run(self, result):
        result.testStarted()

        try:
            self.setUp()
            method = getattr(self, self.name)
            method()
        except:
            result.testFailed()
        finally:
            self.tearDown()
        return result
        
class WasRun(TestCase):
    def __init__(self, name):
        self.wasRun = None
        TestCase.__init__(self, name)

    def setUp(self):
        self.log += "setUp "

    def tearDown(self):
        self.log += "tearDown "
        
    def testMethod(self):
        self.wasRun = 1
        self.log += 'testMethod '

    def testBrokenMethod(self):
        self.log += 'testBrokenMethod '
        raise Exception

    

class TestResult:
    def __init__(self):
        self.runCount = 0
        self.failureCount = 0

    def testStarted(self):
        self.runCount += 1

    def testFailed(self):
        self.failureCount += 1
        
    def summary(self):
        return '%d run, %d failed' % (self.runCount, self.failureCount)

class TestSuite:
    def __init__(self):
        self.tests = []

    def add(self, test):
        self.tests.append(test)

    def run(self, result):
        for t in self.tests:
            t.run(result)
        return result
    
