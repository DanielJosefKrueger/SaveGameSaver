package de.dtech.savegamesaver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Saver {


    public static void main(String[] args) {


        ConfigLoader configLoader = new ConfigLoader();
        List<SaveOrigin> saveOrigins = configLoader.loadSaves(args[0]);
        File saveFolder = new File("saves");
        saveFolder.mkdir();

        for (SaveOrigin saveOrigin : saveOrigins) {
            File folder = new File(saveFolder, saveOrigin.getName());
            folder.mkdir();
            try {
                copyFilesInDirectory(saveOrigin.getFolder(), folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void copyFilesInDirectory(File from, File to) throws IOException {
        if(!to.exists()) {
            to.mkdirs();
        }
        File[] files = from.listFiles();
        if(files==null){
            //TODO
            return;
        }


        for (File file : files) {
            if (file.isDirectory()) {
                copyFilesInDirectory(file, new File(to.getAbsolutePath() + "/" + file.getName()));
            } else {
                File n = new File(to.getAbsolutePath() + "/" + file.getName());
                System.out.println("copy file: "+ n);
                Files.copy(file.toPath(), n.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
