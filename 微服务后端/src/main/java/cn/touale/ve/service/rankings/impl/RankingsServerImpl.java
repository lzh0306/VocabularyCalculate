package cn.touale.ve.service.rankings.impl;

import cn.touale.ve.config.ResultDTO;
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

    @Override
    public List<Rank> getOneRanking(String userName) {
        return veMapper.getOneRanking(userName);
    }

    @Override
    public boolean insertRanking(String userName, Integer score, String image) {
        return veMapper.insertRanking(userName, score, image);
    }

    @Override
    public boolean updateRanking(String userName, Integer score) {
        return veMapper.updateRanking(userName, score);
    }


    @Override
    public ResultDTO updateRank(String userName, Integer score, String image) {
        ResultDTO resultDTO = new ResultDTO();
        List<Rank> temp = getOneRanking(userName);
        try {
            if (temp.size() > 0) {
                updateRanking(userName, score);
            } else {
                insertRanking(userName, score, image);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resultDTO.buildFail("调用失败");
        }
        return resultDTO.buildSucc("调用成功");
    }

}
