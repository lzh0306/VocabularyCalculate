import json

from 输入计算 import quantities
from 选择输出 import get_id_list


class OutputResult:
    def __init__(self):
        self.l1, self.l2, self.l3, self.l4, self.l5 = get_id_list()

    def return_quantity(self, json_result):
        correct_quantity = [0, 0, 0, 0, 0]
        word_quantity = 0
        for i in range(0, 40, 1):
            if json_result[i]['known'] == 'true':
                if json_result[i]['wordId'] in self.l1:
                    correct_quantity[0] += 1
                elif json_result[i]['wordId'] in self.l2:
                    correct_quantity[1] += 1
                elif json_result[i]['wordId'] in self.l3:
                    correct_quantity[2] += 1
                elif json_result[i]['wordId'] in self.l4:
                    correct_quantity[3] += 1
                elif json_result[i]['wordId'] in self.l5:
                    correct_quantity[4] += 1
        for i in range(0, 5, 1):
            word_quantity += correct_quantity[i] * quantities[i] / 8
        return word_quantity

# outputResult = OutputResult()
# test_json = [{"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 597}, {"known": "true", "wordId": 597}, {"known": "true", "wordId": 597}, {"known": "false", "wordId": 419}, {"known": "true", "wordId": 419}, {"known": "true", "wordId": 419}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "false", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 597}, {"known": "true", "wordId": 597}, {"known": "true", "wordId": 597}, {"known": "true", "wordId": 419}, {"known": "true", "wordId": 419}, {"known": "true", "wordId": 419}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 8662}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 4951}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 2124}, {"known": "true", "wordId": 597}]
# print(type(test_json))
# r = outputResult.return_result(test_json)
# print(r)
