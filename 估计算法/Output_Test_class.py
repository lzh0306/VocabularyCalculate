import random
import json

from Output_Test import get_test_json_from_db, get_quantity_test, load_db


# quantity_test = get_quantity_test()


class OutputTest:
    def __init__(self):
        # self.json_level1, self.json_level2, self.json_level3, self.json_level4, self.json_level5 = get_test_json()
        """上面是本地txt读取，下面是远程db"""

        self.list_level1, self.list_level2, self.list_level3, self.list_level4, self.list_level5 = load_db()
        # self.json_level1, self.json_level2, self.json_level3, self.json_level4, self.json_level5 = \
        #     get_test_json_from_db(self.list_level1, self.list_level2, self.list_level3, self.list_level4,
        #                           self.list_level5, test_quantity)

    # def update_test_json_level(self, test_quantity):  # 更新测试数据
    #     self.json_level1 = []
    #     self.json_level2 = []
    #     self.json_level3 = []
    #     self.json_level4 = []
    #     self.json_level5 = []
    #     # self.json_level1, self.json_level2, self.json_level3, self.json_level4, self.json_level5 = get_test_json()
    #     '''上面是本地txt，下面是远程db'''
    #     self.json_level1, self.json_level2, self.json_level3, self.json_level4, self.json_level5 = \
    #         get_test_json_from_db(self.list_level1, self.list_level2, self.list_level3, self.list_level4,
    #                               self.list_level5, test_quantity)

    def get_jl(self, test_quantity):
        json_level1, json_level2, json_level3, json_level4, json_level5 = \
            get_test_json_from_db(self.list_level1, self.list_level2, self.list_level3, self.list_level4,
                                  self.list_level5, test_quantity)
        # self.update_test_json_level(test_quantity)
        data = []
        for i in range(0, test_quantity[0], 1):
            data.append(json.loads(json_level1)[i])
        for i in range(0, test_quantity[1], 1):
            data.append(json.loads(json_level2)[i])
        for i in range(0, test_quantity[2], 1):
            data.append(json.loads(json_level3)[i])
        for i in range(0, test_quantity[3], 1):
            data.append(json.loads(json_level4)[i])
        for i in range(0, test_quantity[4], 1):
            data.append(json.loads(json_level5)[i])
        jl = {'code': 1, 'msg': 'success', 'data': data}
        jl = json.dumps(jl)
        return jl


        # self.jl = json.loads(self.jl)
        # print(self.jl['data_level'][2]['localdata'])

    # def clear1(self):
    #     self.json_level1 = []
    #     self.json_level2 = []
    #     self.json_level3 = []
    #     self.json_level4 = []
    #     self.json_level5 = []

# op = OutputTest()
# jl = op.get_jl([8,8,8,8,8])
# print(jl)
# jl = json.loads(jl)
# print(len(jl['data']))

# print('--------')
# op.output()
# print('---------------')
# # op.clear1()
# op.update_test_json()
# print(op.output())
# op.get_jl()
