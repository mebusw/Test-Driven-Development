'''
Created on 2014-6-17

@author: mebusw@gmail.com
'''


class PermissionState:
	def __init__(self, name):
		self.name = name

	def claimedBy(self, systemPermission):
		if systemPermission.state != REQUESTED:
			return
		systemPermission.willBeHandledBy(systemPermission.admin)
		systemPermission.state = CLAIMED

	def grantedBy(self, systemPermission):
		if systemPermission.state != CLAIMED:
			return
		systemPermission.state = GRANTED
		systemPermission.isGranted = True


class PermissionRequested(PermissionState):
	pass

class PermissionClaimed(PermissionState):
	pass

class PermissionGranted(PermissionState):
	pass

REQUESTED = PermissionRequested("REQUESTED")
CLAIMED = PermissionClaimed("CLAIMED")
GRANTED = PermissionGranted("GRANTED")


###############
class SystemPermission:
	REQUESTED = "REQUESTED"
	CLAIMED = "CLAIMED"
	GRANTED = "GRANTED"

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


