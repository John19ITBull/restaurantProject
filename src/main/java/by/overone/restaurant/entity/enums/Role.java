package by.overone.restaurant.entity.enums;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static by.overone.restaurant.entity.enums.UserPermission.*;

public enum Role {
    ADMIN(Sets.newHashSet(INFO_READ, INFO_WRITE, ADMIN_READ, ADMIN_WRITE)),
    USER(Sets.newHashSet(INFO_READ, INFO_WRITE, ADMIN_READ)),
    CLIENT(Sets.newHashSet(INFO_READ));

    private final Set<UserPermission> permissions;

    Role(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> permissions = this
                .getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
