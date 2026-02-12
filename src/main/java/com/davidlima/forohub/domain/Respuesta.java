package com.davidlima.forohub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Respuesta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String mensaje;

  @Column(name = "fecha_creacion")
  private LocalDateTime fechaCreacion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "autor_id")
  private Usuario autor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "topico_id")
  private Topico topico;

  private Boolean solucion;
}
