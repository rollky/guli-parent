package com.atguigu.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName TeacherQuery
 * @Description 查询条件
 * @Author zb
 * @Date 2021/1/18 22:33
 **/
@Data
@ApiModel(value="Teacher查询条件对象", description="讲师查询条件")
public class TeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师级别")
    private Integer level;

    @ApiModelProperty(value = "讲师入驻开始时间")
    private Date begin;

    @ApiModelProperty(value = "讲师入驻结束时间")
    private Date end;

    private long current;
    private long limit;
}
