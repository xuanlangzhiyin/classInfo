package com.wuhei.cms.login;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import com.wuhei.cms.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录用户信息实体类，实现Spring Security中的...接口
 * 
 * 
 */
public class LoginUser extends User implements UserDetails, Serializable {

	private Set<GrantedAuthority> authorities;

	/**
	 * 登录用户为教务员和教师：人事编号
	 */
	private String teachercode;

	/**
	 * 登录用户为学生：学号
	 */
	private String studentcode;

	/*
	 * UserDetails 接口
	 */
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getUsername() {
		return super.getUsername();
	}

	public boolean isAccountNonExpired() {
		return super.getAccountNonExpired();
	}

	public boolean isAccountNonLocked() {
		return super.getAccountNonLocked();
	}

	public boolean isCredentialsNonExpired() {
		return super.getCredentialsNonExpired();
	}

	public boolean isEnabled() {
		return super.getEnabled();
	}

	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof LoginUser)
			return this.getUsername().equals(((LoginUser) rhs).getUsername());
		return false;
	}

	@Override
	public int hashCode() {
		return this.getUsername().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Username: ").append(this.getUsername()).append("; ");
		sb.append("teachercode: ").append(this.getTeachercode()).append("; ");
		sb.append("studentcode: ").append(this.getStudentcode()).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("\n");
		sb.append("Enabled: ").append(this.getEnabled()).append("; ");
		sb.append("AccountNonExpired: ").append(this.getAccountNonExpired())
				.append("; ");
		sb.append("\n");
		sb.append("credentialsNonExpired: ")
				.append(this.getCredentialsNonExpired()).append("; ");
		sb.append("AccountNonLocked: ").append(this.getAccountNonLocked())
				.append("; ");
		sb.append("\n");
		if (authorities != null && !authorities.isEmpty()) {
			sb.append("Granted Authorities: ");

			boolean first = true;
			for (GrantedAuthority auth : authorities) {
				if (!first) {
					sb.append(",");
				}
				first = false;
				sb.append(auth);
			}
		} else {
			sb.append("Not granted any authorities");
		}
		sb.append("\n");
		return sb.toString();
	}

	/**
	 * Getter and Setter
	 */

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	public String getTeachercode() {
		return teachercode;
	}

	public void setTeachercode(String teachercode) {
		this.teachercode = teachercode;
	}

	public String getStudentcode() {
		return studentcode;
	}

	public void setStudentcode(String studentcode) {
		this.studentcode = studentcode;
	}
}
