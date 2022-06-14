package cn.touale.ve.mapper;

import cn.touale.ve.entity.battle.Question;
import cn.touale.ve.entity.rankings.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VeMapper {

    List<Question> getQuestionList(@Param("size") Integer size);

    List<Rank> getRankings(@Param("size") Integer size);
}
