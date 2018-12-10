package com.sy.dawnfile.file;

public class Files {
    private String filename;
    private String filepath;
    private String fileouter;
    private String createdata;
    private String fileurl;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFileouter() {
        return fileouter;
    }

    public void setFileouter(String fileouter) {
        this.fileouter = fileouter;
    }

    public String getCreatedata() {
        return createdata;
    }

    public void setCreatedata(String createdata) {
        this.createdata = createdata;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    @Override
    public String toString() {
        return "Files{" +
                "filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", fileouter='" + fileouter + '\'' +
                ", createdata='" + createdata + '\'' +
                ", fileurl='" + fileurl + '\'' +
                '}';
    }
}
