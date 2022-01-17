@@ -0,0 +1,65 @@
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import com.digi.xbee.api.WiFiDevice;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeProtocol;
import com.digi.xbee.api.models.XBeeMessage;

/**
 *
 * @author pi
 */
public class JavaApplication2 {

    private static final String PORT = "/dev/ttyUSB0"; // On choisit le port, celui où la carte Xbee est connectée.

    private static final int BAUD_RATE = 9600; // On définit la rapidité de modulation, à savoir ici 9600 ( 9600 bit/s).

    private static final String DATA_RECEIVE = ""; // On définit une variable de type String afin d'y stocker la valeur brute reçue.
    
    public static final String recu=""; // On définit une variable de type String afin d'y stocker la valeur finale voulue.

   
    public static void main(String[] args) {

        XBeeDevice myDevice = new XBeeDevice(PORT, BAUD_RATE); // On définit le nouvel objet Xbee
        byte[] dataToReceive = DATA_RECEIVE.getBytes();

        try {

            myDevice.open(); // On "ouvre" la communication

            if (myDevice.getXBeeProtocol() == XBeeProtocol.XBEE_WIFI) {
                myDevice.close();
                myDevice = new WiFiDevice(PORT, BAUD_RATE);
                myDevice.open();
                

            } else {
                for(int i = 0; i<20; i++){
                    XBeeMessage recu =  myDevice.readData();    
                    System.out.println("Le message recu est le suivant : "+recu); // Ici on affiche le message, la valeur reçue. 
                }
               
            }

            System.out.println(" >> Success");

        } catch (XBeeException e) {
            System.out.println(" >> Error");
            e.printStackTrace();
            System.exit(1);

        } finally {
            myDevice.close(); 
        }

        // TODO code application logic here
    }

}
