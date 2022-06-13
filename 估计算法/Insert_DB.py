import pymysql

from Output_Test import load_txt

db = pymysql.connect(host="sh-cynosdbmysql-grp-ky91abiy.sql.tencentcdb.com",
                     port=25742, user='root', passwd='Rotoc.111', db='ve')
cursor = db.cursor()
'''读入接口，报废'''
l1, l2, l3, l4, l5 = load_txt()
'''↑↑↑'''
# for i in l1:
#     i = i.split('---')
#     i[0] = int(i[0])
#     i[3] = int(i[3])
#     i[5] = i[5].replace('\n', '')
#     sql = "insert into Words values(%s,%s,%s,%s,%s,%s)" % (i[0], i[1], i[2], i[3], i[4], i[5])
#     print(i)

# i = l1[0]
# i = i.split('---')
# i[0] = int(i[0])
# i[3] = int(i[3])
# i[5] = i[5].replace('\n', '')
# tag = '"' + i[4] + '"'
# print(tag)
# print(i)
# sql = "insert into Words values ({},'{}','{}',{},'{}','{}')".format(i[0], i[1], i[2], i[3], tag, i[5])
# cursor.execute(sql)
'''----------------'''
for i in l1:
    i = i.split('---')
    i[0] = int(i[0])
    i[3] = int(i[3])
    i[5] = i[5].replace('\n', '')
    tag = '"' + i[4] + '"'
    sql = "insert into Words values ({},'{}','{}',{},'{}','{}')".format(i[0], i[1], i[2], i[3], tag, i[5])
    print(i)
    cursor.execute(sql)
    # db.commit()
for i in l2:
    i = i.split('---')
    i[0] = int(i[0])
    i[3] = int(i[3])
    i[5] = i[5].replace('\n', '')
    tag = '"' + i[4] + '"'
    sql = "insert into Words values ({},'{}','{}',{},'{}','{}')".format(i[0], i[1], i[2], i[3], tag, i[5])
    print(i)
    cursor.execute(sql)
    # db.commit()
for i in l3:
    i = i.split('---')
    i[0] = int(i[0])
    i[3] = int(i[3])
    i[5] = i[5].replace('\n', '')
    tag = '"' + i[4] + '"'
    sql = "insert into Words values ({},'{}','{}',{},'{}','{}')".format(i[0], i[1], i[2], i[3], tag, i[5])
    cursor.execute(sql)
    # db.commit()
for i in l4:
    i = i.split('---')
    i[0] = int(i[0])
    i[3] = int(i[3])
    i[5] = i[5].replace('\n', '')
    tag = '"' + i[4] + '"'
    sql = "insert into Words values ({},'{}','{}',{},'{}','{}')".format(i[0], i[1], i[2], i[3], tag, i[5])
    cursor.execute(sql)
    # db.commit()
for i in l5:
    i = i.split('---')
    i[0] = int(i[0])
    i[3] = int(i[3])
    i[5] = i[5].replace('\n', '')
    tag = '"' + i[4] + '"'
    sql = "insert into Words values ({},'{}','{}',{},'{}','{}')".format(i[0], i[1], i[2], i[3], tag, i[5])
    cursor.execute(sql)
    # db.commit()

# sql = "truncate table Words"
# cursor.execute(sql)
db.commit()
db.close()
