package model.entities.vowelEntities;

public class Word {
    private String fullWords;
    private String wordWith_;
    private String imgName;

    public Word(String fullWords, String wordWith_, String imgName) {
        this.fullWords = fullWords;
        this.wordWith_ = wordWith_;
        this.imgName = imgName;
    }

    @Override
    public String toString() {
        return "Word{" +
                "fullWords='" + fullWords + '\'' +
                ", wordWith_='" + wordWith_ + '\'' +
                ", imgName='" + imgName + '\'' +
                '}';
    }

    public String getFullWords() {
        return fullWords;
    }

    public String getWordWith_() {
        return wordWith_;
    }

    public String getImgName() {
        return imgName;
    }
}
