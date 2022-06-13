package cn.touale.ve.service.battle.impl;

import cn.touale.ve.entity.battle.Question;
import cn.touale.ve.service.battle.BattleServer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Touale
 * @description BattleServerImpl
 * @date 2022/6/13 14:01
 */
@Service
public class BattleServerImpl implements BattleServer {
    @Override
    public List<Question> getQuestionsList(){
        List<Question> questionList = new ArrayList<>();
        for(int i = 0;i<3;i++){
            Question temp = new Question()
                    .setWordId(1)
                    .setQuestion("touale")
                    .setA("人")
                    .setB("haha1")
                    .setC("ad噢噢噢噢")
                    .setD("名字")
                    .setAnswer(3);
            questionList.add(temp);
        }
        return questionList;
    }
}
