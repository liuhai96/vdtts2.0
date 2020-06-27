package com.lsjbc.vdtts.dao;

import com.lsjbc.vdtts.dao.mapper.ExamParamMapper;
import com.lsjbc.vdtts.entity.ExamParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Repository
//public class ExamParamDao implements BaseDao<ExamParam> {
public class ExamParamDao {

    @Resource
    ExamParamMapper examParamMapper;

    public List<ExamParam> selectParamInfo(String pmKey) {
        Example example = new Example(ExamParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("pmKey","%"+pmKey+"%");
        return examParamMapper.selectByExample(example);
    }

    public ExamParam getById(Integer id) {
        return null;
    }


    public Integer add(ExamParam object) {
        return null;
    }


    public Integer updateById(ExamParam examParam) {
         return examParamMapper.updateByPrimaryKeySelective(examParam);
    }

    public Integer deleteById(Integer id) {
        return null;
    }

   public  ExamParam selectTime(String pmKey){
        Example example = new Example(ExamParam.class);
        example.selectProperties("pmValue");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pmKey",pmKey);
        return (ExamParam) examParamMapper.selectOneByExample(example);
   }
}
