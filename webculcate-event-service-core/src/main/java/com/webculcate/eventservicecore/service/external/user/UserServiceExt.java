package com.webculcate.eventservicecore.service.external.user;

import com.webculcate.eventservicecore.model.external.user.UserBulkRequest;
import com.webculcate.eventservicecore.model.external.user.UserBulkResponse;
import com.webculcate.eventservicecore.model.external.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.webculcate.eventservicecore.utility.ServiceHelper.nullHandledExtraction;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceExt {

    private final UserServiceClient userServiceClient;

    public List<UserDto> resolveUsers(Set<Long> userIds) {
        if (userIds.isEmpty())
            return Collections.EMPTY_LIST;
        List<UserDto> userDtoList = getUserDtoBulk(userIds);
        int difference = userIds.size() - userDtoList.size();
        if (difference > 0)
            log.info("Unable to resolve " + difference + " users");
        return userDtoList;
    }

    public Optional<UserDto> resolveUser(Long userId) {
        if (isNull(userId))
            return Optional.empty();
        Optional<UserDto> optionalUserDto = getUserDto(userId);
        if (!optionalUserDto.isPresent())
            log.info("Unable to resolve user");
        return optionalUserDto;
    }

    private List<UserDto> getUserDtoBulk(Set<Long> userIds) {
        try {
            ResponseEntity<UserBulkResponse> response = userServiceClient.getUserBulk(new UserBulkRequest(userIds));
            Optional<List<UserDto>> optionalUserDtoList = nullHandledExtraction(() -> response.getBody().getUserList());
            return optionalUserDtoList.orElse(Collections.EMPTY_LIST);
        } catch (Exception exception) {
            log.error("Exception : ", exception);
        }
        return Collections.EMPTY_LIST;
    }

    private Optional<UserDto> getUserDto(Long userId) {
        try {
            ResponseEntity<UserDto> response = userServiceClient.getUser(userId);
            Optional<UserDto> optionalUserDto = nullHandledExtraction(() -> response.getBody());
            return optionalUserDto;
        } catch (Exception exception) {
            log.error("Exception : ", exception);
        }
        return Optional.empty();
    }

}
