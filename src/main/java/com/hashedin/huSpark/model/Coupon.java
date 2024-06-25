package com.hashedin.huSpark.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String code;

    @NotNull
    private BigDecimal discountPercent;

    @NotNull
    private Integer maxUses;

    @NotNull
    private Integer remainingUses;

    @NotNull
    @Future
    private Date expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    // Add constructors, toString(), and any other methods as needed
}

