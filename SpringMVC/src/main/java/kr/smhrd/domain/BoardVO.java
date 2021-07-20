package kr.smhrd.domain;

import lombok.Data;

// 게시판 한개의 구조 (VO)
@Data
public class BoardVO {
	private int idx; 
	private String title;
	private String contents;
	private int count;
	private String writer;
	private String indate;
	// setter, getter, tostring()
}
