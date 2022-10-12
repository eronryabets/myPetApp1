package com.eronryabets.first_pet.entity;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="pet_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"wallets"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NonNull
    @Length(min = 2, max = 15, message = "Min 2 symbols, Max 15!")
    private String username;

    @Column(name = "name")
    @NonNull
    @Length(min = 2, max = 15, message = "Min 2 symbols, Max 15!")
    private String name;

    @Column(name = "surname")
    @NonNull
    @Length(min = 2, max = 15, message = "Min 2 symbols, Max 15!")
    private String surname;

    @Column(name = "password")
    @NonNull
    @Length(min = 5, max = 200, message = "Min 5 symbols!")
    private String password;

    @Column(name = "active")
    private boolean active;

    @Column(name = "avatar")
    private String avatar;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "pet_users_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Wallet> wallets;


    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive();
    }

    public void addWalletToUser(Wallet newWallet){
        if(wallets == null){
            wallets = new ArrayList<>();
        }
        wallets.add(newWallet);
        newWallet.setUser(this);
    }

}
