package org.HS;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String info = "Abbas Koussan <br>Am Bahndamm 8<br>D-86650 Wemding<br>Landkreis Donau-Ries<br>";
        Arrays.stream(info.split("<br>")).toList().forEach(System.out::println);
    }
}