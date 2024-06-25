package com.hashedin.huSpark.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @NotNull
    private Integer numTickets;

    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date cancellationTime;

    // Add constructors, toString(), and any other methods as needed
}