package com.harshraj9988.payment_simulator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmountRequestDto {

    private Long id;

    @NotNull
    private Long accountId;

    @NotNull
    @Positive
    private Long amount;

    @NotNull
    @NotBlank
    private String idempotencyKey;
}
