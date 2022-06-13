import random
import json

from Output_Test import get_test_json_from_db, get_quantity_test

quantity_test = get_quantity_test()


class OutputTest:
    def __init__(self):
        # self.json_level1, self.json_level2, self.json_level3, self.json_level4, self.json_level5 = get_test_json()
        """上面是本地txt读取，下面是远程db"""
        self.json_level1, self.json_level2, self.json_level3, self.json_level4, self.json_level5 = get_test_json_from_db()

    def update_test_json_level(self):  # 更新测试数据
        self.json_level1 = []
        self.json_level2 = []
        self.json_level3 = []
        self.json_level4 = []
        self.json_level5 = []
        # self.json_level1, self.json_level2, self.json_level3, self.json_level4, self.json_level5 = get_test_json()
        '''上面是本地txt，下面是远程db'''
        self.json_level1, self.json_level2, self.json_level3, self.json_level4, self.json_level5 = get_test_json_from_db()

    def output(self):  # 输出
        return self.jl

    def get_jl(self):
        data = []
        for i in range(0, quantity_test, 1):
            data.append(json.loads(self.json_level1)['localdata'][i])
        for i in range(0, quantity_test, 1):
            data.append(json.loads(self.json_level2)['localdata'][i])
        for i in range(0, quantity_test, 1):
            data.append(json.loads(self.json_level3)['localdata'][i])
        for i in range(0, quantity_test, 1):
            data.append(json.loads(self.json_level4)['localdata'][i])
        for i in range(0, quantity_test, 1):
            data.append(json.loads(self.json_level5)['localdata'][i])
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


op = OutputTest()
# print('--------')
# op.output()
# print('---------------')
# # op.clear1()
# op.update_test_json()
# print(op.output())
op.get_jl()
