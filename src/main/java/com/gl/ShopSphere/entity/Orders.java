package com.gl.ShopSphere.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Tells JPA how to generate the value for the primary key when a new record is inserted into the database.

            private Integer id;

            @Column(name = "total")
            private Double total;

            @Column(name = "status")
            private String status;

            @Column(name = "created_at")
            private LocalDateTime created_at;

            @ManyToOne(cascade = CascadeType.ALL)
            @JoinColumn(name = "user_id")
            private Users user;

            @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
            private List<Order_items> order_itemsList;
}
