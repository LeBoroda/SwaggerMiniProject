package servise;

import api.IJsonable;
import api.store.PetJson;
import data.PetUrlData;
import logging.ILoggable;
import mock.Mocker;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import petstore.Pet;

import static okhttp.Client.JSON;

public class PetService extends AbsService implements ILoggable {
    private final IJsonable petJson = new PetJson();
    private Pet pet;

    public PetService(Pet pet) {
        this.pet = pet;
    }

    private String getPostJson() {
        return String.format(petJson.getPostJson(), pet.getPetId(), pet.getCategoryId(), pet.getCategoryName(), pet.getPetName(),
                pet.getPhotoUrl(), pet.getTagId(), pet.getTagName(), pet.getPetStatus());
    }

    public Response postPet() {
        log().info("Creating pet in system");
        RequestBody requestBody = RequestBody.create(getPostJson(), JSON);
        request = new Request.Builder()
                .url(Mocker.getUrl() + PetUrlData.POST.getName())
                .post(requestBody)
                .build();

        return client.call(request);
    }

    public Response getPet(Pet pet) {
        log().info("Getting pet from system");
        request = new Request.Builder()
                .url(Mocker.getUrl() + String.format(PetUrlData.GET.getName(), pet.getPetId()))
                .get()
                .build();

        return client.call(request);
    }

    public Response deletePet(Pet pet) {
        log().info("Deleting pet from system");
        request = new Request.Builder()
                .url(Mocker.getUrl() + String.format(PetUrlData.DELETE.getName(), pet.getPetId()))
                .delete()
                .build();

        return client.call(request);
    }

}
