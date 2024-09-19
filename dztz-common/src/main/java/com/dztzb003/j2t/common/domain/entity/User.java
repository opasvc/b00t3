package com.dztzb003.j2t.common.domain.entity;

import lombok.Data;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * user entity
 *
 * @author dztz
 * @date 2024/09/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;

    private String username;

    private String password;


}
