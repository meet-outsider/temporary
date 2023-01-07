package com.outsider.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @TableName user
 */
@TableName(value ="user")
@Accessors(chain = true)
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private boolean deleted;

    private static final long serialVersionUID = 1L;
}
