'''
Created on 2014-6-17

@author: mebusw@gmail.com
'''


class PermissionState:
	def __init__(self, name):
		self.name = name


class PermissionRequested(PermissionState):
	def claimedBy(self, systemPermission):
		systemPermission.willBeHandledBy(systemPermission.admin)
		systemPermission.state = CLAIMED


class PermissionClaimed(PermissionState):
	def grantedBy(self, systemPermission):
		if systemPermission.state != CLAIMED:
			return
		systemPermission.state = GRANTED
		systemPermission.isGranted = True

class PermissionGranted(PermissionState):
	pass

REQUESTED = PermissionRequested("REQUESTED")
CLAIMED = PermissionClaimed("CLAIMED")
GRANTED = PermissionGranted("GRANTED")


###############
class SystemPermission:
	def __init__(self):
		self.state = REQUESTED
		self.admin = None
		self.isGranted = False



	def claimedBy(self):
		self.state.claimedBy(self)

	def grantedBy(self):
		self.state.grantedBy(self)

	def deniedBy(self):
		pass

	def willBeHandledBy(self, admin):
		pass


