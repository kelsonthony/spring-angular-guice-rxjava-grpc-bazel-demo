package com.kelsonthony.costumer.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user_entity_table")
public class UserEntity
        //implements UserDetails {
        {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dateCreate;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "access_entity_table",
//            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "access_id"},
//                    name = "unique_access_user"),
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", table = "user_entity_table",
//                    unique = false,
//                    foreignKey = @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT)),
//            inverseJoinColumns = @JoinColumn(name = "access_id", unique = false, referencedColumnName = "id", table = "access_entity_table",
//                    foreignKey = @ForeignKey(name = "access_fk", value = ConstraintMode.CONSTRAINT)))
//    private List<AccessEntity> accessEntities;

    @ManyToMany
    @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupEntity> groups = new HashSet<>();

    public boolean removerGrupo(GroupEntity group) {
        return getGroups().remove(group);
    }

    public boolean adicionarGrupo(GroupEntity group) {
        return getGroups().add(group);
    }

    public boolean isNovo() {
        return getId() == null;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.accessEntities;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
