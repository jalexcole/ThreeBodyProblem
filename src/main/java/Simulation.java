import java.sql.*;
import com.google.gson.*;

public class Simulation {
    /**
     * Simulation is the governing class of the N-Body Simulation.
     * It is to create the system of objects of however many is needed and load
     * into a single page database file.
     *
     * The Simulation class will create workers that operate upon the NBody.db file
     * and then load results back into the .db file.
     *
     * The Simulation class will also act as a state machine to govern frame times.*/



    public Simulation(){

    }

    public Simulation(String[] args){

    }



    public void checkFileType(String word){
        String filetype = word.substring(word.lastIndexOf(".") + 1);
        if(filetype.equals("json")){

        } else if(filetype.equals("db")){

        }
    }


}
