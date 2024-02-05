import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Random;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;
public class MyGame extends Game  {
    public static final String TITLE = "MyGame";
    public static final int SCREEN_WIDTH = 1500;
    public static final int SCREEN_HEIGHT = 800;
    public Color backColor = new Color(187, 172, 159);
    public Color cellColor = new Color(205, 193, 180);
    public Color[] numberColors = new Color[] {Color.WHITE, new Color(0xeee1c9), new Color(0xf3b27a), new Color(0xf69664), new Color(0xf77c5f), new Color(0xf75f3b)};
    public int count = 0;
    // declare variables here
    public static final int offsetX = 500;
    public static final int offsetY = 100;
    public int[][] pieces = new int[4][4];
    public int boxWidth = 125;
    public int boxHeight = 125;
    public Random rand = new Random();
    public int spot1 = rand.nextInt(4);
    public int spot2 = rand.nextInt(4);
    public int value = rand.nextInt(10);
    public boolean full = false;
    public boolean moved = false;
    public int temper;
    public MyGame() {
        // initialize variables here
        pieces[0] = new int[] {0,0,0,0};
        pieces[1] = new int[] {0,0,0,0};
        pieces[2] = new int[] {0,0,0,0};
        pieces[3] = new int[] {0,0,0,0};
        if(value == 0){
            value = 4;
        }else{
            value = 2;
        }
        value = rand.nextInt(10);
        if(value == 0){
            value = 4;
        }else{
            value = 2;
        }
        while(pieces[spot1][spot2] != 0){
            spot1 = rand.nextInt(4);
            spot2 = rand.nextInt(4);
        }
        pieces[spot1][spot2] = value;
        value = rand.nextInt(10);
        if(value == 0){
            value = 4;
        }else{
            value = 2;
        }
        while(pieces[spot1][spot2] != 0){
            spot1 = rand.nextInt(4);
            spot2 = rand.nextInt(4);
        }
        pieces[spot1][spot2] = value;
    }
   
