'''
Created on 2013-8-10

@author: mebusw@gmail.com
'''

from datetime import date


class Loan():
    def __init__(self, commitment, riskRating, maturity, capitalStrategy=None, expiry=None, outstanding=None):
        self.commitment = commitment
        self.riskRating = riskRating
        self.maturity = maturity
        self.expiry = expiry
        self.outstanding = outstanding
        self.capitalStrategy = capitalStrategy

    @staticmethod
    def createTermLoan(commitment, riskRating, maturity):
        return Loan(commitment, riskRating, maturity, CapitalStrategyTermLoan())

    @staticmethod
    def createRevolver(commitment, riskRating, maturity, expiry):
        return Loan(commitment, riskRating, maturity, CapitalStrategyRevolver(), expiry=expiry)

    @staticmethod
    def createRCTL(commitment, riskRating, maturity, outstanding):
        return Loan(commitment, riskRating, maturity, CapitalStrategyRCTL(), outstanding=outstanding)


    def capital(self):
        return self.capitalStrategy.capital(self)

    def duration(self):
        return self.capitalStrategy.duration(self)




class CapitalStrategy:
    def capital(self, loan):
        if None == loan.expiry and None != loan.maturity:
            return loan.commitment * loan.duration() * self.riskFactor(loan)
        if None != loan.expiry and None == loan.maturity:
            if loan.getUnusedPercentage() != 1.0:
                return loan.commitment * loan.getUnusedPercentage() * loan.duration() * self.riskFactor(loan)
            else:
                return loan.ourstandingRiskAmount() * loan.duration() * self.riskFactor(loan) \
                    + loan.unusedRiskAmount() * loan.duration() * self.unusedRiskFactor(loan)
        return 3.0
    def riskFactor(self, loan):
        return loan.riskRating * 1.0

    def unusedRiskFactor(self, loan):
        return loan.riskRating * 1.0

    def duration(self, loan):
        if None == loan.expiry and None != loan.maturity:
            return self.weightedAverageDuration(loan)
        if None != loan.expiry and None == loan.maturity:
            return self.yearsTo(loan.expiry)
        return 1.0

    def weightedAverageDuration(self, loan):
        duration = 1.0
        weightedAverage = 80
        sumOfPayments = 100
        loan.payments = []

        for payment in loan.payments:
            sumOfPayments += payments.amount
            weightedAverage += self.yearsTo(payment.date) * payment.amount

        if loan.commitment != 0.0:
            duration = weightedAverage * 1.0 / sumOfPayments
        return duration

    def yearsTo(self, endDate, loan):
        beginDate = date(2013, 8, 15)
        return (endDate - beginDate).days / 365

    def ourstandingRiskAmount(self, loan):
        return loan.outstanding

    def unusedRiskAmount(self, loan):
        return loan.commitment - loan.outstanding


class CapitalStrategyTermLoan(CapitalStrategy):
    def capital(self, loan):
        return 120

class CapitalStrategyRevolver(CapitalStrategy):
    def capital(self, loan):
        return 130

class CapitalStrategyRCTL(CapitalStrategy):
    def capital(self, loan):
        return 140
