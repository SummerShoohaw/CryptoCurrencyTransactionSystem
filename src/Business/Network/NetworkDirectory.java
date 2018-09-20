/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Network;

import java.util.ArrayList;

/**
 *
 * @author summershoohaw
 */
public class NetworkDirectory {
    
    private ArrayList<Network> networkDirectory;
    
    public NetworkDirectory(){
        networkDirectory = new ArrayList<>();
    }
    

    public ArrayList<Network> getNetworkDirectory() {
        return networkDirectory;
    }
    
    //remove network
    public void removeNetwork(Network n){
        
    }
}
