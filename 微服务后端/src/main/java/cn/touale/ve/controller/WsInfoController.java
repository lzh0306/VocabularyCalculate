package cn.touale.ve.controller;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.utils.GameWebSocketUtils;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Touale
 * @description WsInfoController
 * @date 2022/6/13 19:44
 */
@Component
@Api(tags = "多人匹配接口")
@RestController
@RequestMapping("/pk/")
public class WsInfoController {
    @Autowired
    private GameWebSocketUtils gameWebSocketUtils;


    @Operation(summary = "获取房间信息", description = "获取房间信息")
    @GetMapping("/getAllRoom")
    public ResultDTO getAllRoom() {
        ResultDTO res = new ResultDTO();

        return res.buildSucc(GameWebSocketUtils.getAllRoom());
    }

    @Operation(summary = "获取玩家信息", description = "获取玩家信息")
    @GetMapping("/getAllPlayers")
    public ResultDTO getAllPlayers() {
        ResultDTO res = new ResultDTO();
        return res.buildSucc(gameWebSocketUtils.getAllPlayers());
    }
}
