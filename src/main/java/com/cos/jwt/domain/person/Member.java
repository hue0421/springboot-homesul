package com.cos.jwt.domain.person;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cos.jwt.domain.post.Board;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String membername;
	private String password;
	private String email;

	// 나는 연관관계의 주인이 아니야!!(나는 FK를 가진 아이가 아니니까 DB만들지마)
	@JsonIgnoreProperties({ "member" }) // 중괄호 안에 무시하고싶은 변수"member"
	@OneToMany(mappedBy = "member") // Board 오브젝트의 member변수를 걸어야함.
	private List<Board> Boards;
}
