package com.promineotech.music.music.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tracks {
    private int trackID;
    private String trackName;
    private int albumID;
}
