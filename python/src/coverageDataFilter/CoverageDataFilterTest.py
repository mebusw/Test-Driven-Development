'''
Created on Feb 26, 2013

@author: 1411494
'''
import unittest

rawData = '''
TN:
SF:app/common/app.js
FN:6,(anonymous_1)
FN:9,(anonymous_2)
FN:14,(anonymous_3)
FN:17,(anonymous_4)
FN:24,(anonymous_5)
FN:29,(anonymous_6)
FN:32,(anonymous_7)
FN:35,(anonymous_8)
FN:45,(anonymous_9)
FN:52,(anonymous_10)
FN:57,(anonymous_11)
FN:62,(anonymous_12)
FNF:12
FNH:3
FNDA:1,(anonymous_1)
FNDA:0,(anonymous_2)
FNDA:1,(anonymous_3)
FNDA:0,(anonymous_4)
FNDA:1,(anonymous_5)
FNDA:0,(anonymous_6)
FNDA:0,(anonymous_7)
FNDA:0,(anonymous_8)
FNDA:0,(anonymous_9)
FNDA:0,(anonymous_10)
FNDA:0,(anonymous_11)
FNDA:0,(anonymous_12)
DA:3,1
DA:7,1
DA:10,0
DA:11,0
DA:12,0
DA:15,1
DA:18,0
DA:22,1
DA:29,1
DA:30,0
DA:31,0
DA:32,0
DA:35,0
DA:36,0
DA:38,0
DA:45,1
DA:46,0
DA:47,0
DA:48,0
DA:52,1
DA:53,0
DA:54,0
DA:57,1
DA:58,0
DA:59,0
DA:62,1
DA:63,0
DA:64,0
DA:65,0
DA:66,0
LF:30
LH:9
BRDA:31,1,0,0
BRDA:31,1,1,0
BRDA:46,2,0,0
BRDA:46,2,1,0
BRDA:64,3,0,0
BRDA:64,3,1,0
BRF:6
BRH:0
end_of_record
TN:
SF:app/common/core/application/applicationController.js
FN:14,(anonymous_1)
FNF:1
FNH:1
FNDA:1,(anonymous_1)
DA:11,1
DA:16,1
LF:2
LH:2
BRF:0
BRH:0
end_of_record
TN:
SF:app/common/core/application/applicationView.js
FNF:0
FNH:0
DA:11,1
LF:1
LH:1
BRF:0
BRH:0
end_of_record'''

### Test List
# 1. only SF & DA will be filtered out
# 2. SF: filename need to be absolute path
# 3. write to new file with fixed name
class Test(unittest.TestCase):


    def setUp(self):
        pass


    def tearDown(self):
        pass


    def testName(self):
        pass


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()