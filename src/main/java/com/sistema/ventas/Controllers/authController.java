package com.sistema.ventas.Controllers;

import com.sistema.ventas.Dto.ApiResponse;
import com.sistema.ventas.Entities.Enums.Role;
import com.sistema.ventas.Entities.UserInfo;
import com.sistema.ventas.Services.UserInfoDetailService;
import com.sistema.ventas.exception.ServiceException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class authController {


    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";


    @Autowired
    UserInfoDetailService userInfoDetailService;


    @PostMapping("/createUser")
    public ResponseEntity<?> addNewUser(@RequestBody @Valid UserInfo userInfo) {
        try {
            userInfo.setRoles(Role.CLIENTE);
            UserInfo user = userInfoDetailService.addUser(userInfo);
            System.out.println(user);
            ApiResponse<UserInfo> userInfoApiResponse = new ApiResponse<>(SUCCESS, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userInfoApiResponse);
        } catch (ServiceException ex) {
            ApiResponse<String> errorResponse = new ApiResponse<>(ERROR, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
