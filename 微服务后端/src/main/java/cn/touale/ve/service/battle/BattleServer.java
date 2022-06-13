package cn.touale.ve.service.battle;

import cn.touale.ve.entity.battle.Question;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Touale
 * @description BattleServer
 * @date 2022/6/13 14:00
 */
@Service
public interface BattleServer {
    public List<Question> getQuestionsList();

}
