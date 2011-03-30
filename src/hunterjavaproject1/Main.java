/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hunterjavaproject1;

import processing.core.*;
import java.awt.Color;
/**
 *
 * @author umar456 && Hunter Glass
 */
public class Main extends PApplet {

    public int valCounter = 0;
    public int labelCounter = 0;
    //arrays created to hold vals, labels, and colors for the graph, they will be instantiated as the program progresses
    public Float[] graphValues;
    public String[] graphLabels;
    public Color[] graphColors;

    @Override
    public void setup() {
        //argsCounter String Array made for clarity when used for future array indeces,
        String argsCounter[] = new String[args.length];
        //Values Labels and Colors are all made into an array with an index of inputted args
        graphValues = new Float[(argsCounter.length)];
        graphLabels = new String[(argsCounter.length)];
        graphColors = new Color[(argsCounter.length)];

        //loop set to 3 due to where the first graphable arg is located
        for (int i = 3; i < args.length; i++) {
            //Numbers are randomly picked for color selection
            double randomNumOne = Math.random();
            double randomNumTwo = Math.random();
            double randomNumThree = Math.random();
            //new colors are used via random numbers
            Color randomGraphColor = new Color((float) randomNumOne, (float) randomNumTwo, (float) randomNumThree);
            //This actually colors the graph bars, if the resultant of i%2 == 0 then graphLabel's index is increments and set to args index
            //If not, graphValues with an index of valCounter is incremented, then set = to the index of args converted to a float
            //This essentially just prevents crashing
            graphColors[valCounter] = randomGraphColor;
            if ((i % 2) == 0) {
                graphLabels[labelCounter++] = args[i];
            } else {
                graphValues[valCounter++] = Float.parseFloat(args[i]);
            }

        }
        //Windows size parameters are set to their respected arguments
        size(Integer.parseInt(args[1]), Integer.parseInt(args[2]));

        //args test loop
        //for (int i = 0; i < args.length; i++) {
        //   System.out.println(args[i]);
        //}
    }

    @Override
    public void draw() {
        //background is set
        background(204);
        //width taken from args
        float width = Float.parseFloat(args[1]);
        // This sets the rectangles width to all be the same and spaced AND maintains spacing on the last 2 bars
        float rectangleWidth = ((width - (5 * (valCounter + 1))) / valCounter);
        //Sets the largest Rectangle, all other rectangles will scale from this one
        float largestRectangle = 0;
        //Loop will update which rectangle is the largest as they are inputted
        for (int i = 0; i < valCounter; i++) {
            if (graphValues[i] > largestRectangle) {
                largestRectangle = graphValues[i];
            }
        }
        for (int i = 0; i < valCounter; i++) {
            //This fills graph color's index with one of the 3 colors from AWT
            fill(graphColors[i].getGreen(), graphColors[i].getBlue(), graphColors[i].getRed());



            //Sets how tall the rectangles respected to each other.
            float rectangleStacking = graphValues[i] / largestRectangle;
            //Sets the beginning coordinate for the rectangles. Starts at the y value gotten from args, - 20 pixels as the rubric requires.
            //This is then Multiplied by the Scaling of the rectangles to ensure no rectangle is the same height as another.
            //Because of the line, the rectangles will become progressively smaller as their value from args decreases
            float rectangleBeginCoordinate = Float.parseFloat(args[2]) - ((Float.parseFloat(args[2]) - 20) * rectangleStacking);
            //Creates a rectangle with an x component of 5 pixels * index + 1, this ensures the rectangles are spaced 5 pixels apart
            //The height compenent is set to be equal to the 2nd index of args minus the required - 20 pixels
            rect((5 * (i + 1) + (rectangleWidth * i)), rectangleBeginCoordinate, rectangleWidth, Float.parseFloat(args[2]) - rectangleBeginCoordinate);
            //Fills the text in the boxes
            fill(224, 255, 255);
            //This will put text in the boxes
            //This is set to keep the 5 pixel spacing. label them.
            // the rectangleWidth/2 keeps the text in the center of the graph
            //To maintain that bars are in full vision. 10 is subtracted from the args[2]
            text(graphLabels[i], (5 * i + (rectangleWidth * i)) + ((rectangleWidth / 2)), Float.parseFloat(args[2]) - 10);
        }
        //TO DO Mouse Over Highlight
        fill(255,255,255);
        line(mouseX, 20, mouseX, 80);
    }

    public static void main(String[] args) {
        //Create an array which will be sent to the PApplet's main function
        //This array should include the arguments from the comand line and
        //the arguments that processing requires. The first two will be used by
        //the Processing API and the rest will be used by our program.
        String tempArgs[] = new String[args.length + 2];

        //Set the parameters which will be used by Processing's API
        tempArgs[0] = "--bgcolor=#FFFFFF";
        tempArgs[1] = "hunterjavaproject1.Main";
        //Append the arguments from the command line to the end of the tempArray
        for (int i = 2; i < tempArgs.length; i++) {
            tempArgs[i] = args[i - 2];
        }
        //Send the String array to the PApplet's main function.
        PApplet.main(tempArgs);

    }
}
