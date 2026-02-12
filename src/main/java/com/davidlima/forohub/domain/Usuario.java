package com.davidlima.forohub.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  @Column(name = "correo_electronico", unique = true)
  private String correoElectronico;

  private String contrasena;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "usuarios_perfiles",
          joinColumns = @JoinColumn(name = "usuario_id"),
          inverseJoinColumns = @JoinColumn(name = "perfil_id")
  )
  private Set<Perfil> perfiles;

  // UserDetails
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return perfiles.stream()
            .map(p -> (GrantedAuthority) p::getNombre)
            .toList();
  }

  @Override
  public String getPassword() {
    return contrasena;
  }

  @Override
  public String getUsername() {
    return correoElectronico;
  }

  @Override
  public boolean isAccountNonExpired() { return true; }

  @Override
  public boolean isAccountNonLocked() { return true; }

  @Override
  public boolean isCredentialsNonExpired() { return true; }

  @Override
  public boolean isEnabled() { return true; }
}
