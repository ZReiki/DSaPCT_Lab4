package main.model;

import lombok.*;

@Data
@AllArgsConstructor
public class Watch {
    private int id;
    private String brand;
    private String type;
    private double price;
    private int quantity;
    private int manufacturer_id;
}
