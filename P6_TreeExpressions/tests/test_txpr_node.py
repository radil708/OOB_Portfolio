import unittest
from tree_expression_package.txpr_node import txpr_node_obj


class TestTxprNodes(unittest.TestCase):

    def setUp(self):
        self.node1 = txpr_node_obj()
        self.xpr1 = "5"
        self.xpr2 = "-10"
        self.xpr3 = "( 2 // 3 )"

    def test_constructor(self):
        self.assertEqual(None, self.node1.data)
        self.assertEqual(None, self.node1.left_p)
        self.assertEqual(None, self.node1.right_p)

    def testBuildNodeStructureInt(self):
        self.node1.build_helper(self.xpr1)
        self.assertEqual(5, (self.node1).data)
        self.assertEqual(None, (self.node1).left_p)
        self.assertEqual(None, (self.node1).right_p)

    def testBuildNodeStructureIntNeg(self):
        self.node1.build_helper(self.xpr2)
        self.assertEqual(-10, (self.node1).data)
        self.assertEqual(None, (self.node1).left_p)
        self.assertEqual(None, (self.node1).right_p)

    def testBuildNodeSimpleXpr(self):
        self.node1.build_helper(self.xpr3)
        self.assertEqual("//", (self.node1).data)
        left_node = (self.node1).left_p
        right_node = (self.node1).right_p
        self.assertEqual(2, left_node.data)
        self.assertEqual(3, right_node.data)

    def testBuildComplicated1(self):
        x = "( 5 * ( 2 // -3 ) )"
        self.node1.build_helper(x)
        self.assertEqual("*", (self.node1).data)

        level1_left = (self.node1).left_p
        level1_right = (self.node1).right_p
        self.assertEqual(5, level1_left.data)
        self.assertEqual(None, level1_left.left_p)
        self.assertEqual(None, level1_left.right_p)
        self.assertEqual("//", level1_right.data)

        level2_leftchild_of_level1right = level1_right.left_p
        level2_rightchild_of_level1right = level1_right.right_p
        self.assertEqual(2, level2_leftchild_of_level1right.data)
        self.assertEqual(None, level2_leftchild_of_level1right.left_p)
        self.assertEqual(None, level2_leftchild_of_level1right.right_p)
        self.assertEqual(-3, level2_rightchild_of_level1right.data)
        self.assertEqual(None, level2_rightchild_of_level1right.left_p)
        self.assertEqual(None, level2_rightchild_of_level1right.right_p)

    def testSolve1(self):
        self.node1.build_helper(self.xpr1)
        self.assertEqual(5, self.node1.node_solve_txpr())

        node2 = txpr_node_obj()
        node2.build_helper(self.xpr2)
        self.assertEqual(-10, node2.node_solve_txpr())

    def testSolve2(self):
        self.node1.build_helper(self.xpr3)
        self.assertEqual(0, self.node1.node_solve_txpr())

    def testRaiseZeroError(self):
        self.node1.build_helper("( 2 // 0 )")
        with self.assertRaises(ZeroDivisionError):
            self.node1.node_solve_txpr()

    def testRaiseZero2(self):
        x = "( ( ( ( ( 2121034265 - ( -1362320398 // -1598327332 ) ) <<^ ( -1084742079 <<^ 2025695981 ) ) + ( ( ( " \
            "-1499725523 * 1226180197 ) <<^ ( -1458962115 // 1679243938 ) ) <<^ ( ( ( ( -37467154 * 868508523 ) * ( ( " \
            "( -1919520462 - 1283811728 ) * ( ( -886841718 // 194114367 ) * ( -510612122 - ( 241187362 // 1211702715 " \
            ") ) ) ) // ( ( ( -793873958 + -549241529 ) + -1520294578 ) + ( -1969166079 + 286459704 ) ) ) ) - " \
            "1780140246 ) <<^ ( ( -1451648835 * -1688375010 ) + ( ( -453990213 // -653825190 ) + ( ( ( -1534980779 * " \
            "1008208048 ) + ( 228730444 - 2011470872 ) ) * -446146030 ) ) ) ) ) ) <<^ ( ( ( ( ( -1898346654 <<^ " \
            "-1301453199 ) * 768560566 ) // ( -114228069 // 1947249057 ) ) - ( ( ( ( -979709711 - ( 250343953 * " \
            "-617359272 ) ) + 64459754 ) <<^ ( ( ( ( 1043124425 + ( 1192091003 <<^ 561849741 ) ) + 1204338143 ) + " \
            "1341709223 ) <<^ ( ( 1853584882 + ( ( -2087831407 // ( ( -1859587024 - -1449799538 ) * 706731343 ) ) // " \
            "1434406504 ) ) + ( 265524022 * 2129947997 ) ) ) ) * ( -1561046929 // ( 2071735449 <<^ 1598417605 ) ) ) ) " \
            "<<^ ( ( -1938880905 // 1732631279 ) - 1063094031 ) ) ) * ( ( ( ( 208429724 + ( -1695507684 <<^ " \
            "1983222085 ) ) <<^ ( 1623455938 + ( ( -34937652 <<^ ( -569452094 <<^ -916871833 ) ) // ( ( 1059251101 + " \
            "1845071692 ) - -1672536338 ) ) ) ) - ( ( ( ( 1572948000 + ( -936620457 // 1476986110 ) ) <<^ ( ( " \
            "1141664059 - -1693307541 ) * ( ( -255634292 + -1395580150 ) * ( 1084630309 <<^ -1169790078 ) ) ) ) - ( ( " \
            "-547459515 // 482297501 ) * ( ( 461828879 * 298466258 ) - ( ( -794306182 <<^ -1700439312 ) * ( " \
            "-2039336747 + -1163422135 ) ) ) ) ) + -1869021759 ) ) <<^ ( ( ( ( 105164505 * -604781850 ) * -812336002 " \
            ") + ( ( ( ( ( -1906538964 * 886189226 ) + 1311829189 ) <<^ ( 1599178428 - -1427793326 ) ) // ( ( ( ( " \
            "-22481901 <<^ 2053383808 ) - 1699342568 ) + ( ( -382764021 <<^ 1820180422 ) <<^ 1571849620 ) ) + ( " \
            "-283543375 - -55971170 ) ) ) - ( ( ( -1290351086 <<^ ( -1132344796 * 37006194 ) ) // ( 226237214 * ( ( " \
            "-124145501 * -645277570 ) * 130204634 ) ) ) // 1921549170 ) ) ) // ( 525715353 // ( ( ( 719754363 - " \
            "-1754491217 ) + ( -131460798 * ( 2109901059 * 1817484234 ) ) ) <<^ ( ( -1308336071 + -1214320267 ) * " \
            "1342918677 ) ) ) ) ) )"

        self.node1.build_helper(x)
        with self.assertRaises(ZeroDivisionError):
            self.node1.node_solve_txpr()

    def testExampleGive(self):
        x = "( ( ( ( ( ( ( ( 330938623 * ( -44720962 // 12312 ) ) // -14069 ) // ( ( ( ( 21486 + -3797 ) - ( 6539 - " \
            "-4354 ) ) // -92 ) // ( ( -76 + ( ( -62 // 3 ) - ( -80 + -111 ) ) ) - 84 ) ) ) <<^ ( ( -1662702836 // ( " \
            "24247 - -6619 ) ) + ( ( ( ( -1498156034 <<^ -708311476 ) - ( 1541239628 // -24158 ) ) * ( ( 206140450 + " \
            "( 828742585 + -1005239800 ) ) * ( ( -463178467 // 23507 ) + ( ( ( -2090027431 - 2036864129 ) * " \
            "1806232291 ) * ( 158179264 * -887639797 ) ) ) ) ) // 29362 ) ) ) - ( ( ( -1285730016 // ( 27254 * -26475 " \
            ") ) <<^ ( ( -595204296 * 1993902957 ) + ( -1967172409 + -372520500 ) ) ) // ( ( 31441 + ( -23735 <<^ ( " \
            "10745 * 26428 ) ) ) + 17296 ) ) ) - ( ( -1321704357 <<^ ( ( ( ( -1198070549 + -1614881430 ) + " \
            "-1814505099 ) // -1157 ) - 1251931495 ) ) * ( ( -545687743 <<^ 551487888 ) - 1081017645 ) ) ) <<^ " \
            "-1846872194 ) + ( ( ( ( ( ( -1665375506 + -233210943 ) <<^ ( 1177854063 + -1602657823 ) ) * ( " \
            "-2080846166 * ( 296865374 <<^ -1261409301 ) ) ) * ( ( ( 2064756327 <<^ -206315412 ) // ( ( -24974 // 10 " \
            ") + ( ( -24220 - -24063 ) + -31439 ) ) ) + 40477143 ) ) // ( ( ( -18376 // ( ( ( -127 - 61 ) + ( 50 * 74 " \
            ") ) // ( 7 // -1 ) ) ) // ( ( -92 * ( 87 - -30 ) ) <<^ 111 ) ) // ( ( 7 // ( -7 + -2 ) ) * ( -75 * ( " \
            "-106 <<^ -7 ) ) ) ) ) + ( ( 1057863386 * 380984409 ) // ( ( 10347 * 24704 ) + ( -25579 + -10966 ) ) ) ) )"
        self.node1.build_helper(x)
        self.assertEqual(106239235818368919843985044682453135716255749122013737872,self.node1.node_solve_txpr())

