import random
import json

import pymysql

quantity_test = 8  # 每级测试数目


def get_quantity_test():
    return quantity_test


def data_to_json(data_level, level):  # 将list转为json
    json_level = {'code': 200, 'msg': 'success', 'level': level, 'localdata': data_level}
    json_level = json.dumps(json_level)
    return json_level

# def list_to_test(list_level):  # 从该级词汇列表中选择词汇
#     test_level = []
#     value_level = []
#     localdata = []
#     len_list = len(list_level) - 1
#     i = random.randint(0, len_list)
#     rank = int(list_level[i].split('---')[0])  # rank为单词id
#     value = list_level[i].split('---')[1]  # value为单词
#     test_level.append(rank)
#     # value_level.append(value)
#     localdata.append({'value': value, 'id': rank})
#     while len(test_level) < quantity_test:  # 一级测试几个
#         i = random.randint(0, len_list)
#         rank = int(list_level[i].split('---')[0])  # rank作为id
#         value = list_level[i].split('---')[1]
#         if rank not in test_level:
#             test_level.append(rank)
#             # value_level.append(value)
#             localdata.append({'value': value, 'id': rank})
#     return localdata
#
#
# def load_txt():  # 读取单词进入该单词分级的列表中
#     list_level1 = []
#     list_level2 = []
#     list_level3 = []
#     list_level4 = []
#     list_level5 = []
#     with open('4_6_ky.txt', encoding='utf-8') as f:
#         for i in f:
#             j = i.split('---')  # 缩进符
#             if int(j[3]) == 1:
#                 list_level1.append(i)
#             elif int(j[3]) == 2:
#                 list_level2.append(i)
#             elif int(j[3]) == 3:
#                 list_level3.append(i)
#             elif int(j[3]) == 4:
#                 list_level4.append(i)
#             elif int(j[3]) == 5:
#                 list_level5.append(i)
#     return list_level1, list_level2, list_level3, list_level4, list_level5
#
#

#
#
# def get_test_json():  # 从本地txt文件中读取
#     list_level1, list_level2, list_level3, list_level4, list_level5 = load_txt()
#     data_level1 = list_to_test(list_level1)
#     data_level2 = list_to_test(list_level2)
#     data_level3 = list_to_test(list_level3)
#     data_level4 = list_to_test(list_level4)
#     data_level5 = list_to_test(list_level5)
#     json_level1 = data_to_json(data_level1, 1)
#     json_level2 = data_to_json(data_level2, 2)
#     json_level3 = data_to_json(data_level3, 3)
#     json_level4 = data_to_json(data_level4, 4)
#     json_level5 = data_to_json(data_level5, 5)
#     return json_level1, json_level2, json_level3, json_level4, json_level5


'''
上面是本地读取
----------------分割线----------------
下面是远程数据库读取

'''


def load_db():
    list_level1 = []
    list_level2 = []
    list_level3 = []
    list_level4 = []
    list_level5 = []
    db = pymysql.connect(host="sh-cynosdbmysql-grp-ky91abiy.sql.tencentcdb.com",
                         port=25742, user='root', passwd='Rotoc.111', db='ve')
    cursor = db.cursor()
    sql = "select * from Words"
    cursor.execute(sql)
    results = cursor.fetchall()
    for row in results:
        j = [row[0], row[1], row[2], row[3], row[4], row[5]]
        if int(j[3]) == 1:
            list_level1.append(j)
        elif int(j[3]) == 2:
            list_level2.append(j)
        elif int(j[3]) == 3:
            list_level3.append(j)
        elif int(j[3]) == 4:
            list_level4.append(j)
        elif int(j[3]) == 5:
            list_level5.append(j)
    db.close()
    return list_level1, list_level2, list_level3, list_level4, list_level5


def list_to_test_db(list_level):  # 从该级词汇列表中选择词汇
    test_level = []
    value_level = []
    data = []
    len_list = len(list_level) - 1
    i = random.randint(0, len_list)
    rank = list_level[i][0]  # rank为单词id
    value = list_level[i][1]  # value为单词
    test_level.append(rank)
    # value_level.append(value)
    data.append({'value': value, 'id': rank})
    while len(test_level) < quantity_test:  # 一级测试几个
        i = random.randint(0, len_list)
        rank = list_level[i][0]  # rank为单词id
        value = list_level[i][1]  # value为单词
        if rank not in test_level:
            test_level.append(rank)
            # value_level.append(value)
            data.append({'value': value, 'id': rank})
    return data


def get_test_json_from_db():  # 从远程数据库中读取
    list_level1, list_level2, list_level3, list_level4, list_level5 = load_db()
    data_level1 = list_to_test_db(list_level1)
    data_level2 = list_to_test_db(list_level2)
    data_level3 = list_to_test_db(list_level3)
    data_level4 = list_to_test_db(list_level4)
    data_level5 = list_to_test_db(list_level5)
    json_level1 = data_to_json(data_level1, 1)
    json_level2 = data_to_json(data_level2, 2)
    json_level3 = data_to_json(data_level3, 3)
    json_level4 = data_to_json(data_level4, 4)
    json_level5 = data_to_json(data_level5, 5)
    return json_level1, json_level2, json_level3, json_level4, json_level5


def get_id_list():
    l1, l2, l3, l4, l5 = load_db()
    id_list_level1 = []
    id_list_level2 = []
    id_list_level3 = []
    id_list_level4 = []
    id_list_level5 = []
    for i in l1:
        id_list_level1.append(i[0])
    for i in l2:
        id_list_level2.append(i[0])
    for i in l3:
        id_list_level3.append(i[0])
    for i in l4:
        id_list_level4.append(i[0])
    for i in l5:
        id_list_level5.append(i[0])
    return id_list_level1, id_list_level2, id_list_level3, id_list_level4, id_list_level5
# print(get_test_json())
