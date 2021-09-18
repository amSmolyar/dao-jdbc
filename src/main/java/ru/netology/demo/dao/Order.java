package ru.netology.demo.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, insertable = true, updatable = true)
    private Date date;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(nullable = false)
    @Min(1)
    private int amount;

    @Column(name = "customer_id", nullable = false)
    @Min(1)
    private long customerId;


    @Transient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id", referencedColumnName="id")
    private Customer customer;
}
