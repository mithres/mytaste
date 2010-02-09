package com.vc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserInfo implements UserDetails {

	private static final long serialVersionUID = -6406111134570906351L;

	@Id
	private String userName = null;

	private String password = null;

	private String firstName = null;

	private String lastName = null;
	
	@Column(unique = true)
	private String email = null;

	@GeneratedValue
	private Long userIndex = null;

	private UserLevel userLevel = UserLevel.User;

	private Boolean enable = Boolean.TRUE;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_name"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	private Set<Role> roles = new HashSet<Role>();

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@OrderBy("purchaseDate desc")
	private Set<PurchasesHistory> purchasesHistory = new HashSet<PurchasesHistory>();

	private Float accountBalance = new Float(0);

	@Transient
	private GrantedAuthority[] authorities = null;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUserIndex() {
		return userIndex;
	}

	public void setUserIndex(Long userIndex) {
		this.userIndex = userIndex;
	}

	public UserLevel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Override
	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return getEnable();
	}

	public void setAuthorities(GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}

	public Set<PurchasesHistory> getPurchasesHistory() {
		return purchasesHistory;
	}

	public void setPurchasesHistory(Set<PurchasesHistory> purchasesHistory) {
		this.purchasesHistory = purchasesHistory;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
