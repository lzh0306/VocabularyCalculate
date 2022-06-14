package cn.touale.ve.entity.rankings;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Rank {
    private String userName;
    private Integer score;
    private String image;
    private Integer battileNumber;
}
