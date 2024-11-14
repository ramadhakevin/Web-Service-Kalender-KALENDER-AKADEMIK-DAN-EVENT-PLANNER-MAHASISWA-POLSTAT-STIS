package com.polstat.kalender.auth;

import com.polstat.kalender.dto.UserDto;
import com.polstat.kalender.exception.InvalidPasswordException;
import com.polstat.kalender.exception.UserNotFoundException;
import com.polstat.kalender.request.UpdatePasswordRequest;
import com.polstat.kalender.request.UpdateProfileRequest;
import com.polstat.kalender.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;

    @Operation(summary = "User login to get access token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Email and access token",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))}
            ),
            @ApiResponse(responseCode = "401",
                    description = "Invalid credentials",
                    content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            String accessToken = jwtUtil.generateAccessToken(authentication);
            UserDto userDto = userService.getUserByEmail(request.getEmail());
            AuthResponse response = new AuthResponse(userDto.getId(), request.getEmail(), accessToken);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Register a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User registered successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "409",
                    description = "Conflict - User with the same email already exists",
                    content = @Content),
    })

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto request) {
        UserDto user = userService.createUser(request);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Update user profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User profile updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content),
    })
    @PatchMapping("/update-profile/{userId}")
    public ResponseEntity<UserDto> updateProfile(
            @PathVariable Long userId,
            @RequestBody UpdateProfileRequest request
    ) {
        UserDto updatedUser = userService.updateUserProfile(userId, request.getName(), request.getEmail());
        if (updatedUser != null) {
            return ResponseEntity.ok().body(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update user password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User password updated successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content),
    })
    @PatchMapping("/update-password/{id}")
    public ResponseEntity<String> updateUserPassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequest request
    ) {
        try {
            UserDto updatedUser = userService.updateUserPassword(id, request.getOldPassword(), request.getNewPassword());
            return ResponseEntity.ok("Password updated successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password");
        }
    }

    @Operation(summary = "Delete user profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @Operation(summary = "View user profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User profile retrieved successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}
            ),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content),
    })
    @GetMapping("/view-profile/id/{id}")
    public ResponseEntity<UserDto> viewUserProfile(@PathVariable Long id) {
        try {
            UserDto userProfile = userService.viewProfile(id);
            return ResponseEntity.ok(userProfile);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/view-profile/email/{email}")
    public ResponseEntity<UserDto> viewUserProfile(@PathVariable String email) {
        try {
            UserDto userProfile = userService.getUserByEmail(email);
            return ResponseEntity.ok(userProfile);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
