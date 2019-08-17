package de.dtech.savegamesaver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ConfigLoader {



    List<SaveOrigin> loadSaves(String configFile){

        List<SaveOrigin> saves = new ArrayList<>();

        try(BufferedReader in = new BufferedReader(new FileReader(new File((configFile))))){
            in.lines()
                    .filter(e->!e.isEmpty())
                    //.peek(System.out::println)
                    .map(ConfigLoader::replaceEnvironmentVariables)
                    .map(SaveOrigin::fromString)
                    .peek(System.out::println)
                    .forEach(saves::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saves;
    }



    private static String replaceEnvironmentVariables(String string){
        //appdata
        String appData = System.getenv("APPDATA");
        if(appData!=null){
            appData = appData.substring(0, appData.lastIndexOf("\\")); //remove
            appData = appData.replace("\\", "\\\\"); // escape the backslash!
            string =  string.replaceAll("\\$APPDATA", appData );
        }else{
            System.out.println("Appdata not found");
        }

        //MYGAMES
        string =  string.replaceAll("\\$MYGAMES", "\\$USERFOLDER//Documents//My Games//");


        //user folder
        String userprofile = System.getenv("USERPROFILE");
        if(userprofile!=null){

            userprofile = userprofile.replace("\\", "\\\\"); // escape the backslash!
            string =  string.replaceAll("\\$USERFOLDER", userprofile );
        }else{
            System.out.println("USERPROFILE not found");
        }





     return string;
    }


}
