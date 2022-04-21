from tree_expression_package.helper_functions import parse_string_to_dict
from tree_expression_package.helper_functions import isInt

class txpr_node_obj:
    '''
    This is a node class for a binary tree. Each node
    has a left and right pointer. At construction each attribute only has none
    '''


    def __init__(self):
        '''
        - Constructor for t_node
        - Sets data, right_p, and left_p attribute to None
        - left_p and right_p will either be of type t_node or None
        - self.data can be a string expression of operator or int value of integer
        '''
        self.data = None
        self.right_p = None
        self.left_p = None

    #recursive algo to build node structure
    def build_helper(self, expr : str) -> None:
        '''
        - Recursive algorithm to build tree expression structure
        :param expr: string expression to be parsed
            - The format of the strings to parse is:
                > txpr := [int] | '(' [txpr] [op] [txpr] ')'
        :return: None
        '''

        dict_item = parse_string_to_dict(expr)

        #base case
        if isInt(dict_item["op"]):
            self.data = float(dict_item["op"])
            self.data = int(self.data)
            return None

        # recursive case
        else:
            self.data = dict_item["op"]
            self.left_p = txpr_node_obj()
            self.right_p = txpr_node_obj()

            (self.left_p).build_helper(dict_item["left"])
            (self.right_p).build_helper(dict_item["right"])


    def node_solve_txpr(self) -> int:
        '''
        This function solves the a expression. This needs to be called at the
        root node only. Calling at a different node will raise an error
        or give incorrect value.
        operators that are available are
            > op   := '+' | '-' | '*' | '//' | '<<^'
            > 'a // b' := floor[a / b]
            > 'a <<^ b' := (a << 13) (xor) b
        :return: int
        '''

        #must be called at the root

        if self.data is None:
            raise ValueError("The node does not contain any data" +
                             "\n please call '.build_helper(string expression)' method first")

        if isinstance(self.data, int):
            return self.data

        else:
            left_child = self.left_p
            right_child = self.right_p

            if self.data == "+":
                return left_child.node_solve_txpr() + right_child.node_solve_txpr()
            elif self.data == "-":
                return left_child.node_solve_txpr() - right_child.node_solve_txpr()
            elif self.data == "*":
                return left_child.node_solve_txpr() * right_child.node_solve_txpr()
            elif self.data == "//":
                if right_child.node_solve_txpr() == 0:
                    raise ZeroDivisionError("Cannot Divide By Zero")
                else:
                    return left_child.node_solve_txpr() // right_child.node_solve_txpr()
            elif self.data == "<<^":
                return (left_child.node_solve_txpr() << 13) ^ right_child.node_solve_txpr()
