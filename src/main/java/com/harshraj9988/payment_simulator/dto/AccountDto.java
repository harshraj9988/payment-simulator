package com.harshraj9988.payment_simulator.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    @NotNull(message = "Account name cannot be null")
    @NotEmpty(message = "Account name cannot be empty")
    private String name;

    @NotNull(message = "Balance must be provided")
    @PositiveOrZero(message = "Balance cannot be lesser than 0")
    private Long balance;
}
