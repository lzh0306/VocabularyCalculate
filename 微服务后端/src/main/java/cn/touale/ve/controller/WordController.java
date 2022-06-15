package cn.touale.ve.controller;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.preply.PreplyAnswer;
import cn.touale.ve.service.han.WordServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "han词汇量估计接口")
@RestController
@RequestMapping("/han/")
public class WordController {
    @Autowired
    private WordServer wordServer;

    @Operation(summary = "获取单词", description = "调用han接口，获取单词，返回不定量单词列表")
    @GetMapping("/getInitVe")
    public String getInitVe() {
        return wordServer.startTest();
    }

    @Operation(summary = "上传单词回答结果", description = "上传单词回答结果，返回初步判断估算量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hanAnswerList", value = "用户答题结果", dataTypeClass = PreplyAnswer.class,paramType = "query")})
    @PostMapping("/getResult")
    public String getResult(@RequestBody List<PreplyAnswer> preplyAnswerList) {
        return wordServer.outputResult(preplyAnswerList);
    }




}
