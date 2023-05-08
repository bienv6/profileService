package com.upwork.DemoProject.ProfileData.service;


import com.upwork.DemoProject.ProfileData.Entity.Profile;
import com.upwork.DemoProject.ProfileData.dto.ProfileRequestDto;
import com.upwork.DemoProject.ProfileData.dto.ProfileResponseDto;
import com.upwork.DemoProject.ProfileData.dto.ProfileResponseDtoForJwt;
import com.upwork.DemoProject.ProfileData.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto) {

        Optional<Profile> profileOptional = profileRepository.findByEmail(profileRequestDto.getEmail());
        if (profileOptional.isPresent()) {
            throw new RuntimeException("This username already exist please login");
        }
        Profile profile = modelMapper.map(profileRequestDto, Profile.class);
        String encodedPassword = Base64.getEncoder().encodeToString(profileRequestDto.getAuthRecord().getPassword().getBytes());
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
}
