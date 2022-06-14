import json

from flask import Flask
import os
from flask import request

from Output_Result_class import OutputResult
from Output_Test_class import OutputTest
from Update_Credit_class import UserInformation

app = Flask(__name__)
output_test = OutputTest()
output_result = OutputResult()
userInfo = UserInformation()


@app.route('/')
def hello_world():
    return 'Hello World'


# post连接，返回测试用json数据
@app.route('/startTest', methods=['POST'])
def start_test():
    # output_test = OutputTest()
    # output_test.update_test_json_level()
    jl = output_test.get_jl([8, 8, 8, 8, 8])
    return jl


# post json数据，返回词汇量估算结果
'''
{"level":1, "data":[{"known":bool, "wordID": int}]}
{"level":2, "data":[{"known":bool, "wordID": int}]}
'''


@app.route('/outputResult', methods=['POST'])
def output_quantity():
    # output_result = OutputResult()
    json_post = request.get_json()
    if len(json_post) == 40:
        # output_result.return_second_test_quantity_list(json_post)
        quantity_list = output_result.return_second_test_quantity_list(json_post)
        jl = output_test.get_jl(quantity_list)
        return jl
    # json_post = json.dumps(json_post)
    elif len(json_post) == 80:
        result = output_result.return_quantity(json_post)
        result = int(result / 1)
        return_result = {'code': 1, 'msg': 'success', 'data': result}
        return_result = json.dumps(return_result)
        return return_result
    else:
        return 0


# 更新积分表 {"userName": name, "score": 10, "image": image}
@app.route('/updateScoreboard', methods=['POST'])
def update_scoreboard():
    json_post = request.get_json()
    result = userInfo.update_scoreboard(json_post)
    if result == 1:
        return_result = {'code': 1, 'msg': 'success'}
    else:
        return_result = {'code': 1, 'msg': 'failure'}
    return_result = json.dumps(return_result)
    return return_result


# 获取个人信息 {"userName": name}
@app.route('/getUserInfo', methods=['POST'])
def get_user_info():
    json_post = request.get_json()
    result = userInfo.get_info_by_username(json_post["userName"])
    username = result[0][0]
    score = result[0][1]
    battile_number = result[0][3]
    return_result = {'userName': username, 'userScore': score, 'battileNumber': battile_number}
    return_result = json.dumps(return_result)
    return return_result


app.run(host='0.0.0.0', debug=True,
        port=int(os.environ.get('PORT', 81)), use_reloader=False)
