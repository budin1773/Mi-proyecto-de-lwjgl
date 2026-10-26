public class Colision {
private float x, y, w, h;

public Colision(float x, float y, float w, float h){
this.x = x;
this.y = y;
this.w = w;
this.h = h;
}

public void update(float newX, float newY){
this.x = newX;
this.y = newY;
}

public boolean intersects(Colision other){
return this.x < other.x + other.w &&
       this.x + this.w > other.x &&
       this.y < other.y + other.h &&
       this.y + this.h > other.y;

}
public float getX() { return x; }
public float getY() { return y; }
public float getW() { return w; }
public float getH() { return h; }
}
