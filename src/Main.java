import JFrames.UI;

import javax.swing.*;

public class Main {
    static String teamName;
    public static void main(String[] args) {
        teamName = "Sheffield Wednesday";
        JFrame userUI = new UI(teamName);
    }
}