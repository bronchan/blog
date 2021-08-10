package com.chan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author bronchan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;

}
