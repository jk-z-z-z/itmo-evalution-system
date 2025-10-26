package com.itmo.evaluationsystem.Model.vo;

import lombok.Data;

import java.util.List;
@Data
public class OpinionGetListVo {
    private List<String> CteacherList;
    private List<String> RteacherList;
    private List<OpinionVo>  OpinionList;
}
