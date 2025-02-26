package com.examplesemanal_serverless.semanal_serverless.entity;

import com.examplesemanal_serverless.semanal_serverless.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@Table
public class Pedido {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "cliente", nullable = false)
    private String cliente;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "itens", nullable = false)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <Item> itens;

    @Column(name = "total", nullable = false)
    private float total;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime data_criacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime data_atualizacao;

    @PrePersist
    private void prePersist ()
    {
        this.data_criacao = LocalDateTime.now();
        this.status = status.PENDENTE;
    }

    @PreUpdate
    private void preUpdate ()
    {
        this.data_atualizacao = LocalDateTime.now();
    }

}
