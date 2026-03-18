package bus;

import java.time.LocalDate;

public record TransactionRecord(Double newBalance, 
		  Double operation,
		  LocalDate date) {
}