'''
Created on 2014-6-17

@author: mebusw@gmail.com
'''

class SystemPermission:
	REQUESTED = "REQUESTED"
	CLAIMED = "CLAIMED"
	GRANTED = "GRANTED"

	def __init__(self):
		self.state = SystemPermission.REQUESTED
		self.admin = None
		self.isGranted = False


	def claimedBy(self):
		if self.state != SystemPermission.REQUESTED:
			return
		self.willBeHandledBy(self.admin)
		self.state = SystemPermission.CLAIMED

	def grantedBy(self):
		if self.state != SystemPermission.CLAIMED:
			return
		self.state = SystemPermission.GRANTED
		self.isGranted = True

	def deniedBy(self):
		pass

	def willBeHandledBy(self, admin):
		pass