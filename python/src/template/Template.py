'''
Created on 2013-7-27

@author: mebusw@gmail.com

@ref "Practical TDD and Acceptance TDD for Java Developers" by Lasse Koskela
'''

import re

class MissingValueException(Exception):
    pass

class Template(object):
    def __init__(self, templateText):
        self.templateText = templateText
        self.variables = {}

    def set(self, variable, value):
        self.variables[variable] = value

    def evaluate(self):
        parser = TemplateParser()
        segments = parser.parseSegments(self.templateText)
        return self._concatenate(segments)

    def _concatenate(self, segments):
        self.result = ''
        for seg in segments:
            self.result += seg.evaluate(self.variables)
        return self.result


    @staticmethod
    def isVariable(seg):
        return seg.startswith('${') and seg.endswith('}')

    def _evaluateVariable(self, seg):
        k = seg[2:-1]
        if not k in self.variables:
            raise MissingValueException('No value for %s' % seg)
        self.result += self.variables[k]
    

class TemplateParser(object):
    def parse(self, templateText):
        segments = []
        index = self._collectSegments(segments, templateText)
        self._addTail(segments, templateText, index)
        self._addEmptyStringIfTemplateWasEmpty(segments)
        return tuple(segments)

    def parseSegments(self, templateText):
        segments = []
        strings = self.parse(templateText)
        for s in strings:
            if Template.isVariable(s):
                segments.append(Variable(s[2:-1]))
            else:
                segments.append(PlainText(s))
        return segments

    def _collectSegments(self, segments, src):
        index = 0
        for matchObject in re.finditer('\$\{[^}]*\}', src):
            self._addPrecedingPlainText(segments, src, matchObject, index)
            self._addVariable(segments, src, matchObject)
            index = matchObject.end()
        return index

    def _addPrecedingPlainText(self, segments, src, matchObject, index):
        if index != matchObject.start():
            segments.append(src[index:matchObject.start()])

    def _addVariable(self, segments, src, matchObject):
        segments.append(src[matchObject.start():matchObject.end()])

    def _addTail(self, segments, src, index):
        if index < len(src):
            segments.append(src[index:])

    def _addEmptyStringIfTemplateWasEmpty(self, segments):
        if len(segments) == 0:
            segments.append('')

class Segment(object):
    def __ne__(self, other):
        return not self.__eq__(other)


class PlainText(Segment):
    def __init__(self, text):
        self.text = text

    def __eq__(self, other):
        return self.text == other.text

    def __str__(self):
        return 'PlainText: ' + self.text

    def evaluate(self, variables):
        return self.text

class Variable(Segment):
    def __init__(self, name):
        self.name = name    

    def __eq__(self, other):
        return self.name == other.name

    def __str__(self):
        return 'Variable: ' + self.name

    def evaluate(self, variables):
        if self.name not in variables:
            raise MissingValueException('No value for ${%s}' % self.name)
        return variables[self.name]
