import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.*;

public class Main {
    public static void main(String[] args) {
   Window wn = new Window();
   wn.begin();
  boolean posInv = false;   

   Texture inv = new Texture("Inventorycook.png");
   Texture piso = new Texture("suelo.png");
   Texture piso2 = new Texture("suelo2.png");
   Texture chef = new Texture("chef.png");
   Texture client = new Texture("sanchez.png");
   Texture kitchen = new Texture("kitchen.png");
   Texture frezer = new Texture("frezer.png");
   
   Cuadrado Invtxr = new Cuadrado(inv);
   Cuadrado sueloObj = new Cuadrado(piso);
   Cuadrado sueloObj2 = new Cuadrado(piso2);
   Cuadrado chefPl = new Cuadrado(chef);
   Cuadrado clientNpc = new Cuadrado(client);
   Cuadrado kitchenObj = new Cuadrado(kitchen); 
   Cuadrado frezerObj = new Cuadrado(frezer);

Invtxr.Make(0f, 0f, 0.5f, 0.5f);
Invtxr.setColision(0f, 0f);

sueloObj.Make(0f, -0.5f, 0.5f, 0.5f);
sueloObj.setColision(1f, 1f);

sueloObj2.Make(0f, 0f, 0.5f, 0.5f);
sueloObj2.setColision(1f, 1f);

chefPl.Make(0f, 0f, 0.1f, 0.2f);
chefPl.setColision(0.1f, 0.1f);

clientNpc.Make(-0.4f, 0.1f, 0.1f, 0.2f);
clientNpc.setColision(0.1f, 0.1f);

kitchenObj.Make(0.3f, 0.5f, 0.08f, 0.1f);
kitchenObj.setColision(0.1f, 0.1f);

frezerObj.Make(0.2f, 0.55f, 0.1f, 0.2f);
frezerObj.setColision(0.1f, 0.1f);


while (!glfwWindowShouldClose(wn.getWin())) {
wn.clear();

System.out.println(chefPl.cubeY);
System.out.println(chefPl.cubeX);

sueloObj.draw();
sueloObj2.draw();
kitchenObj.draw();
frezerObj.draw();
chefPl.draw();
clientNpc.draw();
    
// 1
if (glfwGetKey(wn.getWin(), GLFW_KEY_W) == GLFW_PRESS) {
float lastMoveY = chefPl.cubeY;

chefPl.cubeY += 0.0009f;
chefPl.actualizarPosicion();

if (chefPl.getColision().intersects(kitchenObj.getColision())){
chefPl.cubeY = lastMoveY;
chefPl.actualizarPosicion();
}
if (chefPl.getColision().intersects(frezerObj.getColision())){
chefPl.cubeY = lastMoveY;
chefPl.actualizarPosicion();
}
}

// 2
if (glfwGetKey(wn.getWin(), GLFW_KEY_S) == GLFW_PRESS) {
float lastMoveY = chefPl.cubeY;

chefPl.cubeY -= 0.0009f;
chefPl.actualizarPosicion();

if (chefPl.getColision().intersects(kitchenObj.getColision())){
chefPl.cubeY = lastMoveY;
Invtxr.draw();
chefPl.actualizarPosicion();
}
if (chefPl.getColision().intersects(frezerObj.getColision())){
chefPl.cubeY = lastMoveY;
chefPl.actualizarPosicion();

}
}

// 3
if (glfwGetKey(wn.getWin(), GLFW_KEY_D) == GLFW_PRESS) {
float lastMoveX = chefPl.cubeX;

chefPl.cubeX += 0.0009f;
chefPl.actualizarPosicion();

if (chefPl.getColision().intersects(kitchenObj.getColision())){
chefPl.cubeX = lastMoveX;
Invtxr.draw();
chefPl.actualizarPosicion();

}
if (chefPl.getColision().intersects(frezerObj.getColision())){
chefPl.cubeX = lastMoveX;
chefPl.actualizarPosicion();

}
} 

// 4
if (glfwGetKey(wn.getWin(), GLFW_KEY_A) == GLFW_PRESS) {
float lastMoveX = chefPl.cubeX;

chefPl.cubeX -= 0.0009f;
chefPl.actualizarPosicion();

if (chefPl.getColision().intersects(kitchenObj.getColision())){
chefPl.cubeX = lastMoveX;
Invtxr.draw();
chefPl.actualizarPosicion();

}

if (chefPl.getColision().intersects(frezerObj.getColision())){
chefPl.cubeX = lastMoveX;
chefPl.actualizarPosicion();

}
}

wn.swapandpoll();
}

Invtxr.shouldBucle();
sueloObj.shouldBucle();
sueloObj2.shouldBucle();
chefPl.shouldBucle();
clientNpc.shouldBucle();
kitchenObj.shouldBucle();
frezerObj.shouldBucle();

inv.cleanUp();
piso.cleanUp();
piso2.cleanUp();
chef.cleanUp();
client.cleanUp();
kitchen.cleanUp();
frezer.cleanUp();

wn.End();
}
}
