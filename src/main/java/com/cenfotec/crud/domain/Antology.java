package com.cenfotec.crud.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Antology {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;	
	
	private String name;
	private String description;


	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date created;


	@OneToMany(fetch=FetchType.LAZY, mappedBy="anthology")
	private Set<Article> articles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created){
		this.created = created;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
	
}
