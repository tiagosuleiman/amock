/*package com.segur.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.segur.model.User;

@Entity
@Table(name="Roles")
@SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLES_SEQ", initialValue = 1, allocationSize = 1)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ROLE_SEQ")
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    private String username;
    
    private String role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
    
}*/