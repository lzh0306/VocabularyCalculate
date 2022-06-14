import json

from Output_Test import con_db


class UserInformation:
    def __init__(self):
        self.db = con_db()
        self.cursor = self.db.cursor()

    def get_info_by_username(self, username):
        sql = "select * from Scoreboard where userName='{}'".format(username)
        self.cursor.execute(sql)
        result = self.cursor.fetchall()
        return result

    def update_scoreboard(self, js):
        results = self.get_info_by_username(js['userName'])
        # sql = ""
        print(results)
        if not results:
            print(1)
            sql = "insert into Scoreboard values ('{}',{},'{}',1)".format(js['userName'], js['score'], js['image'])
        else:
            print(2)
            username = js['userName']
            score = results[0][1] + js['score']
            image = js['image']
            battile_number = results[0][3] + 1
            sql = "update Scoreboard set score = {},image = '{}',battileNumber = {} where userName = '{}'" \
                .format(score, image, battile_number, username)
        self.cursor.execute(sql)
        self.db.commit()
        return 1

    def db_close(self):
        self.db.close()
        return 1


# upc = UpdateCredit()
# results = upc.get_info_by_username('h')
# print(results)
# if not results:
#     print(1)
# if results == '':
#     print(2)
# for row in results:
#     print(row)
'''下面测试数据'''
# json_score = {"userName": "aaa",
#               "score": 10,
#               "image": "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLLpEeviacHgYZS6ZVwsPWCyuaHgPgnEGAibekMgXgUiaMQczfVPuw0icU9VgSZ9vm946tQ3p49UyaIYA/132",
#               }
# upc.update_scoreboard(json_score)
# upc.db_close()
# {"userName": "aaa"}
