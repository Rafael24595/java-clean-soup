package core.java;

import java.io.File;

class Arguments {

    static Arguments instance;

    String[] args;

    private Arguments(String[] args){
        this.args = args;
    }

    static void initialize(String[] args) {
        if(instance == null)
            instance = new Arguments(args);
    }

    static File customConfigurationFile() {
        File file = null;
        if(instance != null && instance.args.length > 0){
            String path = instance.args[0];
            file = new File(path);
        }

        return file != null && file.exists() ? file : null;
    }

}