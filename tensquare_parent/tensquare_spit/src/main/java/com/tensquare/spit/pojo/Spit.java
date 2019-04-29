package com.tensquare.spit.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 吐槽实体类
 */
public class Spit implements Serializable{

    @Id
    private String _id;

    private String content;//吐槽内容

    private Date publictime;//发布日期

    private String userid;//发布人id

    private String nickname;//昵称

    private Integer visits;//浏览量

    private Integer thumbup;//点赞数

    private Integer share;//分享数

    private Integer comment;//回复数

    private String state;//状态

    private String parentid;//父级id

    public Spit() {
    }

    public Spit(String content, Date publictime, String userid, String nickname, Integer visits, Integer thumbup, Integer share, Integer comment, String state, String parentid) {
        this.content = content;
        this.publictime = publictime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.thumbup = thumbup;
        this.share = share;
        this.comment = comment;
        this.state = state;
        this.parentid = parentid;
    }

    public String get_id() {
        return _id;
    }

    public String getContent() {
        return content;
    }

    public Date getPublictime() {
        return publictime;
    }

    public String getUserid() {
        return userid;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getVisits() {
        return visits;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public Integer getShare() {
        return share;
    }

    public Integer getComment() {
        return comment;
    }

    public String getState() {
        return state;
    }

    public String getParentid() {
        return parentid;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublictime(Date publictime) {
        this.publictime = publictime;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Spit{");
        sb.append("_id='").append(_id).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", publictime=").append(publictime);
        sb.append(", userid='").append(userid).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", visits=").append(visits);
        sb.append(", thumbup=").append(thumbup);
        sb.append(", share=").append(share);
        sb.append(", comment=").append(comment);
        sb.append(", state='").append(state).append('\'');
        sb.append(", parentid='").append(parentid).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
