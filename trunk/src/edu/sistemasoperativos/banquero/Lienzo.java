package edu.sistemasoperativos.banquero;

import java.util.Collection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

//Se encarga de pintar lo que obtiene del banquero    
class Lienzo extends View {
   	Banco banquero;
   	
       public Lienzo(Context context, Banco banq) {
           super(context);
           banquero = banq;
       }
       
       protected void onDraw(Canvas canvas) {
           canvas.drawRGB(245,245,245); //borrado del lienzo
           int ancho = canvas.getWidth(); //obtiene el ancho del lienzo
           int alto = canvas.getHeight(); //obtiene el alto del lienzo
           
           Paint pincel1=new Paint();  //objeto pincel para dibujar
           
           Collection<Cliente> colClientes = banquero.getClientes();
           Object[] clientes = colClientes.toArray();
           
           int cantidadRecursos = banquero.getCantidadRecursos();
           
           int altoRecurso = alto/cantidadRecursos;
           int anchoRecurso = ancho-200;
                      
           
           //se recorren todos los clientes pintandolos a la derecha 
           int yActual = 10;
           for(int i=0; i<clientes.length;i++){
        	  SetColor(pincel1,i);
        	  canvas.drawRect(ancho-100, yActual, ancho-50, yActual+10, pincel1); //rectangulo por vertices
        	  canvas.drawText("Cliente"+i,ancho-90, yActual+20, pincel1); //txt en ubicacion x,y
        	  yActual+=25;
           }
           
           //pinta las divisiones para cada recurso
           pincel1.setARGB(255, 0, 0, 0);
           for(int i=0; i < cantidadRecursos;i++){
        	   int y = altoRecurso*i;
        	   canvas.drawLine(0, y, ancho-100, y, pincel1); //dibuja una linea xi,yi,xf,yf
        	   canvas.drawText("Recurso"+i,10, y+(altoRecurso/2), pincel1);
           }
           
           //pinta las barras de lo necesario de cada cliente
           int xActual = 100;
           for(int i=0; i < clientes.length;i++){
        	   SetColor(pincel1,i);
        	   canvas.drawLine(xActual,0,xActual,alto,pincel1);
        	   canvas.drawLine(xActual+30,0,xActual+30,alto,pincel1);
        	   xActual += 35;
           }
           
           //recorre los clientes pintando los recursos obtenidos
           xActual = 100;
           yActual = altoRecurso;
           
           
           for(int i=0; i < clientes.length;i++){
        	   Cliente cli = (Cliente) clientes[i];
        	   for(int j=0;j<cantidadRecursos;j++){
	           	   int necesarios = cli.getCantidadRecursoNecesario(j);
	        	   int obtenidos = cli.getCantidadRecursoObtenido(j);
	        	   int altopintar = altoRecurso*obtenidos/necesarios;
	        	   
	        	   System.out.println("i:"+i+" j:"+j+" nec:"+necesarios+" obt:"+obtenidos+" alt:"+altopintar);
	        	   
	        	   SetColor(pincel1,i);
	        	   canvas.drawRect(xActual,yActual-altopintar,xActual+30,yActual,pincel1);
	        	   yActual += altoRecurso;
	           }
        	   yActual = altoRecurso;
        	   xActual += 35;
           }
           
	           
           
           //pincel1.setStrokeWidth(2); //ancho del pincel
       }
       
       public void SetColor(Paint pincel, int color){
    	  switch(color){
    	   	case 0: pincel.setARGB(255, 0, 0, 0);
    	   	break;	
    	   
    	   	case 1: pincel.setARGB(255, 255, 0, 0);
    	   	break;
    	   	
    	   	case 2: pincel.setARGB(255, 0, 255, 0);
    	   	break;
    	   	
    	   	case 3: pincel.setARGB(255, 0, 0, 255);
    	   	break;
    	   	
    	   	case 4: pincel.setARGB(255, 255, 255, 0);
    	   	break;
    	   	
    	   	case 5: pincel.setARGB(255, 255, 0, 255);
    	   	break;
    	   	
    	   	case 6: pincel.setARGB(255, 0, 130, 90);
    	   	break;
    	   	
    	   	case 7: pincel.setARGB(255, 90, 155, 255);
    	   	break;
    	   	
    	   	case 8: pincel.setARGB(255, 255, 25, 100);
    	   	break;
    	   	
    	   	case 9: pincel.setARGB(255, 100, 80, 150);
    	   	break;
    	   	
    	   }
    	   
       }
}   