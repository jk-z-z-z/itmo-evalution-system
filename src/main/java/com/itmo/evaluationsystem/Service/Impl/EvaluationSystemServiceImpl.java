package com.itmo.evaluationsystem.Service.Impl;

import com.itmo.evaluationsystem.Mapper.EvaluationSystemBodyMapper;
import com.itmo.evaluationsystem.Mapper.EvaluationSystemHeadMapper;
import com.itmo.evaluationsystem.Model.Entity.EvaluationSystemBody;
import com.itmo.evaluationsystem.Model.Entity.EvaluationSystemHead;
import com.itmo.evaluationsystem.Model.dto.evaluationsystem.EvaluationSystemAddRequest;
import com.itmo.evaluationsystem.Model.dto.evaluationsystem.EvaluationSystemBodyUpdateRequest;
import com.itmo.evaluationsystem.Model.dto.evaluationsystem.EvaluationSystemHeadUpdateRequest;
import com.itmo.evaluationsystem.Model.vo.EvaluationSystemHeadVo;
import com.itmo.evaluationsystem.Service.EvaluationSystemService;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class EvaluationSystemServiceImpl implements EvaluationSystemService {
    @Autowired
    private EvaluationSystemHeadMapper evaluationSystemHeadMapper;
    @Autowired
    private EvaluationSystemBodyMapper evaluationSystemBodyMapper;

    @Override
    public List<EvaluationSystemHeadVo> get(Integer party) {

        List<EvaluationSystemHead> headList=evaluationSystemHeadMapper.getHeadList(party);

        List<EvaluationSystemHeadVo> headVoList=new ArrayList<>();
        for(EvaluationSystemHead head:headList){
            EvaluationSystemHeadVo headVo=new EvaluationSystemHeadVo();
            headVo.setId(head.getId());
            headVo.setHeadName(head.getHeadName());
            headVo.setProportion(head.getProportion());
            headVo.setParty(head.getParty());
            headVo.setEvaluationSystemBodyVoList(evaluationSystemBodyMapper.getBodyList(head.getId()));
            headVoList.add(headVo);
        }

        return headVoList;
    }

    @Override
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void add(@RequestBody EvaluationSystemAddRequest request) {

        EvaluationSystemHead head=new EvaluationSystemHead();
        head.setHeadName(request.getHeadName());
        head.setProportion(request.getProportion());
        head.setParty(request.getParty());
        evaluationSystemHeadMapper.add(head);

        for(EvaluationSystemBody body:request.getEvaluationSystemBodyList()){
            body.setHeadId(head.getId());
            evaluationSystemBodyMapper.add(body);
        }

    }

    @Override
    public void deleteHead(Integer headId) {
        evaluationSystemHeadMapper.delete(headId);
        evaluationSystemBodyMapper.delete(headId);
    }

    @Override
    public void deleteBody(Integer bodyId) {
        evaluationSystemBodyMapper.delete(bodyId);
    }

    @Override
    public void updateHead(EvaluationSystemHeadUpdateRequest request) {
        evaluationSystemHeadMapper.update(request);
    }

    @Override
    public void updateBody(EvaluationSystemBodyUpdateRequest request) {
        evaluationSystemBodyMapper.update(request);
    }
}
