package cn.touale.ve.controller;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.battle.Question;
import cn.touale.ve.service.battle.BattleServer;
import cn.touale.ve.utils.GameWebSocketUtils;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Touale
 * @description WsInfoController
 * @date 2022/6/13 19:44
 */
@Component
@Api(tags = "多人匹配接口")
@RestController
@Slf4j
@RequestMapping("/pk/")
public class WsInfoController {
    @Autowired
    private GameWebSocketUtils gameWebSocketUtils;

    @Autowired
    private BattleServer battleServer;


    @Operation(summary = "获取所有房间信息", description = "获取房间信息")
    @GetMapping("/getAllRoom")
    public ResultDTO getAllRoom() {
        ResultDTO res = new ResultDTO();

        return res.buildSucc(GameWebSocketUtils.getAllRoom());
    }

    @Operation(summary = "获取所有玩家信息", description = "获取玩家信息")
    @GetMapping("/getAllPlayers")
    public ResultDTO getAllPlayers() {
        ResultDTO res = new ResultDTO();
        return res.buildSucc(gameWebSocketUtils.getAllPlayers());
    }

    @Operation(summary = "获取随机题目", description = "返回随机题目")
    @GetMapping("/getQuestionList")
    public List<Question> getQuestionList(@RequestParam(name = "size") Integer size) {
        return battleServer.getQuestionsList(size);
    }

    @Operation(summary = "移除用户", description = "移除用户")
    @GetMapping("/removeUser")
    public ResultDTO removeUser(@RequestParam(name = "userId") Integer userId) {
        ResultDTO res = new ResultDTO();
        log.info("移除用户" + userId);
        try {
            gameWebSocketUtils.runWay(userId);
            return res.buildSucc("操作成功");
        } catch (Exception e) {
            return res.buildFail("操作失败");
        }

    }

}
