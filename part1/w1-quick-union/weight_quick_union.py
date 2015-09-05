class weight_quick_union(object):
	"""docstring for weight_quick_union"""
	def __init__(self, nb_of_nodes):
		super(weight_quick_union, self).__init__()
		self.nodes = range (nb_of_nodes)
		# the number of items consider this item as root
		self.sz = [1] * nb_of_nodes
		# id of father node of each item
		self.fathers = range (nb_of_nodes)

	def get_root (self, node_id):
		while (self.fathers [node_id] != node_id):
			node_id = self.fathers [node_id]
		return node_id

	def connect (self, node_id_1, node_id_2):
		if self.sz [self.get_root (node_id_1)] < self.sz [self.get_root (node_id_2)]:
			self.fathers [self.get_root (node_id_1)] = self.get_root (node_id_2)
			self.sz [self.get_root (node_id_2)] += self.sz [self.get_root (node_id_1)]
		else:
			self.fathers [self.get_root (node_id_2)] = self.get_root (node_id_1)
			self.sz [self.get_root (node_id_1)] += self.sz [self.get_root (node_id_2)]

	def get_fathers (self):
		return self.fathers

	def find (self, node_id_1, node_id_2):
		return self.get_root (node_id_1) == self.get_root (node_id_2)

if __name__ == "__main__":
	union = weight_quick_union (10)
	union.connect (2, 6)
	union.connect (1, 9)
	union.connect (4, 3)
	union.connect (5, 8)
	union.connect (5, 4)
	union.connect (6, 0)
	union.connect (1, 0)
	union.connect (9, 7)
	union.connect (9, 5)
	print(' '.join(map(str,union.get_fathers())))