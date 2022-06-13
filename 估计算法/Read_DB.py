import pymysql


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


# print(load_db())
