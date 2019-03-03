package org.academiadecodigo.tropadelete.cheiodesono.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.net.URL;

public class ScrollingImage extends Picture {
    private BufferedImage image;
    private BufferedImage largeImage;

    private JLabel label = new JLabel();
    private String source;
    private Rectangle box =new Rectangle(70,50);
    private double x;
    private double y;
    private double xGrow;
    private double yGrow;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;


    public ScrollingImage(double var1, double var3, String var5) {
        this.x = var1;
        this.y = var3;
        this.load(var5);
    }



    public void load(String var1) {
        try {
            this.source = var1;
            if (var1.startsWith("http://")) {
                this.largeImage = ImageIO.read((new URL(var1)).openStream());
            } else {
                URL var2 = this.getClass().getResource(var1.startsWith("/") ? var1 : var1);
                if (var2 != null) {
                    this.largeImage = ImageIO.read(var2.openStream());
                } else {
                    this.largeImage = ImageIO.read(new File(var1));
                }
            }

        } catch (Exception var3) {
            this.image = null;
            this.label.setIcon((Icon) null);
            var3.printStackTrace();
        }

        //Canvas.getInstance().repaint();
    }

    public int getX() {
        return (int) Math.round(this.x - this.xGrow);
    }

    public int getY() {
        return (int) Math.round(this.y - this.yGrow);
    }

    public int getMaxX() {
        return this.getX() + this.getWidth();
    }

    public int getMaxY() {
        return this.getY() + this.getHeight();
    }

    public int getWidth() {
        return (int) Math.round((double) (this.image == null ? 0 : this.image.getWidth()) + 2.0D * this.xGrow);
    }

    public int getHeight() {
        return (int) Math.round((double) (this.image == null ? 0 : this.image.getHeight()) + 2.0D * this.yGrow);
    }

    public int pixels() {
        return this.image == null ? 0 : this.image.getWidth() * this.image.getHeight();
    }

    public int[][] getGrayLevels() {
        if (this.image == null) {
            return new int[0][0];
        } else {
            int[][] var1 = new int[this.getHeight()][this.getWidth()];

            for (int var2 = 0; var2 < var1.length; ++var2) {
                for (int var3 = 0; var3 < var1[var2].length; ++var3) {
                    int var4 = this.image.getRGB(var3, var2);
                    var1[var2][var3] = (int) (0.2989D * (double) (var4 >> 16 & 255) + 0.5866D * (double) (var4 >> 8 & 255) + 0.1144D * (double) (var4 & 255));
                }
            }

            return var1;
        }
    }

    public String toString() {
        this.getX();
        this.getY();
        this.getWidth();
        this.getHeight();
        return this.source;
    }

    public Color getColorAt(int var1) {
        if (this.image != null && var1 >= 0 && var1 < this.pixels()) {
            return this.getColorAt(var1 % this.image.getWidth(), var1 / this.image.getWidth());
        } else {
            throw new IndexOutOfBoundsException(var1);
        }
    }

    public void setColorAt(int var1, Color var2) {
        if (this.image != null && var1 >= 0 && var1 < this.pixels()) {
            this.setColorAt(var1 % this.image.getWidth(), var1 / this.image.getWidth(), var2);
        } else {
            throw new IndexOutOfBoundsException(var1);
        }
    }

    public Color getColorAt(int var1, int var2) {
        if (this.image != null && var1 >= 0 && var1 < this.image.getWidth() && var2 >= 0 && var2 < this.image.getHeight()) {
            int var3 = this.image.getRGB(var1, (int) var2) & 16777215;
            return new Color(var3 / 65536, var3 / 256 % 256, var3 % 256);
        } else {
            IndexOutOfBoundsException var10001 = new IndexOutOfBoundsException();
            throw var10001;
        }
    }

    public void setColorAt(int var1, int var2, Color var3) {
        if (this.image != null && var1 >= 0 && var1 < this.image.getWidth() && var2 >= 0 && var2 < this.image.getHeight()) {
            this.image.setRGB(var1, (int) var2, var3.getRed() * 65536 + var3.getGreen() * 256 + var3.getBlue());
            Canvas.getInstance().repaint();
        } else {
            IndexOutOfBoundsException var10001 = new IndexOutOfBoundsException();
            throw var10001;
        }
    }

    public void translate(double var1, double var3) {
        this.x += var1;
        this.y += var3;
        Canvas.getInstance().repaint();
    }

    public void grow(double var1, double var3) {
        this.xGrow += var1;
        this.yGrow += var3;
        Canvas.getInstance().repaint();
    }

    public void drawFrom (int positionX){
        delete();

        image = largeImage.getSubimage(positionX,0,FRAME_WIDTH,FRAME_HEIGHT);

        this.label.setIcon(new ImageIcon(this.image));
        this.label.setText("");
        draw();
    }

    public void draw() {
        Canvas.getInstance().show(this);
    }

    public void delete() {
        Canvas.getInstance().hide(this);
    }

    public void paintShape(Graphics2D var1) {
        if (this.image != null) {
            Dimension var2 = this.label.getPreferredSize();
            if (var2.width > 0 && var2.height > 0) {
                this.label.setBounds(0, 0, var2.width, var2.height);
                var1.translate(this.getX(), this.getY());
                var1.scale(((double) this.image.getWidth() + 2.0D * this.xGrow) / (double) var2.width, ((double) this.image.getHeight() + 2.0D * this.yGrow) / (double) var2.height);
                this.label.paint(var1);
            }
        }

    }
}


