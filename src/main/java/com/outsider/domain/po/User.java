package com.outsider.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.outsider.annotation.Resolve;
import com.outsider.enums.ResolveType;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @TableName user
 */
@TableName(value = "user")
@Accessors(chain = true)
@Data
@Resolve(type = ResolveType.ACCESS)
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Resolve(type = ResolveType.EDIT)
    private String name;

    @Resolve(type = ResolveType.ACCESS)
    private Integer age;

    @JsonIgnore
    private boolean deleted;

    @Serial
    private static final long serialVersionUID = 1L;
}
