package com.itmo.evaluationsystem.Service.Impl;

import com.itmo.evaluationsystem.Mapper.OpinionMapper;
import com.itmo.evaluationsystem.Mapper.TeacherMapper;
import com.itmo.evaluationsystem.Model.dto.opinion.OpinionAddRequest;
import com.itmo.evaluationsystem.Model.vo.OpinionGetListVo;
import com.itmo.evaluationsystem.Model.vo.OpinionVo;
import com.itmo.evaluationsystem.Service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionMapper opinionMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void add(OpinionAddRequest request) {
        opinionMapper.add(request);

    }

    @Override
    public OpinionGetListVo getList() {
        List<String> CteacherList=teacherMapper.getListByParty(0);
        List<String> RteacherList=teacherMapper.getListByParty(1);
        List<OpinionVo> opinionList=opinionMapper.getList();

        OpinionGetListVo data=new OpinionGetListVo();
        data.setCteacherList(CteacherList);
        data.setRteacherList(RteacherList);
        data.setOpinionList(opinionList);
        return data;
    }
}
