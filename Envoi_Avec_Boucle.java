
package com.digi.xbee.example;

import com.digi.xbee.api.WiFiDevice;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBeeProtocol;

public class MainApp {

    private static final String PORT = "/dev/tty.usbserial-14140";  // On vient chercher le port qui nous intéresse.
   
    private static final int BAUD_RATE = 9600; // On définit la vitesse de transmission

    private static final String DATA_TO_SEND = "Test"; // On définit une valeur à émettre. 

    public static void main(String[] args) {
        XBeeDevice myDevice = new XBeeDevice(PORT, BAUD_RATE);
        byte[] dataToSend = DATA_TO_SEND.getBytes(); // On encode notre valeur prédéfinis dans une nouvelle variable.
        
        try {
            
            myDevice.open();

            System.out.format("Sending broadcast data: '%s'", new String(dataToSend));

            if (myDevice.getXBeeProtocol() == XBeeProtocol.XBEE_WIFI) {
                myDevice.close();
                myDevice = new WiFiDevice(PORT, BAUD_RATE);
                myDevice.open();
                ((WiFiDevice)myDevice).sendBroadcastIPData(0x2616, dataToSend);
            } else
                
                    for(int i = 0; i< 20; i++){ // On crée une boucle qui va permettre d'émettre plusieurs fois (ici 20) notre valeur.
                        myDevice.sendBroadcastData(dataToSend); // On envoie simplement notre valeur. 
                    }
                    
                    
                
                

            System.out.println(" >> Success");

        } catch (XBeeException e) {
            System.out.println(" >> Error");
            e.printStackTrace();
            System.exit(1);
        } finally {
            myDevice.close();
        }
    
    }
}
