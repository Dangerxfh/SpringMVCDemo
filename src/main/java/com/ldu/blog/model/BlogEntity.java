package com.ldu.blog.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="blog",schema="springdemo",catalog="")
public class BlogEntity  {
	private int id;
	private String title;
	private String content;
	private Date pubDate;
	private UserEntity userByUserId;
	
	@Id
	@Column(name="id",nullable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
	@Column(name="title",nullable=false,length=100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Basic
	@Column(name="content",nullable=false,length=255)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Basic
	@Column(name="pub_date",nullable=false)
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="id",nullable=false)
	public UserEntity getUserByUserId() {
		return userByUserId;
	}
	public void setUserByUserId(UserEntity userByUserId) {
		this.userByUserId = userByUserId;
	}
	//判断两个blog实体类是否相同
	@Override
	public boolean equals(Object obj){
		if(this==obj)
			return true;
		if(obj==null || getClass()!=obj.getClass())
			return false;
		BlogEntity that = (BlogEntity) obj;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null) return false;

        return true;
	} 
	
	@Override
	public int hashCode(){
		int result=id;
		 result = 31 * result + (title != null ? title.hashCode() : 0);
	     result = 31 * result + (content != null ? content.hashCode() : 0);
	     result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
	     return result;
	}	
}
