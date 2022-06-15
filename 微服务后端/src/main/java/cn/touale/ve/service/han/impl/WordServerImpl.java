package cn.touale.ve.service.han.impl;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.constant.enumeration.ResultCode;
import cn.touale.ve.entity.preply.PreplyAnswer;
import cn.touale.ve.service.han.WordServer;
import cn.touale.ve.utils.OkHttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServerImpl implements WordServer {

    public static final String startUrl = "https://python-2007965-1312437268.ap-shanghai.run.tcloudbase.com/startTest";
    public static final String outputUrl = "https://python-2007965-1312437268.ap-shanghai.run.tcloudbase.com/outputResult";

    @Override
    public String startTest() {
        ResultDTO resultDTO = new ResultDTO();
        String res;
        try{
            res = OkHttpUtil.postJsonParams(startUrl,"","");
        }catch(Exception e){
            e.printStackTrace();
            return resultDTO.buildFail(ResultCode.ERROR, "调用失败").toJsonString();
        }
        return  res;
    }

    @Override
    public String outputResult(List<PreplyAnswer> preplyAnswerList) {
        ResultDTO resultDTO = new ResultDTO();
        String res = JSON.toJSONString(preplyAnswerList);
        try{
            res = OkHttpUtil.postJsonParams(outputUrl,res,"");
        }catch(Exception e){
            e.printStackTrace();
            return resultDTO.buildFail(ResultCode.ERROR, "调用失败").toJsonString();
        }
        return  res;
    }
}
