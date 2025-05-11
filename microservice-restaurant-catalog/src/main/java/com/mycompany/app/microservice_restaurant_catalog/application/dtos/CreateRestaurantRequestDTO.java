package com.mycompany.app.microservice_restaurant_catalog.application.dtos;

import jakarta.validation.constraints.*;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
public class CreateRestaurantRequestDTO {

    @NotBlank(message = "This field 'name' can't be empty or null.") // No nulo, no vacio, no solo espacios
    @Size(max = 100, message = "This field 'name' must contains less than 100 letters") // Ejemplo de tamaño maximo
    private String name;

    @NotBlank(message = "This field 'nit' can't be empty or null.")
    @Size(max = 10, message = "This fiel 'nit' must contains 10 chars")
    @Pattern(regexp = "\\d+", message = "This field 'nit' must constains only numbers") // Ejemplo: solo digitos. Ajusta segun formato real de NIT
    private String nit;

    @NotBlank(message = "El telefono del restaurante no puede estar vacio")
    @Size(max = 20, message = "El telefono del restaurante no puede exceder los {max} caracteres")
    @Pattern(regexp = "^\\+\\d{1,3}\\d{10}$", message = "El formato del telefono no es valido (ej. +573001234567)") // Ejemplo de formato con prefijo internacional + codigo pais + 10 digitos
    private String phone;

    @NotBlank(message = "La direccion del restaurante no puede estar vacia")
    @Size(max = 200, message = "La direccion del restaurante no puede exceder los {max} caracteres")
    private String address;

    @NotBlank(message = "La URL del logo no puede estar vacia")
    @Pattern(regexp = "^(http|https)://.*\\.(png|jpg|jpeg|gif)$", message = "La URL del logo debe ser una URL valida de imagen (png, jpg, jpeg, gif)") // Ejemplo de validacion de URL de imagen
    private String urlLogo;

    @NotNull(message = "El ID del propietario no puede ser nulo") // El ID del propietario es crucial
    @Min(value = 1, message = "El ID del propietario debe ser mayor o igual a 1") // Si los IDs son positivos
    private Long ownerId;

    public @NotBlank(message = "This field 'name' can't be empty or null.") @Size(max = 100, message = "This field 'name' must contains less than 100 letters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "This field 'name' can't be empty or null.") @Size(max = 100, message = "This field 'name' must contains less than 100 letters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "This field 'nit' can't be empty or null.") @Size(max = 10, message = "This fiel 'nit' must contains 10 chars") @Pattern(regexp = "\\d+", message = "This field 'nit' must constains only numbers") String getNit() {
        return nit;
    }

    public void setNit(@NotBlank(message = "This field 'nit' can't be empty or null.") @Size(max = 10, message = "This fiel 'nit' must contains 10 chars") @Pattern(regexp = "\\d+", message = "This field 'nit' must constains only numbers") String nit) {
        this.nit = nit;
    }

    public @NotBlank(message = "El telefono del restaurante no puede estar vacio") @Size(max = 20, message = "El telefono del restaurante no puede exceder los {max} caracteres") @Pattern(regexp = "^\\+\\d{1,3}\\d{10}$", message = "El formato del telefono no es valido (ej. +573001234567)") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "El telefono del restaurante no puede estar vacio") @Size(max = 20, message = "El telefono del restaurante no puede exceder los {max} caracteres") @Pattern(regexp = "^\\+\\d{1,3}\\d{10}$", message = "El formato del telefono no es valido (ej. +573001234567)") String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "La direccion del restaurante no puede estar vacia") @Size(max = 200, message = "La direccion del restaurante no puede exceder los {max} caracteres") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "La direccion del restaurante no puede estar vacia") @Size(max = 200, message = "La direccion del restaurante no puede exceder los {max} caracteres") String address) {
        this.address = address;
    }

    public @NotBlank(message = "La URL del logo no puede estar vacia") @Pattern(regexp = "^(http|https)://.*\\.(png|jpg|jpeg|gif)$", message = "La URL del logo debe ser una URL valida de imagen (png, jpg, jpeg, gif)") String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(@NotBlank(message = "La URL del logo no puede estar vacia") @Pattern(regexp = "^(http|https)://.*\\.(png|jpg|jpeg|gif)$", message = "La URL del logo debe ser una URL valida de imagen (png, jpg, jpeg, gif)") String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public @NotNull(message = "El ID del propietario no puede ser nulo") @Min(value = 1, message = "El ID del propietario debe ser mayor o igual a 1") Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(@NotNull(message = "El ID del propietario no puede ser nulo") @Min(value = 1, message = "El ID del propietario debe ser mayor o igual a 1") Long ownerId) {
        this.ownerId = ownerId;
    }
}
