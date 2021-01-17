package com.littercarp.servicebase.config.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  // ：有参构造方法
@NoArgsConstructor   // ：无参构造方法
public class LitterCarpException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
