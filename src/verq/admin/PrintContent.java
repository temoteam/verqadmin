/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verq.admin;

/**
 *
 * @author Sergey
 */
import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
public class PrintContent implements Printable {
  private Component componentToBePrinted;
  public static void printComponent(Component c) {
    new PrintContent(c).print();
  }
  public PrintContent(Component componentToBePrinted) {
    this.componentToBePrinted = componentToBePrinted;
  }
  public void print() {
    PrinterJob printJob = PrinterJob.getPrinterJob();
    printJob.setPrintable(this);
    if (printJob.printDialog())
      try {
        System.out.println("Calling PrintJob.print()");
        printJob.print();
        System.out.println("End PrintJob.print()");
      }
      catch (PrinterException pe) {
        System.out.println("Error printing: " + pe);
      }
  }
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    int response = NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    //  for faster printing, turn off double buffering
    disableDoubleBuffering(componentToBePrinted);
    Dimension d = componentToBePrinted.getSize(); //get size of document
    double panelWidth = d.width; //width in pixels
    double panelHeight = d.height; //height in pixels
    double pageHeight = pf.getImageableHeight(); //height of printer page
    double pageWidth = pf.getImageableWidth(); //width of printer page
    double scale = pageWidth / panelWidth;
    int totalNumPages = (int) Math.ceil(scale * panelHeight / pageHeight);
    //  make sure not print empty pages
    if (pageIndex >= totalNumPages) {
      response = NO_SUCH_PAGE;
    }
    else {
      //  shift Graphic to line up with beginning of print-imageable region
      g2.translate(pf.getImageableX(), pf.getImageableY());
      //  shift Graphic to line up with beginning of next page to print
      g2.translate(0f, -pageIndex * pageHeight);
      //  scale the page so the width fits...
      g2.scale(scale, scale);
      componentToBePrinted.paint(g2); //repaint the page for printing
      enableDoubleBuffering(componentToBePrinted);
      response = Printable.PAGE_EXISTS;
    }
    return response;
  }
  public static void disableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(false);
  }
  public static void enableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(true);
  }
}