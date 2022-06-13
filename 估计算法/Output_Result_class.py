import json

from Output_Result import quantities
from Output_Test import get_id_list


class OutputResult:
    def __init__(self):
        self.l1, self.l2, self.l3, self.l4, self.l5 = get_id_list()

    def return_quantity(self, json_result):
        correct_quantity = [0, 0, 0, 0, 0]
        level_quantity = [0, 0, 0, 0, 0]
        word_quantity = 0
        for i in range(0, 80, 1):
            if json_result[i]['known']:
                if json_result[i]['wordId'] in self.l1:
                    correct_quantity[0] += 1
                    level_quantity[0] += 1
                elif json_result[i]['wordId'] in self.l2:
                    correct_quantity[1] += 1
                    level_quantity[1] += 1
                elif json_result[i]['wordId'] in self.l3:
                    correct_quantity[2] += 1
                    level_quantity[2] += 1
                elif json_result[i]['wordId'] in self.l4:
                    correct_quantity[3] += 1
                    level_quantity[3] += 1
                elif json_result[i]['wordId'] in self.l5:
                    correct_quantity[4] += 1
                    level_quantity[4] += 1
            else:
                if json_result[i]['wordId'] in self.l1:
                    level_quantity[0] += 1
                elif json_result[i]['wordId'] in self.l2:
                    level_quantity[1] += 1
                elif json_result[i]['wordId'] in self.l3:
                    level_quantity[2] += 1
                elif json_result[i]['wordId'] in self.l4:
                    level_quantity[3] += 1
                elif json_result[i]['wordId'] in self.l5:
                    level_quantity[4] += 1
        for i in range(0, 5, 1):
            word_quantity += correct_quantity[i] * quantities[i] / level_quantity[i]
        return word_quantity

    def return_second_test_quantity_list(self, json_result):
        correct_quantity = [0, 0, 0, 0, 0]
        for i in range(0, 40, 1):
            if json_result[i]['known']:
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
        # correct_quantity = [5, 4, 3, 2, 1]
        test_quantity = [0, 0, 0, 0, 0]
        correct_sum = 0
        word_quantity = 0

        for i in range(0, 5, 1):
            correct_sum += correct_quantity[i]
        for i in range(0, 5, 1):
            test_quantity[i] = 40 * correct_quantity[i] / correct_sum
            test_quantity[i] = int(test_quantity[i] / 1)
        index_max = 0
        for i in range(0, 5, 1):
            if correct_quantity[i] > correct_quantity[index_max]:
                index_max = i
        print(test_quantity)
        test_sum = 0
        for i in range(0, 5, 1):
            test_sum += test_quantity[i]
        if test_sum < 40:
            test_quantity[index_max] += 40 - test_sum
        print(test_quantity)
        return test_quantity

# outputResult = OutputResult()
# outputResult.return_second_test(0)
