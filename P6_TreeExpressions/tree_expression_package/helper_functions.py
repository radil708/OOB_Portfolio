OPERATORS = ["+","-", "*","//","<<^"] # constant list

def parse_string_to_dict(expr : str) -> list:
    '''
    This function will parse a string txpr into
    segments of operator, left and right expression
    to be stored in a dictionary
    :param expr: a mathematical expression as a string
        > format of the string is:
        txpr := [int] | '(' [txpr] [op] [txpr] ')'
    :return: a dictionary containing the segments
        > ex: "( a + b )" will yield a dictionary of
            dictionary[op] = "+"
            dictionary[left] = "a"
            dictionary[right] = "b"
        > if dictionary[op] not in OPERATORS then it's a single int i.e.
        ex: "a" will yield dictionary of
            dictionary[op] = "a"
            dictionary[left] = None
            dictionary[right] = None
        > a and b can be txpr themselves.
    '''


    expr_lst = expr.split(" ")
    negative_len_list = -(len(expr_lst)) - 1 #used to iterate list backwards
    right_paren_count = 0 # counts amount of right parentheses "scanned"
    item_dict = {} # dictionary to return
    operator_split_pos = -1

    if len(expr_lst) > 1: # if the whole txpr is NOT an int
        for i in range(-1, negative_len_list, -1):
            if expr_lst[i].isnumeric():
                #if the expression is just the number
                if right_paren_count == 0:
                    operator_split_pos = i
                    break
                else:
                    continue
            elif expr_lst[i] == ")":
                right_paren_count += 1
                continue
            elif expr_lst[i] == "(":
                right_paren_count -= 1
                continue
            elif expr_lst[i] in OPERATORS:
                if right_paren_count == 1:
                    operator_split_pos = i
                    break

        operator = expr_lst[operator_split_pos]
        # left expression does not have an empty space on the right side/end of expression
        # outer parenthesis pair is removed via list slice
        left_expr = " ".join(expr_lst[negative_len_list + 2:operator_split_pos])
        # WARNING: Possible error point: maybe don't include ) paren below
        right_expr = " ".join(expr_lst[operator_split_pos + 1:-1])
        item_dict["op"] = operator
        item_dict["left"] = left_expr
        item_dict["right"] = right_expr
    else:
        item_dict["op"] = expr_lst[0]
        item_dict["left"] = None
        item_dict["right"] = None

    return item_dict

def isInt(value : str):
    '''
    This function checks if the string can be converted into a
        numeric (float).
    :param value: The string to analyze.
    :return: A boolean, true if the string param is a numeric, false otherwise
    '''
    try:
        float(value)
        return True
    except ValueError:
        return False

def parse_input_to_useable():
    x = ""
    counter = 1
    y = input("input line {}\n".format(counter))

    while y != "":
        x += y
        counter += 1
        y = input("input line {}\n".format(counter))

    print(x)

