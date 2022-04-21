from tree_expression_package.txpr_node import txpr_node_obj

class txpr_tree:

    def __init__(self, string_expr : str):
        '''
        Constructor for tree expression tree.
        :param string_expr: A string that must be int the format of:
                                txpr := [int] | '(' [txpr] [op] [txpr] ')'
        '''

        if string_expr == None or string_expr == "":
            raise ValueError("String to build tree cannot be empty or null")

        self.root = txpr_node_obj()
        (self.root).build_helper(string_expr)

    def solve_tree_xpr(self) -> list:
        '''
        The method that will solve the tree expression. It returns
            a list with a status and answer to the tree expression.
        :return: A list [<Status>, <Value>] where the status indicates
            whether a zero division occured or not.
        '''
        status = "Failed"

        try:
            solved = (self.root).node_solve_txpr()
            status = "Passed"
            return [status, solved]
        except ZeroDivisionError:
            solved = 0
            return [status, solved]