import unittest
from tree_expression_package.helper_functions import parse_string_to_dict, isInt

class TestParseStringMod(unittest.TestCase):

    def test_int_only(self):
        expr0 = "7"
        dict_expr0 = parse_string_to_dict(expr0)
        self.assertEqual("7", dict_expr0["op"])
        self.assertEqual(None, dict_expr0["left"])
        self.assertEqual(None, dict_expr0["right"])

    def test_simple_expr(self):
        expr1 = "( 1 + 2 )"
        dict_expr1 = parse_string_to_dict(expr1)
        self.assertEqual("+", dict_expr1["op"])
        self.assertEqual("1", dict_expr1["left"])
        self.assertEqual("2", dict_expr1["right"])

    def test_complicated_easy1(self):
        expr2 = "( 5 * ( 2 // 3 ) )"
        dict_expr2 = parse_string_to_dict(expr2)
        self.assertEqual("*", dict_expr2["op"])
        self.assertEqual("5", dict_expr2["left"])
        self.assertEqual("( 2 // 3 )", dict_expr2["right"])

    def test_complicated_easy2(self):
        expr3 = "( ( ( 1 + 2 ) - ( 3 - 4 ) ) // 6 )"
        dict_expr3 = parse_string_to_dict(expr3)
        self.assertEqual("//", dict_expr3["op"])
        self.assertEqual("( ( 1 + 2 ) - ( 3 - 4 ) )", dict_expr3["left"])
        self.assertEqual("6", dict_expr3["right"])
        
    def test_complicated(self):
        expr4 = "( ( 1 + 2 ) - ( 3 * 4 ) )"

        dict_expr4 = parse_string_to_dict(expr4)
        self.assertEqual("-", dict_expr4["op"])
        self.assertEqual("( 1 + 2 )", dict_expr4["left"])
        self.assertEqual("( 3 * 4 )", dict_expr4["right"])

    def test_complicated2(self):
        expr4 = "( ( 1 + 2 ) <<^ ( 3 * 4 ) )"

        dict_expr4 = parse_string_to_dict(expr4)
        self.assertEqual("<<^", dict_expr4["op"])
        self.assertEqual("( 1 + 2 )", dict_expr4["left"])
        self.assertEqual("( 3 * 4 )", dict_expr4["right"])

    def test_simple_expr_neg(self):
        expr1 = "( 1 + -2 )"
        dict_expr1 = parse_string_to_dict(expr1)
        self.assertEqual("+", dict_expr1["op"])
        self.assertEqual("1", dict_expr1["left"])
        self.assertEqual("-2", dict_expr1["right"])

        expr2 = "( -1 + 2 )"
        dict_expr2 = parse_string_to_dict(expr2)
        self.assertEqual("+", dict_expr2["op"])
        self.assertEqual("-1", dict_expr2["left"])
        self.assertEqual("2", dict_expr2["right"])

    def test_complicated_easy1_neg(self):
        expr1 = "( -5 * ( 2 // 3 ) )"
        dict_expr1 = parse_string_to_dict(expr1)
        self.assertEqual("*", dict_expr1["op"])
        self.assertEqual("-5", dict_expr1["left"])
        self.assertEqual("( 2 // 3 )", dict_expr1["right"])

        expr2 = "( 5 * ( -2 // 3 ) )"
        dict_expr2 = parse_string_to_dict(expr2)
        self.assertEqual("*", dict_expr2["op"])
        self.assertEqual("5", dict_expr2["left"])
        self.assertEqual("( -2 // 3 )", dict_expr2["right"])

        expr3 = "( 5 * ( 2 // -3 ) )"
        dict_expr3 = parse_string_to_dict(expr3)
        self.assertEqual("*", dict_expr3["op"])
        self.assertEqual("5", dict_expr3["left"])
        self.assertEqual("( 2 // -3 )", dict_expr3["right"])

    def test_complicated_easy2_neg(self):
        expr1 = "( ( ( 1 + 2 ) - ( 3 - 4 ) ) // -6 )"
        dict_expr1 = parse_string_to_dict(expr1)
        self.assertEqual("//", dict_expr1["op"])
        self.assertEqual("( ( 1 + 2 ) - ( 3 - 4 ) )", dict_expr1["left"])
        self.assertEqual("-6", dict_expr1["right"])

        expr2 = "( ( ( 1 + 2 ) - ( 3 - -4 ) ) // -6 )"
        dict_expr2 = parse_string_to_dict(expr2)
        self.assertEqual("//", dict_expr2["op"])
        self.assertEqual("( ( 1 + 2 ) - ( 3 - -4 ) )", dict_expr2["left"])
        self.assertEqual("-6", dict_expr2["right"])

        expr3 = "( ( ( 1 + -2 ) - ( 3 - -4 ) ) // 6 )"
        dict_expr3 = parse_string_to_dict(expr3)
        self.assertEqual("//", dict_expr3["op"])
        self.assertEqual("( ( 1 + -2 ) - ( 3 - -4 ) )", dict_expr3["left"])
        self.assertEqual("6", dict_expr3["right"])

    def testIsInt(self):
        x = "-2"
        self.assertTrue(isInt(x))

        y = "10"
        self.assertTrue(isInt(y))

        z = "( 2 + 4 )"
        self.assertFalse(isInt(z))

