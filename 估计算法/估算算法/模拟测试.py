import json
import random

from 选择输出 import get_quantity_test

quantity_level = get_quantity_test()  # 获取每级测试数目

json_level1 = {"code": 200, "msg": "success", "level": 1,
               "localdata": [{"value": "thicken", "rank": 8662}, {"value": "injure", "rank": 3755},
                        {"value": "cigar", "rank": 5775}, {"value": "bazaar", "rank": 16232},
                        {"value": "henceforth", "rank": 18166}, {"value": "integral", "rank": 5970},
                        {"value": "rose", "rank": 3397}, {"value": "apprehensive", "rank": 13875}]}
json_level2 = {"code": 200, "msg": "success", "level": 2,
               "localdata": [{"value": "plea", "rank": 4951}, {"value": "torment", "rank": 12343},
                        {"value": "oxygen", "rank": 3875}, {"value": "microwave", "rank": 5935},
                        {"value": "nut", "rank": 3216}, {"value": "random", "rank": 3656},
                        {"value": "enlarge", "rank": 7674}, {"value": "aspire", "rank": 7970}]}
json_level3 = {"code": 200, "msg": "success", "level": 3,
               "localdata": [{"value": "childhood", "rank": 2124}, {"value": "breath", "rank": 1637},
                        {"value": "praise", "rank": 3754}, {"value": "ballot", "rank": 3747},
                        {"value": "occasional", "rank": 3542}, {"value": "evil", "rank": 3478},
                        {"value": "naturally", "rank": 2820}, {"value": "laboratory", "rank": 3156}]}
json_level4 = {"code": 200, "msg": "success", "level": 4,
               "localdata": [{"value": "source", "rank": 597}, {"value": "movement", "rank": 708},
                        {"value": "reject", "rank": 2046}, {"value": "basic", "rank": 1147},
                        {"value": "fair", "rank": 1493}, {"value": "rock", "rank": 872},
                        {"value": "politician", "rank": 1797}, {"value": "bottom", "rank": 1520}]}
json_level5 = {"code": 200, "msg": "success", "level": 5,
               "localdata": [{"value": "course", "rank": 419}, {"value": "least", "rank": 303},
                        {"value": "player", "rank": 514}, {"value": "director", "rank": 511},
                        {"value": "line", "rank": 277}, {"value": "direct", "rank": 1487},
                        {"value": "however", "rank": 285}, {"value": "less", "rank": 478}]}


def get_json_result(json_level):
    data_result = []
    for i in range(0, quantity_level, 1):
        j = random.randint(0, 1)
        data = {'rank': json_level['localdata'][i]['rank'], 'result': j}
        data_result.append(data)
    json_result = {'code': 200, 'msg': 'success', 'level': json_level['level'],'localdata':data_result }
    json_result = json.dumps(json_result)
    return json_result


# print(json_level1['level'])
print(get_json_result(json_level1))
print(get_json_result(json_level2))
print(get_json_result(json_level3))
print(get_json_result(json_level4))
print(get_json_result(json_level5))
