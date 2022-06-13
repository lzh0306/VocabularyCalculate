import json

from flask import Flask
import os
from flask import request

from Output_Result_class import OutputResult
from Output_Test_class import OutputTest

app = Flask(__name__)
output_test = OutputTest()
output_result = OutputResult()


@app.route('/')
def hello_world():
    return 'Hello World'


# post连接，返回测试用json数据
@app.route('/startTest', methods=['POST'])
def start_test():
    # output_test = OutputTest()
    output_test.update_test_json_level()
    jl = output_test.get_jl()
    return jl


# post json数据，返回词汇量估算结果
@app.route('/outputResult', methods=['POST'])
def output_quantity():
    # output_result = OutputResult()
    json_post = request.get_json()
    # json_post = json.dumps(json_post)
    result = output_result.return_quantity(json_post)
    result = int(result / 1)
    return_result = {'code': 1, 'msg': 'success', 'data': result}
    return_result = json.dumps(return_result)
    return return_result


app.run(host='0.0.0.0', debug=True,
        port=int(os.environ.get('PORT', 81)), use_reloader=False)
