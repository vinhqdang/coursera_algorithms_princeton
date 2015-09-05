# Author: Vinh Dang
# Compiler: Python 2.7+
# the program receives a list of nodes, union command calling list
# and calculate the list of connected component ID
# for more information, please refer to the text of the course site

class quick_find(object):
	"""docstring for Union"""
	def __init__(self, number_of_nodes):
		super(Union, self).__init__()
		self.nodes = range (number_of_nodes)
		self.ids = range (number_of_nodes)

	def connect (self, node_id_1, node_id_2):
		# change id of node_id_2 and other nodes in the same connected component
		# to id of node_id_1
		id_2 = self.ids [node_id_2]
		for i in range (len (self.nodes)):
			if self.ids [i] == id_2:
				self.ids [i] = self.ids [node_id_1]

	def find (self, node_id_1, node_id_2):
		# check if two nodes are connected
		# if and only if their component id are the same
		return self.ids [node_id_1] == self.ids [node_id_2]

	def get_ids (self):
		return self.ids

# test the code
if __name__ == "__main__":
	union = quick_find (10)
	union.connect (0, 5)
	union.connect (9, 6)
	union.connect (9, 2)
	union.connect (4, 3)
	union.connect (2, 3)
	union.connect (0, 6)
	print union.get_ids ()

