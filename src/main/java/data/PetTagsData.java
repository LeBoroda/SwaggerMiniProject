package data;

public enum PetTagsData {

    TESTDOG(1, "testDog");
    private final int tagNumber;
    private final String tagName;

    PetTagsData(final int tagNumber, final String tagName) {
        this.tagNumber = tagNumber;
        this.tagName = tagName;
    }

    public int getTagNumber() {
        return tagNumber;
    }

    public String getTagName() {
        return tagName;
    }
}