    public void update() {
        // updating logic
    }
    public void draw(Graphics pen) {
        pen.setColor(backColor);  
        Graphics2D g = (Graphics2D) pen;
        g.fillRoundRect(offsetX, offsetY, 640, 640, 15, 15);;
        pen.setColor(cellColor);
        for(int x = 0; x < pieces.length; x++){
            for(int y= 0; y < pieces[0].length; y++){
                if(pieces[x][y] == 0){
                    g.fillRoundRect(offsetX + (y* 25) + (boxWidth*y) +20, offsetY + (x*25) + (boxHeight*x)+20, 140, 140, 15, 15);
                }
                else{
                    count = 0;
                    temper = pieces[x][y];
                    while(temper != 1){
                        count++;
                        temper /= 2;
                    }
                    if(count <= 6){
                        pen.setColor(numberColors[count - 1]);
                    }else{
                        pen.setColor(new Color(255, 185 + ((count-6) *10), 0));
                    }

                    g.fillRoundRect(offsetX + (y* 25) + (boxWidth*y) +20, offsetY + (x*25) + (boxHeight*x)+20, 140, 140, 15, 15);
                    if(count <= 2){
                        pen.setColor(Color.BLACK);
                    }else{
                        pen.setColor(Color.WHITE);
                    }
                    int fontser = 75 - ((pieces[x][y] + "").length() * 7);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, fontser));
                    g.drawString(pieces[x][y] + "", offsetX + (y* 25) + (boxWidth*y) + (70 - ((pieces[x][y] + "").length() * 7)) , offsetY + (x*25) + (boxHeight*x) + 105);
                   pen.setColor(cellColor);
                }
            }
        }
    }
       
    @Override
    public void keyTyped(KeyEvent ke) {

    }
 
    @Override
    public void keyPressed(KeyEvent ke) {}
    @Override
    public void keyReleased(KeyEvent ke) {
        moved = false;
        switch(ke.getKeyCode()){
            case 37:
                for(int x = 0; x < pieces.length; x++){
                    for(int y = 0; y <= 3; y++){
                        if(pieces[x][y] != 0){
                            for(int z = 0; z < y; z++){
                                if(pieces[x][z] == 0){
                                    pieces[x][z] = pieces[x][y];
                                    pieces[x][y] = 0;
                                    moved = true;
                                }else if(pieces[x][z] == pieces[x][y]){
                                    for(int p = y - 1; p >= 0; p--){
                                        if(pieces[x][y] != pieces[x][p] && pieces[x][p] != 0){
                                            break;
                                        }
                                        if(pieces[x][p] == pieces[x][y]){
                                            pieces[x][p] *= 2;
                                            pieces[x][y] = 0;
                                            moved = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 39:
                for(int x = 0; x < pieces.length; x++){
                    for(int y = 3; y >=0; y--){
                       if(pieces[x][y] != 0){
                            for(int z = 3; z > y; z--){
                                if(pieces[x][z] == 0){
                                    pieces[x][z] = pieces[x][y];
                                    pieces[x][y] = 0;
                                    moved = true;
                                }else if(pieces[x][z] == pieces[x][y]){
                                    for(int p = y + 1; p < 4; p++){
                                        if(pieces[x][y] != pieces[x][p] && pieces[x][p] != 0){
                                            break;
                                        }
                                        if(pieces[x][p] == pieces[x][y]){
                                            pieces[x][p] *= 2;
                                            pieces[x][y] = 0;
                                            moved = true;
                                            break;
                                        }
                                    }
                                }
                            }
                       }
                    }
                }
                break;
            case 38:
                for(int y = 0; y < pieces[0].length; y++){
                    for(int x = 0; x < pieces.length; x++){
                        if(pieces[x][y] != 0){
                            for(int z = 0; z < x; z++){
                                if(pieces[z][y] == 0){
                                    pieces[z][y] = pieces[x][y];
                                    pieces[x][y] = 0;
                                    moved = true;
                                }else if(pieces[z][y] == pieces[x][y]){
                                    for(int p = x; p >= z; p--){
                                        if(pieces[x][y] != pieces[p][y] && pieces[p][y] != 0){
                                            break;
                                        }
                                        if(p == z){
                                            pieces[p][y] *= 2;
                                            pieces[x][y] = 0;
                                            moved = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 40:
                for(int y = 0; y < pieces.length; y++){
                    for(int x = 3; x >= 0; x--){
                        if(pieces[x][y] != 0){
                            for(int z = 3; z > x; z--){
                                if(pieces[z][y] == 0){
                                    pieces[z][y] = pieces[x][y];
                                    pieces[x][y] = 0;
                                    moved = true;
                                }else if(pieces[z][y] == pieces[x][y]){
                                    for(int p = z; p >= x; p--){
                                        if(pieces[x][y] != pieces[p][y] && pieces[p][y] != 0){
                                            break;
                                        }
                                        if(p == z){
                                            pieces[p][y] *= 2;
                                            pieces[x][y] = 0;
                                            moved = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
        for(int x = 0; x < pieces.length; x++){
            for(int y= 0; y < pieces.length; y++){
                if(pieces[x][y] == 0){
                    full = false;
                    break;
                }else if(x == 3 && y == 3){
                    full = true;
                    break;
                }
            }
        }
        System.out.println(full);
        if((ke.getKeyCode() == 37 || ke.getKeyCode() == 38 || ke.getKeyCode() == 39 || ke.getKeyCode() == 40) && !full && moved){
            spot1 = rand.nextInt(4);
            spot2 = rand.nextInt(4);
            value = rand.nextInt(10);
            if(value == 0){
                value = 4;
            }else{
                value = 2;
            }
            while(pieces[spot1][spot2] != 0){
                spot1 = rand.nextInt(4);
                spot2 = rand.nextInt(4);
            }
            pieces[spot1][spot2] = value;
        }
    }
 
    @Override
    public void mouseClicked(MouseEvent ke) {}
 
    @Override
    public void mousePressed(MouseEvent me) {}
       
    @Override
    public void mouseReleased(MouseEvent me) {}
 
    @Override
    public void mouseEntered(MouseEvent me) {}
 
    @Override
    public void mouseExited(MouseEvent me) {}
       
       
    //Launches the Game
    public static void main(String[] args) { new MyGame().start(TITLE, SCREEN_WIDTH,SCREEN_HEIGHT); }
}