package com.promineotech.music.music.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumSales {
    private int albumSalesID;
    private int albumID;
    private int albumsSold;
}
