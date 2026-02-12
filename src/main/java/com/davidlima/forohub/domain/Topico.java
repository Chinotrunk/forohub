package com.davidlima.forohub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos",
        uniqueConstraints = @UniqueConstraint(name = "uk_topico_titulo_mensaje", columnNames = {"titulo", "mensaje"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;

  @Column(columnDefinition = "TEXT")
  private String mensaje;

  @Column(name = "fecha_creacion")
  private LocalDateTime fechaCreacion;

  private String status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "autor_id")
  private Usuario autor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "curso_id")
  private Curso curso;

  @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Respuesta> respuestas;
}
