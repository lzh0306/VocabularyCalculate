package cn.touale.ve.controller;

import cn.touale.ve.service.battle.BattleServer;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Touale
 * @description testController
 * @date 2022/6/11 12:33
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/test/")
public class TestController {


    @Operation(summary = "测试", description = "返回1")
    @GetMapping("/get0")
    public String getNodeInfoList() {
        return "1";
    }

}
