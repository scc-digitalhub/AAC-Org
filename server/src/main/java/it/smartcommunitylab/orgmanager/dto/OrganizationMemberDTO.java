package it.smartcommunitylab.orgmanager.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import it.smartcommunitylab.aac.model.User;
import it.smartcommunitylab.orgmanager.common.Constants;

public class OrganizationMemberDTO {
	private String id;
	private String username;
	private Set<RoleDTO> roles;
	private boolean owner; // true if the member is owner of the organization
	
	public OrganizationMemberDTO() {};

	public OrganizationMemberDTO(User user) {
		id = user.getUserId();
		username = user.getUsername();
		roles = user.getRoles().stream().map(r -> new RoleDTO(r.canonicalSpace(), r.getRole())).collect(Collectors.toSet());
		owner = user.getRoles().stream().anyMatch(r -> r.getRole().equals(Constants.ROLE_PROVIDER));
	};

	public OrganizationMemberDTO(String id, String username, Set<RoleDTO> roles, boolean owner) {
		this.id = id;
		this.username = username;
		this.roles = roles;
		this.owner = owner;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Set<RoleDTO> getRoles() {
		if (roles == null) roles = new HashSet<>();
		return roles;
	}
	
	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
	
	public boolean getOwner() {
		return owner;
	}
	
	public void setOwner(boolean owner) {
		this.owner = owner;
	}
	

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [" + id + "]: Username=" + username + ", Owner=" + owner;
	}
}
