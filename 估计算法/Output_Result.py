import json

from Output_Test import get_quantity_test, load_db, get_id_list

# Quantity_level1 = 1405
# Quantity_level2 = 1936
# Quantity_level3 = 1141
# Quantity_level4 = 701
# Quantity_level5 = 192
quantities = [1405, 1936, 1141, 701, 192]
quantity_test = get_quantity_test()
test_level1 = []
test_level2 = []
test_level3 = []
test_level4 = []
test_level5 = []
result_level1 = []
result_level2 = []
result_level3 = []
result_level4 = []
result_level5 = []

# json_result1 = {"localdata": [{"rank": 8662, "result": 0}, {"rank": 3755, "result": 1}, {"rank": 5775, "result": 0},
#                          {"rank": 16232, "result": 1}, {"rank": 18166, "result": 0}, {"rank": 5970, "result": 1},
#                          {"rank": 3397, "result": 1}, {"rank": 13875, "result": 1}]}
# json_result2 = {"localdata": [{"rank": 4951, "result": 1}, {"rank": 12343, "result": 1}, {"rank": 3875, "result": 1},
#                          {"rank": 5935, "result": 1}, {"rank": 3216, "result": 0}, {"rank": 3656, "result": 0},
#                          {"rank": 7674, "result": 1}, {"rank": 7970, "result": 0}]}
# json_result3 = {"localdata": [{"rank": 2124, "result": 1}, {"rank": 1637, "result": 1}, {"rank": 3754, "result": 0},
#                          {"rank": 3747, "result": 1}, {"rank": 3542, "result": 1}, {"rank": 3478, "result": 0},
#                          {"rank": 2820, "result": 1}, {"rank": 3156, "result": 1}]}
# json_result4 = {"localdata": [{"rank": 597, "result": 1}, {"rank": 708, "result": 1}, {"rank": 2046, "result": 1},
#                          {"rank": 1147, "result": 1}, {"rank": 1493, "result": 1}, {"rank": 872, "result": 0},
#                          {"rank": 1797, "result": 0}, {"rank": 1520, "result": 0}]}
# json_result5 = {"localdata": [{"rank": 419, "result": 0}, {"rank": 303, "result": 0}, {"rank": 514, "result": 0},
#                          {"rank": 511, "result": 1}, {"rank": 277, "result": 1}, {"rank": 1487, "result": 1},
#                          {"rank": 285, "result": 1}, {"rank": 478, "result": 0}]}
#
json_get = [{"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662},
            {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951},
            {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124},
            {"known": "true", "wordId": 597}, {"known": "true", "wordId": 597}, {"known": "true", "wordId": 597},
            {"known": "false", "wordId": 419}, {"known": "true", "wordId": 419}, {"known": "true", "wordId": 419},
            {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662},
            {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951},
            {"known": "false", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124},
            {"known": "true", "wordId": 597}, {"known": "true", "wordId": 597}, {"known": "true", "wordId": 597},
            {"known": "true", "wordId": 419}, {"known": "true", "wordId": 419}, {"known": "true", "wordId": 419},
            {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662},
            {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951},
            {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124},
            {"known": "true", "wordId": 597}
            ]
#
# json_get = json.dumps(json_get)
#
#
# def ceshi():
#     return json_get


def return_json_result(json_result):
    json_result = json.loads(json_result)
    l1, l2, l3, l4, l5 = get_id_list()
    correct_quantity = [0, 0, 0, 0, 0]
    word_quantity = 0
    for i in range(0, 40, 1):
        if json_result[i]['known'] == 'true':
            if json_result[i]['wordId'] in l1:
                correct_quantity[0] += 1
            elif json_result[i]['wordId'] in l2:
                correct_quantity[1] += 1
            elif json_result[i]['wordId'] in l3:
                correct_quantity[2] += 1
            elif json_result[i]['wordId'] in l4:
                correct_quantity[3] += 1
            elif json_result[i]['wordId'] in l5:
                correct_quantity[4] += 1
    for i in range(0,5,1):
        word_quantity += correct_quantity[i] * quantities[i] / 8
    return word_quantity


# def return_quantity_by_json_result(json_result):
#     correct_quantity = 0
#     level = json_result['level']
#     for i in range(0, quantity_test, 1):
#         if json_result['localdata'][i]['result'] == 1:
#             correct_quantity += 1
#     quantity = correct_quantity / quantity_test
#     quantity = quantity * quantities[level - 1]
#     return quantity


# return_json_result(json_get)
