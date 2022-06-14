package cn.touale.ve.controller;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.rankings.Rank;
import cn.touale.ve.service.rankings.RankingsServer;
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

@Component
@Api(tags = "积分排行榜接口")
@RestController
@Slf4j
@RequestMapping("/rankingList")
public class RankController {
    @Autowired
    private RankingsServer rankingsServer;

    @Operation(summary = "获取score前20",description = "返回积分前20玩家信息")
    @GetMapping("/getRankings")
    public List<Rank> getRankings(@RequestParam(name = "size") Integer size) {
        return rankingsServer.getRankings(size);
    }

    @Operation(summary = "更新排行榜积分",description = "更新Scoreboard数据")
    @GetMapping("/updateRank")
    public ResultDTO updateRank(@RequestParam(name = "userName") String userName, @RequestParam(name = "score") Integer score, @RequestParam(name = "image") String image) {
        return rankingsServer.updateRank(userName,score,image);
    }


}
