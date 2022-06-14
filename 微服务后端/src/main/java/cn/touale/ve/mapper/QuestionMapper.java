package cn.touale.ve.mapper;

import cn.touale.ve.entity.battle.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper {
    List<Question> getQuestionList(@Param("size") Integer size);
}
