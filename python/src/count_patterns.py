"""
A string s contains many patterns of the form 1(0+)1 where (0+) represents
any non-empty consecutive sequence of zeros. The patterns are allowed to overlap.
For example, consider string 1101001, we can see there are two consecutive sequences
1(0)1 and 1(00)1 which are of the form 1(0+)1.
Find the total number of patterns of the form 1(0+)1 that occur in s.
"""


def count_patterns(string):
    """
    Returns number of patterns of the form 1(0+)1 that occur in string
    """
    number_of_patterns = 0
    end_of_previous_pattern = 0
    while end_of_previous_pattern < len(string):
        start = __index_of_one(string, end_of_previous_pattern)
        (end, is_valid_pattern) = __search_pattern_end(string, start + 1)
        if is_valid_pattern:
            number_of_patterns += 1
        end_of_previous_pattern = end
    return number_of_patterns


def __search_pattern_end(string, start):
    length = len(string)
    index = start
    while index < length:
        if string[index] != '0':
            break
        index += 1
    return (index, string[index] == '1' and index > start) if index < length else (length, False)


def __index_of_one(string, start):
    length = len(string)
    for index in range(start, length):
        if string[index] == '1':
            return index
    return length
