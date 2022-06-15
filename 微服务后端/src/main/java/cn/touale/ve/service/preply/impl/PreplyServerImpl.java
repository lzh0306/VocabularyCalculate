package cn.touale.ve.service.preply.impl;

import cn.touale.ve.constant.enumeration.ResultCode;
import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.preply.PreplyAnswer;
import cn.touale.ve.entity.preply.PreplyWorld;
import cn.touale.ve.service.preply.PreplyServer;
import cn.touale.ve.utils.DataDeal;
import cn.touale.ve.utils.OkHttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Touale
 * @description PreplyServerImpl
 * @date 2022/6/11 15:28
 */
@Service
public class PreplyServerImpl implements PreplyServer {
    private static final String initUrl = "https://preply.com/en/learn/english/test-your-vocab";
    public static final String resUrl = "https://preply.com/graphql/";
    public static final String query = "query TestYourVocabCalculateMidpoint($answers: [TestYourVocabAnswerInput!]) {testyourvocabCalculateMidpoint(answers: $answers)}";


    @Override
    public ResultDTO getInitVe() {
        ResultDTO resultDTO = new ResultDTO();
        String res;

        try {
            res = OkHttpUtil.get(initUrl);
            res = DataDeal.getSubString(res, "currentUser\":null,\"words\":", ",\"seoData\"");
            res = "{\"data\": " + res + "}";
        } catch (Exception e) {
            e.printStackTrace();
            return resultDTO.buildFail(ResultCode.ERROR, "加载失败");
        }

        List<PreplyWorld> words = new ArrayList<>();
        try {
            JSONObject jobj = JSON.parseObject(res);
            JSONArray jsonarr = jobj.getJSONArray("data");
            for (int i = 0; i < jsonarr.size(); i++) {
                JSONObject jsonb = jsonarr.getJSONObject(i);
                PreplyWorld tempWorld = new PreplyWorld();
                tempWorld.setId(jsonb.getInteger("word_id"));
                tempWorld.setWord(jsonb.getString("value"));
                words.add(tempWorld);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultDTO.buildError(ResultCode.ERROR, "解析失败");
        }

        return resultDTO.buildSucc("调用成功", words);
    }

    @Override
    public ResultDTO getResult(List<PreplyAnswer> preplyAnswerList) {
        ResultDTO resultDTO = new ResultDTO();
        String res;
        Integer testyourvocabCalculateMidpoint;

        try {
            JSONObject temp = new JSONObject();
            temp.put("answers", JSON.toJSON(preplyAnswerList));
            JSONObject jobj = new JSONObject();
            jobj.put("operationName", "TestYourVocabCalculateMidpoint");
            jobj.put("query", query);
            jobj.put("variables", temp);
            res = jobj.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
            return resultDTO.buildFail(ResultCode.ERROR, "解析失败");
        }

        try {
            res = OkHttpUtil.postJsonParams(resUrl, res, "");
        } catch (Exception e) {
            e.printStackTrace();
            return resultDTO.buildFail(ResultCode.ERROR, "加载失败");
        }

        try {
            JSONObject jobj = JSON.parseObject(res);
            testyourvocabCalculateMidpoint = jobj.getJSONObject("data").getInteger("testyourvocabCalculateMidpoint");
        } catch (Exception e) {
            e.printStackTrace();
            return resultDTO.buildFail(ResultCode.ERROR, "处理数据异常");
        }

        return resultDTO.buildSucc("调用成功", testyourvocabCalculateMidpoint);
    }

    @Override
    public ResultDTO getSecondVe(Integer midpoint) {
        ResultDTO resultDTO = new ResultDTO();
        String res = "{\"variables\":{\"midpoint\":" + midpoint + "},\"operationName\":\"TestYourVocabStepTwoWords\",\"query\":\"\\n    query TestYourVocabStepTwoWords($midpoint: Int!) {\\n        testyourvocabStepTwoWords(midpoint: $midpoint) {\\n            value\\n            word_id\\n        }\\n    }\\n\"}";
        try {
            res = OkHttpUtil.postJsonParams(resUrl, res, "");
        } catch (Exception e) {
            e.printStackTrace();
            return resultDTO.buildFail(ResultCode.ERROR, "加载失败");
        }

        List<PreplyWorld> words = new ArrayList<>();
        try {
            JSONObject jobj = JSON.parseObject(res);
            JSONArray jsonarr = jobj.getJSONObject("data").getJSONArray("testyourvocabStepTwoWords");
            for (int i = 0; i < jsonarr.size(); i++) {
                JSONObject jsonb = jsonarr.getJSONObject(i);
                PreplyWorld tempWorld = new PreplyWorld();
                tempWorld.setId(jsonb.getInteger("word_id"));
                tempWorld.setWord(jsonb.getString("value"));
                words.add(tempWorld);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultDTO.buildError(ResultCode.ERROR, "解析失败");
        }

        return resultDTO.buildSucc("调用成功", words);
    }
}
