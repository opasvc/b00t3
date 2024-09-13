package com.dztzb003.j2t.common.result;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * response result
 *
 * @author dztz
 * @date 2024/09/13
 */

@Data
public class R<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSS")
    private LocalDateTime localTime;

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.code = 500;
        r.msg = msg;
        r.localTime = LocalDateTime.now();
        return r;
    }

    public static <T> R<T> error(Integer code, String msg) {
        R r = new R();
        r.code = code;
        r.msg = msg;
        r.localTime = LocalDateTime.now();
        return r;
    }

    public static <T> R<T> success() {
        R r = new R();
        r.code = 200;
        r.localTime = LocalDateTime.now();
        return r;
    }

    public static <T> R<T> success(T data) {
        R r = new R();
        r.code = 200;
        r.data = data;
        r.localTime = LocalDateTime.now();
        return r;
    }

}
