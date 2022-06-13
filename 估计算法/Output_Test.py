import random
import json

import pymysql

quantity_test = 8  # 每级测试数目


def get_quantity_test():
    return quantity_test


def data_to_json(data_level):  # 将list转为json
    json_level = json.dumps(data_level)
    return json_level


'''
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


def list_to_test_db(list_level, length):  # 从该级词汇列表中选择词汇
    test_level = []
    data = []
    len_list = len(list_level) - 1
    # i = random.randint(0, len_list)
    # rank = list_level[i][0]  # rank为单词id
    # value = list_level[i][1]  # value为单词
    # test_level.append(rank)
    # data.append({'word': value, 'id': rank})
    while len(test_level) < length:  # 一级测试几个
        i = random.randint(0, len_list)
        rank = list_level[i][0]  # rank为单词id
        value = list_level[i][1]  # value为单词
        if rank not in test_level:
            test_level.append(rank)
            data.append({'word': value, 'id': rank})
    return data


def get_test_json_from_db(list_level1, list_level2, list_level3, list_level4, list_level5, list_quantity):  # 从远程数据库中读取
    # list_level1, list_level2, list_level3, list_level4, list_level5 = load_db()
    data_level1 = list_to_test_db(list_level1, list_quantity[0])
    data_level2 = list_to_test_db(list_level2, list_quantity[1])
    data_level3 = list_to_test_db(list_level3, list_quantity[2])
    data_level4 = list_to_test_db(list_level4, list_quantity[3])
    data_level5 = list_to_test_db(list_level5, list_quantity[4])
    json_level1 = data_to_json(data_level1)
    json_level2 = data_to_json(data_level2)
    json_level3 = data_to_json(data_level3)
    json_level4 = data_to_json(data_level4)
    json_level5 = data_to_json(data_level5)
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
