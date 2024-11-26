package services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FormService {
    // Endpoint para enviar los datos del formulario
    @POST("forms")
    Call<Void> submitFormulary(@Body UserForm form);
}
