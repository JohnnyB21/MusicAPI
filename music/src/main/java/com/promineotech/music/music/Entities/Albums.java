package com.promineotech.music.music.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Albums {
    private int albumID;
    private String albumName;
    private int artistID;
    private int genreID;

    
}
