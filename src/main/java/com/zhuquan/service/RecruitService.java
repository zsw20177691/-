package com.zhuquan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuquan.domain.*;
import com.zhuquan.mapper.CompanyInfoMapper;
import com.zhuquan.mapper.RecruitInfoMapper;
import com.zhuquan.mapper.UserMapper;
import com.zhuquan.util.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecruitService {

    @Resource
    private RecruitInfoMapper recruitInfoMapper;
    @Resource
    private CompanyInfoMapper companyInfoMapper;
    @Resource
    private UserMapper userMapper;

    @Transactional
    public void addRecruit(RecruitInfo recruitInfo){
        recruitInfoMapper.insert(recruitInfo);
    }

    public PageResult<RecruitInfo>  findRecruitByPage(RecruitInfo recruitInfo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);

        // 筛选条件
        Example example = new Example(RecruitInfo.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(recruitInfo.getPositionName())){
            criteria.andLike("positionName","%"+recruitInfo.getPositionName()+"%");
        }
        if (StringUtils.isNotBlank(recruitInfo.getPosition())){
            criteria.andEqualTo("position", recruitInfo.getPosition());
        }
        if (recruitInfo.getCompanyId() != null){
            criteria.andEqualTo("companyId", recruitInfo.getCompanyId());
        }
        example.setOrderByClause("create_date desc");

        // 查询返回
        List<RecruitInfo> select = recruitInfoMapper.selectByExample(example);
        PageInfo<RecruitInfo> pageInfo = new PageInfo<>(select);
        return  new PageResult(pageInfo.getTotal(), select);


    }

    public void editRecruit(RecruitInfo recruitInfo) {
        Example example = new Example(RecruitInfo.class);
        example.createCriteria()
                .andEqualTo("id",recruitInfo.getId())
                .andEqualTo("companyId",recruitInfo.getCompanyId());

        recruitInfoMapper.updateByExampleSelective(recruitInfo, example);
    }

    public PageResult<RecruitVO> findRecruitInfoByPage(RecruitRequest recruitRequest, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<RecruitVO> recruits = recruitInfoMapper.findRecruitInfoByPage(recruitRequest);

        PageInfo<RecruitVO> pageInfo = new PageInfo<>(recruits);
        return  new PageResult(pageInfo.getTotal(), recruits);
    }

    public RecruitInfoVO findRecruitInfo(Integer id) {

        RecruitInfo recruitInfo = recruitInfoMapper.selectByPrimaryKey(id);
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(recruitInfo.getCompanyId());

        RecruitInfoVO recruitInfoVO = new RecruitInfoVO();
        recruitInfoVO.setRecruit(recruitInfo);
        System.out.println(recruitInfo);
        recruitInfoVO.setCompany(companyInfo);

        return recruitInfoVO;

    }
}
