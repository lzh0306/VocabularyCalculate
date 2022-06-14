package cn.touale.ve.service.rankings;

import cn.touale.ve.entity.rankings.Rank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingsServer {
    List<Rank> getRankings(Integer size);
}
