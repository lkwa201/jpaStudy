package com.example.testdemo.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Board {

	public Board() {
	}

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String writer;
	private String content;

	@CreationTimestamp
	private Date createDateTime = new Date();

	@UpdateTimestamp
	private Date updateDateTime =  new Date();

	private Long cnt = 0L;




	@Builder
	public Board(Long id, String title, String writer, String content) {
		this.id = id;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.createDateTime = this.getCreateDateTime();
		this.updateDateTime = this.getUpdateDateTime();
		this.cnt = this.getCnt();
	}
}
