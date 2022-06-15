package cn.touale.ve.service.han;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.preply.PreplyAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WordServer {
    public String startTest();

    public String outputResult(List<PreplyAnswer> preplyAnswerList);
}
