'''
Created on 2012-9-17

'''
import unittest
from xml.etree import ElementTree

class TestParsingXML(unittest.TestCase):


    def setUp(self):
        self.xml = '''<?xml version="1.0" encoding="utf-8"?>
            <root>
             <person age="3">
                <name>mebusw</name>
                <sex>man</sex>
             </person>
             <person age="5" des="hello">
                <name>icewe</name>
                <sex>female</sex>
             </person>
            </root>'''
        self.root = ElementTree.fromstring(self.xml)
    
    def tearDown(self):
        pass

    def testNodeByIterator(self):
        nodes = self.root.getiterator("person")
        self.assertEqual(2, len(nodes))
        self.assertEqual('3', nodes[0].attrib['age'])
        self.assertEqual('person', nodes[0].tag)
        self.assertEqual('5', nodes[1].attrib['age'])
        self.assertEqual('person', nodes[1].tag)  
                
    def testNodeByFind(self):
        self.assertEqual('3', self.root.find('person').attrib['age'])
        
    def testNodeByGetChildren(self):
        self.assertEqual('name', self.root.find('person').getchildren()[0].tag)
        self.assertEqual('mebusw', self.root.find('person').getchildren()[0].text)
        
    def testNodeByFindAll(self):
        self.assertEqual(2, len(self.root.findall('person/name')))

 
        
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
