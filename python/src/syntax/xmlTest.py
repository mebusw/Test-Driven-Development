#!/usr/bin/python
#-*- coding: utf-8 -*-

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

class TestGB2312Xml(unittest.TestCase):

    def setUp(self):
        self.xml = '''<?xml version="1.0" encoding="utf-8"?>
            <business_trans>
                <response_type>consumption_response</response_type>
                <req_seq>1</req_seq>
                <sys_seq>1234</sys_seq>
                <trans_time>20070405010104</trans_time>
                <result>
                    <id>0000</id>
                    <code>6846844</code>
                    <comment>成功</comment>
                </result>
            </business_trans>'''
        self.root = ElementTree.fromstring(self.xml)

    def testSimple(self):
        self.assertEqual('6846844', self.root.find('result/code').text)
        self.assertEqual(u'成功', self.root.find('result/comment').text)
        
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
