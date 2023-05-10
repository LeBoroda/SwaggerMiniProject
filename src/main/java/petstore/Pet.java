package petstore;

import data.PetCategoryData;
import data.PetStatusData;
import data.PetTagsData;
import okhttp3.Response;
import servise.PetService;


public final class Pet extends SwaggerObject {
    private final int petId;
    private final int categoryId;
    private final String categoryName;
    private final String petName;
    private final String photoUrl;
    private final int tagId;
    private final String tagName;
    private final String petStatus;
    private final PetService petService = new PetService(this);

    public Pet(final PetCategoryData petCategoryId, final String petName, final PetTagsData petTagId) {
        super();
        this.petId = (int) (Math.random() * 100);
        this.categoryId = petCategoryId.getCategoryNumber();
        this.categoryName = setPetCategoryName(petCategoryId);
        this.petName = petName;
        this.photoUrl = "photoUrl";
        this.tagId = petTagId.getTagNumber();
        this.tagName = getPetTagName(petTagId);
        this.petStatus = PetStatusData.AVAILABLE.getName();
    }

    public int getPetId() {
        return this.petId;
    }

    private String setPetCategoryName(final PetCategoryData petCategoryId) {
        switch (petCategoryId.getCategoryNumber()) {
            case 1:
                return PetCategoryData.CATS.getCategoryName();
            case 2:
                return PetCategoryData.DOGS.getCategoryName();
            default:
                return null;
        }
    }

    private String getPetTagName(final PetTagsData petTagNumber) {
        switch (petTagNumber.getTagNumber()) {
            case 1:
                return PetTagsData.TESTDOG.getTagName();
            default:
                return null;
        }
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getPetName() {
        return petName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public String getPetStatus() {
        return petStatus;
    }

    public Response postPetInPetStore() {
        return petService.postPet();
    }

    public Response getPetFromPetStore() {
        return petService.getPet(this);
    }

    public Response deletePetFromSystem() {
        return petService.deletePet(this);
    }
}
