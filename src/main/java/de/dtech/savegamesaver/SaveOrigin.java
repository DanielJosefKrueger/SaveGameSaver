package de.dtech.savegamesaver;

import java.io.File;

public class SaveOrigin {

    private final String name;
    private final File folder;


    public SaveOrigin(String name, File folder) {
        if(name ==null){
            throw new IllegalArgumentException("Name must no be null");
        }
        if(folder ==null){
            throw new IllegalArgumentException("Folder must no be null");
        }
        this.name = name;
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public File getFolder() {
        return folder;
    }



    public static SaveOrigin fromString(String s){
        String[] split = s.split(";");
        if(split.length< 2){
            throw new IllegalArgumentException();
        }
        File file = new File(split[1]);
        if(file ==null){
            System.out.println("File must not be null");
        }
        return new SaveOrigin(split[0], file);
    }

    @Override
    public String toString() {
        return "SaveOrigin{" +
                "name='" + name + '\'' +
                ", folder=" + folder.getAbsolutePath()+
                '}';
    }

}
