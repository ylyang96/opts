package com.yl.opts.online.form;

import com.yl.opts.base.KeyValueParam;
import jdk.nashorn.internal.runtime.options.KeyValueOption;
import lombok.Data;

import java.util.List;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
@Data
public class OptsOnlineTaskOrderSubmitForm {

    private Integer taskId;

    private List<KeyValueParam> list;


}
