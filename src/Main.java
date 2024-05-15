import Data.Data;
import JFrames.UI;

import javax.swing.*;

public class Main {
    public static String teamName;
    public static void main(String[] args) {
        teamName = "Arsenal";
        JFrame userUI = new UI(teamName);
    }
}