package mahyco.iqc.nxg.model;

import java.io.File;

public class FileModel {
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    File file;
    String type;
}
