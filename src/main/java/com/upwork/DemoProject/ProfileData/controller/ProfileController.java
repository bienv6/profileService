package com.upwork.DemoProject.ProfileData.controller;

import com.upwork.DemoProject.ProfileData.dto.ProfileRequestDto;
import com.upwork.DemoProject.ProfileData.dto.ProfileResponseDto;
import com.upwork.DemoProject.ProfileData.dto.ProfileResponseDtoForJwt;
import com.upwork.DemoProject.ProfileData.service.ProfileService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;


    @PostMapping
    private ResponseEntity<ProfileResponseDto> createProfile(@Parameter(name = "PersonRequestDto",required = true) @RequestBody @Valid ProfileRequestDto profileRequestDto) {

        return new ResponseEntity<>(profileService.createProfile(profileRequestDto), HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<ProfileResponseDto> getProfile(@PathVariable(value = "id") long id){
        return new ResponseEntity<ProfileResponseDto>(profileService.getPerson(id),HttpStatus.OK);
    }

    @GetMapping(value = "getprofilebyusername/{username}")
    private ResponseEntity<ProfileResponseDtoForJwt> getProfileByUsername(@PathVariable(value = "username") String username){
        return new ResponseEntity<ProfileResponseDtoForJwt>(profileService.getPersonByUsername(username),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    private ResponseEntity<String> deleteProfile(@PathVariable(value = "id") long id){
        profileService.deleteProfile(id);

        return new ResponseEntity<>("Profile deleted successfully",HttpStatus.OK);

    }

    @GetMapping(value = "/list")
    private ResponseEntity<List<ProfileResponseDto>> getProfiles(){
        return new ResponseEntity<>(profileService.getProfileResponseDtos(),HttpStatus.OK);

    }
}
