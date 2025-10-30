package com.itmo.evaluationSystem.model.vo;

import lombok.Data;

import java.util.List;
@Data
public class OpinionGetListVo {
    private List<String> CteacherList;
    private List<String> RteacherList;
    private List<OpinionVo>  OpinionList;
}
