package gdut.timer.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author ghy
 * @Description:
 * @date 2017年11月24日 上午11:11:03 
 *
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7418941579246011923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "username",length = 18)
	private String username;
	
	@Column(name = "password",length = 32)
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "banlance")
	private long banlance;	//余额
	
	public User(){}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public long getBanlance() {
		return banlance;
	}


	public void setBanlance(long banlance) {
		this.banlance = banlance;
	}
	
	

}


