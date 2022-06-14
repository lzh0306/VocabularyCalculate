package cn.touale.ve.controller;

import cn.touale.ve.entity.battle.Question;
import cn.touale.ve.service.battle.BattleServer;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Touale
 * @description testController
 * @date 2022/6/11 12:33
 */
@Api(tags = "接口测试")
@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    private BattleServer battleServer;

    @Operation(summary = "测试", description = "返回1")
    @GetMapping("/get0")
    public String getNodeInfoList() {
        return "0";
    }

    // Todo 1.随机获取10个单词（10为参数）
    @Operation(summary = "获取题目",description = "调用第三方接口，返回随机10条数据")
    @GetMapping("/getQuestionList")
    public List<Question> getQuestionList(@RequestParam(name = "size") Integer size) {
        return battleServer.getQuestionsList(size);
    }


}
