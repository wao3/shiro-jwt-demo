package com.github.wao3.shirojwtdemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult<T> success(T data) {
        this.code = 0;
        this.data = data;
        this.message = "请求成功";
        return this;
    }
}
