'''
Created on 2013-9-23

@author: mebusw@gmail.com
'''
import unittest
from mock import MagicMock, Mock
import time, math
from datetime import date

class SingleEliminationTournamentsTest(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testGenerateSingleEleminationWithTwoTeam(self):
        tournament = SingleEliminationTournament()
        tournament.addTeam('Tianjin')
        tournament.addTeam('Beijing')

        tournament.generate()

        self.assertEqual(1, tournament.calcRoundCount())
        self.assertEqual(1, len(tournament.matches))
        self.assertEqual('Tianjin - Beijing', str(tournament.matches[0]))


    def testGenerateSingleEleminationWithFourTeam(self):
        tournament = SingleEliminationTournament()
        tournament.addTeam('Tianjin')
        tournament.addTeam('Beijing')
        tournament.addTeam('Shanghai')
        tournament.addTeam('Chengdu')

        tournament.generate()

        self.assertEqual(2, tournament.calcRoundCount())
        self.assertEqual(3, len(tournament.matches))
        self.assertEqual('Tianjin - Chengdu', str(tournament.matches[0]))
        self.assertEqual('Beijing - Shanghai', str(tournament.matches[1]))
        self.assertEqual('? - ?', str(tournament.matches[2]))

    def testGenerateSingleEleminationWithSixTeam(self):
        tournament = SingleEliminationTournament()
        tournament.addTeam('Tianjin')
        tournament.addTeam('Beijing')
        tournament.addTeam('Shanghai')
        tournament.addTeam('Chengdu')
        tournament.addTeam('Chongqing')
        tournament.addTeam('Guangzhou')

        tournament.generate()

        self.assertEqual(3, tournament.calcRoundCount())
        self.assertEqual(5, len(tournament.matches))
        self.assertEqual('Tianjin - Guangzhou', str(tournament.matches[0]))
        self.assertEqual('Beijing - Chongqing', str(tournament.matches[1]))
        self.assertEqual('Shanghai - Chengdu', str(tournament.matches[2]))
        self.assertEqual('? - ?', str(tournament.matches[3]))
        self.assertEqual('? - ?', str(tournament.matches[4]))


        expected = '? - ?''? - ?''Tianjin - Guangzhou''Beijing - Chongqing''Shanghai - Chengdu'
        self.assertEqual(expected, tournament.visit())


    def testVisitTournamentbyLevel(self):
        tournament = SingleEliminationTournament()

        tournament.final = Match('?', "?")
        tournament.final.linkTo(Match('Tianjin', 'Chengdu'), Match('Beijing', 'Shanghai'))
        expected = '? - ?''Tianjin - Chengdu''Beijing - Shanghai'

        self.assertEqual(expected, tournament.visit())

class SingleRoundRobinTournamentsTest(unittest.TestCase):
    def setUp(self):
        self.tournament = SingleRoundRobinTournament('Zhongchao')

    def testGenerateTournamentWithTwoTeams(self):
        self._addTeams('Tianjin', 'Beijing')

        self.tournament.generate()

        self.assertEqual(1, self.tournament.calcRoundCount())
        self._assertOneMatch('Tianjin - Beijing', 0)


    def testGenerateTournamentWithFourTeams(self):
        self._addTeams('Tianjin', 'Beijing', 'Shanghai', 'Chengdu')

        self.tournament.generate()

        self.assertEqual(3, self.tournament.calcRoundCount())
        self._assertOneMatch('Tianjin - Chengdu', 0)
        self._assertOneMatch('Beijing - Shanghai', 1)
        self._assertOneMatch('Beijing - Chengdu', 2)
        self._assertOneMatch('Shanghai - Tianjin', 3)
        self._assertOneMatch('Shanghai - Chengdu', 4)
        self._assertOneMatch('Tianjin - Beijing', 5)

    def testGenerateTournamentWithThreeTeams(self):
        self._addTeams('Tianjin', 'Beijing', 'Shanghai')

        self.tournament.generate()

        self.assertEqual(2, self.tournament.calcRoundCount())
        self._assertOneMatch('Tianjin - Shanghai', 0)
        self._assertOneMatch('Beijing - Shanghai', 1)
        self._assertOneMatch('Tianjin - Beijing', 2)


    def _addTeams(self, *teams):
        for t in teams:
            self.tournament.addTeam(t)

    def _assertOneMatch(self, expected, matchNbr):
        self.assertEqual(expected, str(self.tournament.matches[matchNbr]))



    def testLeftShiftingList(self):
        tournament = SingleRoundRobinTournament('Zhongchao')
        l = range(5)

        l = tournament.lshift(l, 1)

        self.assertEqual([1, 2, 3, 4, 0], l)       

    def testRightShiftingList(self):
        tournament = SingleRoundRobinTournament('Zhongchao')
        l = range(5)

        l = tournament.lshift(l, -2)

        self.assertEqual([3, 4, 0, 1, 2], l)       

class SingleRoundRobinTournament:
    def __init__(self, name):
        self.name = name
        self.teams = []

    def addTeam(self, team):
        self.teams.append(team)

    def lshift(self, ori, n):
        n = n % len(ori)
        a = ori[n:]
        a.extend(ori[:n])
        return a

    def calcRoundCount(self):
        return len(self.teams) - 1

    def generate(self):
        """ rotate with fixing the team #N """
        self.matches = []
        N = len(self.teams)
        teamsWithoutLast = self.teams[:-1]

        for rnd in xrange(self.calcRoundCount()):
            l = self.lshift(teamsWithoutLast, rnd)
            l.append(self.teams[N - 1])
            # print 'round', rnd, l
            for match in xrange(N / 2):
                self.matches.append(Match(l[match], l[N - 1 - match]))

        # an extra round when odd number of teams
        l = teamsWithoutLast
        for match in xrange(N / 2):
            self.matches.append(Match(l[match], l[N - 2 - match]))

class SingleEliminationTournament:
    def __init__(self):
        self.teams = []

    def addTeam(self, team):
        self.teams.append(team)

    def generate(self, bronzeMatch=False):
        """Builder"""
        """Strategy"""
        self.matches = []
        self._generateForFirstRound()

        matchesInLastRound = self.matches
        n = len(matchesInLastRound)
        while (n > 1):
            byeMatch = matchesInLastRound[n - 1] if n % 2 != 0 else None
            upperMatches = []
            for i in xrange(n / 2):
                nextMatch = Match('?', '?')
                nextMatch.linkTo(matchesInLastRound[i * 2], matchesInLastRound[i * 2 + 1])
                upperMatches.append(nextMatch)

            self.matches.extend(upperMatches)
            matchesInLastRound = upperMatches
            if byeMatch is not None:
                matchesInLastRound.append(byeMatch) 
            n = len(matchesInLastRound)
        self.final = matchesInLastRound[0]

    def calcRoundCount(self):
        self.rounds = math.ceil(math.log(len(self.teams), 2))
        return self.rounds

    def _generateForFirstRound(self):
        N = len(self.teams)
        for m in xrange(N / 2):
            self.matches.append(Match(self.teams[m], self.teams[N - 1 - m]))


    def visit(self):
        return self.final.visit()


class Match:
    def __init__(self, team1, team2):
        self.team1 = team1
        self.team2 = team2
        self.id = None
        self.preceedings = []


    def __str__(self):
        return '%s - %s' % (self.team1, self.team2)

    def visit(self):
        s = str(self)
        for prec in self.preceedings:
            s += prec.visit()
        return s

    def linkTo(self, match1, match2):
        self.preceedings = [match1, match2]

class Team:
    pass

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()     