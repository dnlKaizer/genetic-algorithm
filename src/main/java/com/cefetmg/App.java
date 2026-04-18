package com.cefetmg;

import com.cefetmg.view.NQueensView;

public class App {
    public static void main(String[] args) {
        while (true) {
            System.out.print("\nExecutar (qualquer caracter cancela): ");
            String input = System.console().readLine();
            if (!input.equalsIgnoreCase("")) {
                break;
            }

            System.out.println(NQueensView.execute());
        }
    }
}
