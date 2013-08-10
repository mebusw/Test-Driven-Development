'''
Created on 2013-8-10

@author: mebusw@gmail.com
'''

from datetime import date


class Loan():
    def __init__(self, commitment, riskRating, maturity, expiry=None, outstanding=None):
        self.commitment = commitment
        self.riskRating = riskRating
        self.maturity = maturity
        self.expiry = expiry
        self.outstanding = outstanding

    def capital(self):
        if None == self.expiry and None != self.maturity:
            return self.commitment * self.duration() * self.riskFactor()
        if None != self.expiry and None == self.maturity:
            if self.getUnusedPercentage() != 1.0:
                return self.commitment * self.getUnusedPercentage() * self.duration() * self.riskFactor()
            else:
                return self.ourstandingRiskAmount() * self.duration() * self.riskFactor() \
                    + self.unusedRiskAmount() * self.duration() * self.unusedRiskFactor()
        return 0.0

    def ourstandingRiskAmount(self):
        return self.outstanding

    def unusedRiskAmount(self):
        return self.commitment - self.outstanding

    def duration(self):
        if None == self.expiry and None != self.maturity:
            return self.weightedAverageDuration()
        if None != self.expiry and None == self.maturity:
            return self.yearsTo(self.expiry)
        return 0.0

    def weightedAverageDuration(self):
        duration = 1.0
        weightedAverage = 80
        sumOfPayments = 100
        self.payments = []

        for payment in self.payments:
            sumOfPayments += payments.amount
            weightedAverage += self.yearsTo(payment.date) * payment.amount

        if self.commitment != 0.0:
            duration = weightedAverage / sumOfPayments
        return duration

    def yearsTo(self, endDate):
        beginDate = date(2013, 8, 15)
        return (endDate - beginDate).days / 365

    def riskFactor(self):
        return self.riskRating * 1.0

    def unusedRiskFactor(self):
        return self.riskRating * 1.0
