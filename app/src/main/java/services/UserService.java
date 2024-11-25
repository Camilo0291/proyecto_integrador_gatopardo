package services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    // Endpoint para registrar un usuario
    @POST("users")
    Call<Void> registerUser(@Body User user);

    // Endpoint para obtener la lista de usuarios
    @GET("users")
    Call<List<User>> getUsers();
}
