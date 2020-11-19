package com.yl.opts.db.community.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 社区动态
 * </p>
 *
 * @author ylyang
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("community_dynamic")
public class Dynamic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer merchantId;

    /**
     * 会员名称
     */
    private String merchantName;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类id 为0表示不属于任何模块
     */
    private Integer categoryId;

    /**
     * 内容
     */
    private String content;

    /**
     * 0只有文字，1图片，2视频
     */
    private Integer type;

    /**
     * 视频和图片的url
     */
    private String url;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 0未删除，1删除
     */
    private Integer isDelete;

    /**
     * 0不显示，1显示
     */
    private Integer isShow;

    /**
     * 点赞数
     */
    private Integer voteCount;

    /**
     * 分享数
     */
    private Integer shareCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 有效评论数
     */
    private Integer validCommentCount;

    /**
     * 真实点赞数
     */
    private Integer realVoteCount;

    /**
     * 真实分享数
     */
    private Integer realShareCount;


}
