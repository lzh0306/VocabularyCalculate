package cn.touale.ve.controller;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.PreplyAnswer;
import cn.touale.ve.service.PreplyServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Touale
 * @description PreplyController
 * @date 2022/6/11 15:23
 */
@Api(tags = "第三方preply接口")
@RestController
@RequestMapping("/preply/")
public class PreplyController {
    @Autowired
    private PreplyServer preplyServer;

    @Operation(summary = "获取单词", description = "调用第三方接口，获取单词，返回不定量单词列表")
    @GetMapping("/getInitVe")
    public ResultDTO getInitVe() {
        return preplyServer.getInitVe();
    }

    @Operation(summary = "上传单词回答结果", description = "上传单词回答结果，返回初步判断估算量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "preplyAnswerList", value = "用户答题结果", dataTypeClass = PreplyAnswer.class,paramType = "query")})
    @PostMapping("/getResult")
    public ResultDTO getResult(@RequestBody List<PreplyAnswer> preplyAnswerList) {
        return preplyServer.getResult(preplyAnswerList);
    }

    @Operation(summary = "获取二级单词列表", description = "获取二级单词列表，提高结果准确率")
    @PostMapping("/getSecondVe")
    public ResultDTO getSecondVe(Integer midpoint){
        return preplyServer.getSecondVe (midpoint);
    }
}
