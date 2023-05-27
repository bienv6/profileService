package com.upwork.DemoProject.ProfileData.service;


import com.upwork.DemoProject.ProfileData.Entity.Profile;
import com.upwork.DemoProject.ProfileData.dto.ProfileRequestDto;
import com.upwork.DemoProject.ProfileData.dto.ProfileResponseDto;
import com.upwork.DemoProject.ProfileData.dto.ProfileResponseDtoForJwt;
import com.upwork.DemoProject.ProfileData.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    List<ProfileResponseDto> profileResponseDtos;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) {

        Optional<Profile> profileOptional = profileRepository.findByEmail(profileRequestDto.getEmail());
        if (profileOptional.isPresent()) {
            throw new RuntimeException("This username already exist please login");
        }
        Profile profile = modelMapper.map(profileRequestDto, Profile.class);
        String encodedPassword = passwordEncoder.encode(profileRequestDto.getAuthRecord().getPassword());
        profile.getAuthRecord().setPassword(encodedPassword);
        profile.getAuthRecord().setUsername(profile.getEmail());
        profile.getAuthRecord().setPersonId(profile);
        return modelMapper.map(profileRepository.save(profile), ProfileResponseDto.class);
    }

    public ProfileResponseDto getPerson(long id) {
        return modelMapper.map(profileRepository.findById(id).get(), ProfileResponseDto.class);
    }

    public List<ProfileResponseDto> getProfileResponseDtos() {
        profileResponseDtos = profileRepository.findAll().stream().map(profile -> modelMapper.map(profile, ProfileResponseDto.class)).collect(Collectors.toList());
        return profileResponseDtos;
    }

    public void deleteProfile(long id) {
        Optional<Profile> profileOptional = profileRepository.findById(id);
        if (profileOptional.isEmpty()) {
            throw new RuntimeException("Profile does not exist with this id");
        }
        profileRepository.delete(profileOptional.get());
    }

     public ProfileResponseDtoForJwt getPersonByUsername(String username) {

        Optional<Profile> profileOptional = profileRepository.findByEmail(username);

        ProfileResponseDtoForJwt profileResponseDtoForJwt=modelMapper.map(profileOptional.get(),ProfileResponseDtoForJwt.class);

        profileResponseDtoForJwt.setPassword(profileOptional.get().getAuthRecord().getPassword());

        return profileResponseDtoForJwt;

    }

    public Boolean isValidatedUser (String userName, String password){
        ProfileResponseDtoForJwt profile = getPersonByUsername(userName);
        return profile != null && passwordEncoder.matches(password,profile.getPassword());
    }

    public ProfileResponseDto signInUser (String userName, String password){
        Profile profile = null;
       if (isValidatedUser(userName,password)){
           profile = profileRepository.findByEmail(userName).get();
       }
        return modelMapper.map(profile,ProfileResponseDto.class);
    }

}
