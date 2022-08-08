package com.Motorcycle.MotorcycleStar.dtos.Transaction;

import com.Motorcycle.MotorcycleStar.models.Transaction;
import lombok.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TransactionGridDTO {
    private Integer id;
    private String fullName;
    private String address;
    private String phone;
    private String vehicle;
    private Integer quantity;
    private String totalPrice;
    private String purchaseDate;


    public static List<TransactionGridDTO> convert(List<Object[]> transactionHeaderDtos) {
        NumberFormat formatMoney = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

        List<TransactionGridDTO> transactionHeaderDtoList = new ArrayList<>();
        for (Object[] transactionHeaderDto : transactionHeaderDtos) {

            transactionHeaderDtoList.add(new TransactionGridDTO(
                    (Integer) transactionHeaderDto[0],
                    (String) transactionHeaderDto[1],
                    (String) transactionHeaderDto[2],
                    (String) transactionHeaderDto[3],
                    (String) transactionHeaderDto[4],
                    (Integer) transactionHeaderDto[5],
                    formatMoney.format(transactionHeaderDto[6]),
                    dateToString((Date) transactionHeaderDto[7])
            ));

        }
        return transactionHeaderDtoList;
    }

    private static String dateToString(Date date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String dateString = date.toString();
        LocalDate newLocalDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        return newLocalDate.format(formatter);
    }

    public static TransactionGridDTO set(Transaction transaction) {
        NumberFormat formatMoney = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String totalPrice = formatMoney.format(transaction.getProductID().getUnitPrice().multiply(BigDecimal.valueOf(transaction.getQuantitiy())));
        return new TransactionGridDTO(
                transaction.getId(),
                transaction.getCustomerID().getFirstName() + " " + transaction.getCustomerID().getLastName(),
                transaction.getCustomerID().getAddress(),
                transaction.getCustomerID().getPhone(),
                transaction.getProductID().getVehicleModel(),
                transaction.getQuantitiy(),
                totalPrice,
                transaction.getPurchaseDate().format(formatter)
        );
    }

}

