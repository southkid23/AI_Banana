package phase_2;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.util.*;
import java.io.*;

public class Phase2 {


    private static int canvasWidth = 300;
    private static int canvasHeight = 300;

    public static void main(String[] args) throws Exception{

        JFrame window = new JFrame("Initial");
        JFrame window2 = new JFrame("Final");
        MyCanvas myCanvas = new MyCanvas();
        MyCanvas myCanvas2 = new MyCanvas();
        List<String> ppp = new ArrayList<String>();
        List<String> ppp2 = new ArrayList<String>();

        ReadCSV.read();

        ppp = ReadCSV.getList();

        myCanvas.setCoordinates(ppp);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(200, 200, canvasWidth, canvasHeight);
        window.getContentPane().add(myCanvas);
        window.setVisible(true);

        // Initialize population
        Population pop = new Population(50, true);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());

        // Evolve population for 100 generations
        pop = GA.evolvePopulation(pop);
        while (true) {
            if(pop.isIdentical()){
                break;
            }

            pop = GA.evolvePopulation(pop);
        }

        // Print final results
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        GA.getIterations();
        Tour fittest = pop.getFittest();
        System.out.println("Solution:");
        System.out.println(fittest);

        String x;
        String y;
        for (int i = 0; i < fittest.tourSize(); i++) {
            x = Integer.toString(fittest.getCity(i).getX());
            y = Integer.toString(fittest.getCity(i).getY());
            ppp2.add(x);
            ppp2.add(y);
        }

        myCanvas2.setCoordinates(ppp2);

        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window2.setBounds(800, 200, canvasWidth, canvasHeight);
        window2.getContentPane().add(myCanvas2);
        window2.setVisible(true);
    }
}