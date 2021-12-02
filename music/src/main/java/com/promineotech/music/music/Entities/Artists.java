package com.promineotech.music.music.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artists {
    private int artistID;
    private String firstName;
    private String lastName;
    private int age;
    
}
