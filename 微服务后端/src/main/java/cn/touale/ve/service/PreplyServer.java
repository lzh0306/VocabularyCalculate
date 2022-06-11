package cn.touale.ve.service;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.PreplyAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Touale
 * @description PreplyServer
 * @date 2022/6/11 15:23
 */
@Service
public interface PreplyServer {
    ResultDTO getInitVe();

    ResultDTO getResult(List<PreplyAnswer> preplyAnswerList);

    ResultDTO getSecondVe(Integer midpoint);
}
