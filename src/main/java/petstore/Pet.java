package petstore;

import api.IJsonable;
import api.store.PetJson;
import data.PetCategoryData;
import data.PetStatusData;
import data.PetTagsData;
import data.PetUrlData;
import mock.Mocker;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static okhttp.Client.JSON;

public final class Pet extends SwaggerObject {
    private final int petId;
    private final int categoryId;
    private final String categoryName;
    private final String petName;
    private final String photoUrl;
    private final int tagId;
    private final String tagName;
    private final String petStatus;
    private final IJsonable petJson = new PetJson();

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

    private String getPostJson() {
        return String.format(petJson.getPostJson(), this.petId, this.categoryId, this.categoryName, this.petName, this.photoUrl,
                this.tagId, this.tagName, this.petStatus);
    }

    protected Response postPet() {
        log().info("Creating pet in system");
        RequestBody requestBody = RequestBody.create(getPostJson(), JSON);
        request = new Request.Builder()
                .url(Mocker.getUrl() + PetUrlData.POST.getName())
                .post(requestBody)
                .build();

        return client.call(request);
    }

    protected Response getPet() {
        log().info("Getting pet from system");
        request = new Request.Builder()
                .url(Mocker.getUrl() + String.format(PetUrlData.GET.getName(), this.petId))
                .get()
                .build();

        return client.call(request);
    }

    public Response deletePet() {
        log().info("Deleting pet from system");
        request = new Request.Builder()
                .url(Mocker.getUrl() + String.format(PetUrlData.DELETE.getName(), this.petId))
                .delete()
                .build();

        return client.call(request);
    }
}
