package cn.touale.ve.service.rankings.impl;

import cn.touale.ve.entity.rankings.Rank;
import cn.touale.ve.mapper.VeMapper;
import cn.touale.ve.service.rankings.RankingsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingsServerImpl implements RankingsServer {
    @Autowired
    private VeMapper veMapper;

    @Override
    public List<Rank> getRankings(Integer size) {
        return veMapper.getRankings(size);
    }
}
