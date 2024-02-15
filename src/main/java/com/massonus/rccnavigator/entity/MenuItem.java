package com.massonus.rccnavigator.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MenuItem {

    private String link;

    private String label;

    public MenuItem(String link, String label) {
        this.link = link;
        this.label = label;
    }
}
