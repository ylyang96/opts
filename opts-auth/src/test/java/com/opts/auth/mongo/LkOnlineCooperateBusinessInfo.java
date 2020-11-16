package com.opts.auth.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author ylyang
 */
@Data
@Document(collection = "lk_online_cooperate_business_info2")
public class LkOnlineCooperateBusinessInfo {

    @Id
    private String id;

    private Integer merchantId;

    private String businessName;

    private String businessAddress;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 0男生，1女生
     */
    private Integer sex;

    private String businessPhone;

    private String qrCodeUrl;

    private Integer finishNum;

    private Integer finishSuccessNum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer businessMerchantId;


}
