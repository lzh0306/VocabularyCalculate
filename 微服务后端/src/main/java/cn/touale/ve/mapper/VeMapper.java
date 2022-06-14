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
    //根据userName查询积分表
    List<Rank> getOneRanking(@Param("userName") String userName);
    //向积分表插入一条数据
    boolean insertRanking(@Param("userName") String userName,@Param("score") Integer score,@Param("image") String image);
    //更新积分表数据
    boolean updateRanking(@Param("userName") String userName,@Param("score") Integer score);

}
