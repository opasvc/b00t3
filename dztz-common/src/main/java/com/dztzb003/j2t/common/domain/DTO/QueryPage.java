package com.dztzb003.j2t.common.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 分页参数
 *
 * @author j2t
 * @date 2024/09/23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryPage {


    /**
     * 默认页数
     */
    private Integer pageNum = 1;


    /**
     * 每页条数
     */
    private Integer pageSize = 10;


}
