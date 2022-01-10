package com.zhuquan.mapper;

import com.zhuquan.domain.RecruitInfo;
import com.zhuquan.domain.RecruitInfoVO;
import com.zhuquan.domain.RecruitRequest;
import com.zhuquan.domain.RecruitVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RecruitInfoMapper extends Mapper<RecruitInfo> {

    List<RecruitVO> findRecruitInfoByPage(RecruitRequest recruitRequest);

    // RecruitInfoVO findRecruitInfoByKey(RecruitRequest recruitRequest);

}
