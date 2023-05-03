package data;

public enum PetCategoryData {
    CATS(1, "cats"),
    DOGS(2, "dogs");

    private final int categoryNumber;
    private final String categoryName;

    PetCategoryData(final int categoryNumber, final String categoryName) {
        this.categoryNumber = categoryNumber;
        this.categoryName = categoryName;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
