package modele;

import java.util.Observable;

public class Formes extends Observable {
	
    protected int my_x;
    protected int my_y;
	
    public Formes(int x, int y){
    	my_x = x;
    	my_y = y;
    }

    public void setMy_x(int x) {
        my_x = x;
    }

    public void setMy_y(int y) {
        my_y = y;
    }

    public int getMy_x() {
        return my_x;
    }

    public int getMy_y() {
        return my_y;
    }

    public void validerChamps() {
        this.setChanged();
        this.notifyObservers(this);
    }

}
