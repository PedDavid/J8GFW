package j8gfw.inputs;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private Key[] keys = new Key[256];

    public KeyInput(){
        for(int i=0; i<keys.length; i++)
            keys[i]=new Key();
    }

    public class Key{

        private boolean pressed, clicked;

        public void toggle(boolean pressed){
            if(!this.pressed && pressed)
                clicked = true;
            else
                clicked = false;
            this.pressed = pressed;
        }

        private boolean getClicked(){
            if(clicked){
                clicked = false;
                return true;
            }
            return false;
        }

        private boolean getPressed(){
            return pressed;
        }
    }

    public boolean getClicked(int key){
        return keys[key].getClicked();
    }

    public boolean getPressed(int key){
        return keys[key].getPressed();
    }

    public void releaseAll(){
        for (int i = 0; i < keys.length; i++)
            keys[i].toggle(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()<256)
            keys[e.getKeyCode()].toggle(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()<256)
            keys[e.getKeyCode()].toggle(false);
    }

    @Override
    public String toString(){
        String input = "";
        for(int i = 0;  i < keys.length; i++){
            input += keys[i] + "\n";
        }
        return input;
    }
}