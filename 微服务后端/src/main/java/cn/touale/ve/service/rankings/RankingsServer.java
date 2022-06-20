package cn.touale.ve.service.rankings;

import cn.touale.ve.config.ResultDTO;
import cn.touale.ve.entity.rankings.Rank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingsServer {
    List<Rank> getRankings(Integer size);

    List<Rank> getOneRanking(String userName);

    boolean insertRanking(String userName, Integer score, String image);

    boolean updateRanking(String userName, Integer score,String image);

    ResultDTO updateRank(String userName, Integer score, String image);

}
