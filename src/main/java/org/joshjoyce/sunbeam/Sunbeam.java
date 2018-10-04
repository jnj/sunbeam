package org.joshjoyce.sunbeam;

import javax.swing.JFrame;
import java.awt.*;

public class Sunbeam {
    public static void main(String[] args) {
        World w = new World();
        w.build();
        w.renderScene();
    }
}

